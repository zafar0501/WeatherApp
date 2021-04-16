package com.example.setting_scrren;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.weatherapp.R;

import java.util.Date;


public class SettingsCityActivity extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    protected static final int MY_PERMISSIONS_FOREGROUND_SERVICE = 2;

    // Thursday 2016-01-14 16:00:00
    private Date SAMPLE_DATE = new Date(1452805200000L);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        int theme;


        super.onCreate(savedInstanceState);

        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        View bar = LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addPreferencesFromResource(R.xml.prefs);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        setCustomDateEnabled();
        updateDateFormatList();

        // Set summaries to current value
        setListPreferenceSummary("unit");
        setListPreferenceSummary("lengthUnit");
        setListPreferenceSummary("speedUnit");
        setListPreferenceSummary("pressureUnit");
        setListPreferenceSummary("refreshInterval");
        setListPreferenceSummary("windDirectionFormat");
        setListPreferenceSummary("theme");

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case "unit":
            case "lengthUnit":
            case "speedUnit":
            case "pressureUnit":
            case "windDirectionFormat":
                setListPreferenceSummary(key);
                break;

            case "dateFormat":
                setCustomDateEnabled();
                setListPreferenceSummary(key);
                break;
            case "dateFormatCustom":
                updateDateFormatList();
                break;
            case "theme":
                // Restart activity to apply theme
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                break;
            case "updateLocationAutomatically":
                if (sharedPreferences.getBoolean(key, false)) {
                    requestReadLocationPermission();
                }
                break;
            case "apiKey":
                checkKey(key);
                break;

        }
    }

    private void requestReadLocationPermission() {
        System.out.println("Calling request location permission");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Explanation not needed, since user requests this themself

            }
        } else {
            privacyGuardWorkaround();
        }
    }

    private void requestForegroundServicePermission() {
        System.out.println("Calling request foreground service permission");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                && ContextCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE)
                != PackageManager.PERMISSION_GRANTED) {
            // this is normal permission, so no need to show explanation to user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.FOREGROUND_SERVICE},
                    MY_PERMISSIONS_FOREGROUND_SERVICE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

    }

    private void privacyGuardWorkaround() {
        // Workaround for CM privacy guard. Register for location updates in order for it to ask us for permission
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            DummyLocationListener dummyLocationListener = new DummyLocationListener();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, dummyLocationListener);
            locationManager.removeUpdates(dummyLocationListener);
        } catch (SecurityException e) {
            // This will most probably not happen, as we just got granted the permission
        }
    }


    private void setListPreferenceSummary(String preferenceKey) {
        ListPreference preference = (ListPreference) findPreference(preferenceKey);
        preference.setSummary(preference.getEntry());
    }

    private void setCustomDateEnabled() {
        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        Preference customDatePref = findPreference("dateFormatCustom");
        customDatePref.setEnabled("custom".equals(sp.getString("dateFormat", "")));
    }

    private void updateDateFormatList() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Resources res = getResources();

        ListPreference dateFormatPref = (ListPreference) findPreference("dateFormat");
        String[] dateFormatsValues = res.getStringArray(R.array.dateFormatsValues);
        String[] dateFormatsEntries = new String[dateFormatsValues.length];

        EditTextPreference customDateFormatPref = (EditTextPreference) findPreference("dateFormatCustom");
        customDateFormatPref.setDefaultValue(dateFormatsValues[0]);


        dateFormatPref.setDefaultValue(dateFormatsValues[0]);
        dateFormatPref.setEntries(dateFormatsEntries);

        setListPreferenceSummary("dateFormat");
    }

    private void checkKey(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getString(key, "").equals("")) {
            sp.edit().remove(key).apply();
        }
    }

    public class DummyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
