package com.example.home_screen.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Locale;

public class CityAddress implements Parcelable {

    private Locale mLocale;

    private String mFeatureName;
    private HashMap<Integer, String> mAddressLines;
    private int mMaxAddressLineIndex = -1;
    private String mAdminArea;
    private String mSubAdminArea;
    private String mLocality;
    private String mSubLocality;
    private String mThoroughfare;
    private String mSubThoroughfare;
    private String mPremises;
    private String mPostalCode;
    private String mCountryCode;
    private String mCountryName;
    private double mLatitude;
    private double mLongitude;
    private boolean mHasLatitude = false;
    private boolean mHasLongitude = false;
    private String mPhone;
    private String mUrl;
    private Bundle mExtras = null;

    public CityAddress(Parcel in) {
        mFeatureName = in.readString();
        mMaxAddressLineIndex = in.readInt();
        mAdminArea = in.readString();
        mSubAdminArea = in.readString();
        mLocality = in.readString();
        mSubLocality = in.readString();
        mThoroughfare = in.readString();
        mSubThoroughfare = in.readString();
        mPremises = in.readString();
        mPostalCode = in.readString();
        mCountryCode = in.readString();
        mCountryName = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
        mHasLatitude = in.readByte() != 0;
        mHasLongitude = in.readByte() != 0;
        mPhone = in.readString();
        mUrl = in.readString();
        mExtras = in.readBundle();
        mCityName = in.readString();
        id = in.readInt();
        from = in.readString();
        subject = in.readString();
        message = in.readString();
        timestamp = in.readString();
        picture = in.readString();
        isImportant = in.readByte() != 0;
        isRead = in.readByte() != 0;
        color = in.readInt();
    }

    public CityAddress() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFeatureName);
        dest.writeInt(mMaxAddressLineIndex);
        dest.writeString(mAdminArea);
        dest.writeString(mSubAdminArea);
        dest.writeString(mLocality);
        dest.writeString(mSubLocality);
        dest.writeString(mThoroughfare);
        dest.writeString(mSubThoroughfare);
        dest.writeString(mPremises);
        dest.writeString(mPostalCode);
        dest.writeString(mCountryCode);
        dest.writeString(mCountryName);
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
        dest.writeByte((byte) (mHasLatitude ? 1 : 0));
        dest.writeByte((byte) (mHasLongitude ? 1 : 0));
        dest.writeString(mPhone);
        dest.writeString(mUrl);
        dest.writeBundle(mExtras);
        dest.writeString(mCityName);
        dest.writeInt(id);
        dest.writeString(from);
        dest.writeString(subject);
        dest.writeString(message);
        dest.writeString(timestamp);
        dest.writeString(picture);
        dest.writeByte((byte) (isImportant ? 1 : 0));
        dest.writeByte((byte) (isRead ? 1 : 0));
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityAddress> CREATOR = new Creator<CityAddress>() {
        @Override
        public CityAddress createFromParcel(Parcel in) {
            return new CityAddress(in);
        }

        @Override
        public CityAddress[] newArray(int size) {
            return new CityAddress[size];
        }
    };

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    private String mCityName = null;
    private int id;
    private String from;
    private String subject;
    private String message;
    private String timestamp;
    private String picture;
    private boolean isImportant;
    private boolean isRead;
    private int color = -1;

    public Locale getLocale() {
        return mLocale;
    }

    public void setLocale(Locale mLocale) {
        this.mLocale = mLocale;
    }

    public String getFeatureName() {
        return mFeatureName;
    }

    public void setFeatureName(String mFeatureName) {
        this.mFeatureName = mFeatureName;
    }

    public HashMap<Integer, String> getAddressLines() {
        return mAddressLines;
    }

    public void setAddressLines(HashMap<Integer, String> mAddressLines) {
        this.mAddressLines = mAddressLines;
    }

    public int getMaxAddressLineIndex() {
        return mMaxAddressLineIndex;
    }

    public void setMaxAddressLineIndex(int mMaxAddressLineIndex) {
        this.mMaxAddressLineIndex = mMaxAddressLineIndex;
    }

    public String getAdminArea() {
        return mAdminArea;
    }

    public void setAdminArea(String mAdminArea) {
        this.mAdminArea = mAdminArea;
    }

    public String getSubAdminArea() {
        return mSubAdminArea;
    }

    public void setSubAdminArea(String mSubAdminArea) {
        this.mSubAdminArea = mSubAdminArea;
    }

    public String getLocality() {
        return mLocality;
    }

    public void setLocality(String mLocality) {
        this.mLocality = mLocality;
    }

    public String getSubLocality() {
        return mSubLocality;
    }

    public void setSubLocality(String mSubLocality) {
        this.mSubLocality = mSubLocality;
    }

    public String getThoroughfare() {
        return mThoroughfare;
    }

    public void setThoroughfare(String mThoroughfare) {
        this.mThoroughfare = mThoroughfare;
    }

    public String getSubThoroughfare() {
        return mSubThoroughfare;
    }

    public void setSubThoroughfare(String mSubThoroughfare) {
        this.mSubThoroughfare = mSubThoroughfare;
    }

    public String getPremises() {
        return mPremises;
    }

    public void setPremises(String mPremises) {
        this.mPremises = mPremises;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String mPostalCode) {
        this.mPostalCode = mPostalCode;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String mCountryCode) {
        this.mCountryCode = mCountryCode;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public boolean ismHasLatitude() {
        return mHasLatitude;
    }

    public void setHasLatitude(boolean mHasLatitude) {
        this.mHasLatitude = mHasLatitude;
    }

    public boolean ismHasLongitude() {
        return mHasLongitude;
    }

    public void setHasLongitude(boolean mHasLongitude) {
        this.mHasLongitude = mHasLongitude;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public void setExtras(Bundle mExtras) {
        this.mExtras = mExtras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getessage() {
        return message;
    }

    public void setessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
