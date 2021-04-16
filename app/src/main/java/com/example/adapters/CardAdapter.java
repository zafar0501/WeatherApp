package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.DataObject;
import com.example.utilities.Constants;
import com.example.weatherapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private final List<DataObject> dataSet;

    public CardAdapter(List<DataObject> data) {
        this.dataSet = data;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_weather, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int listPosition) {
        DataObject thisObject = dataSet.get(listPosition);
        holder.timeStamp.setText(getTime(thisObject.getDt() * 1000));
        holder.textViewDescription.setText(thisObject.getWeather().get(0).getDescription());
        holder.textViewWind.setText("Wind: " + thisObject.getWind().getSpeed() + " m/s");
        holder.textViewPressure.setText("Pressure: " + thisObject.getMain().getPressure() + " hPa");
        holder.textViewHumidity.setText("Humidity: " + thisObject.getMain().getHumidity() + " %");
        holder.textViewTemperature.setText(thisObject.getMain().getTemp() + Constants.TEMP_UNIT);
        switch (thisObject.getWeather().get(0).getIcon()) {
            case "01d":
            case "01n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_clear_sky);
                holder.cardView.setBackgroundResource(R.color.color_clear_and_sunny);
                break;
            case "02d":
            case "02n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_few_cloud);
                holder.cardView.setBackgroundResource(R.color.color_partly_cloudy);
                break;
            case "03d":
            case "03n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_scattered_clouds);
                holder.cardView.setBackgroundResource(R.color.color_gusty_winds);
                break;
            case "04d":
            case "04n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_broken_clouds);
                holder.cardView.setBackgroundResource(R.color.color_cloudy_overnight);
                break;
            case "09d":
            case "09n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_shower_rain);
                holder.cardView.setBackgroundResource(R.color.color_hail_stroms);
                break;
            case "10d":
            case "10n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_rain);
                holder.cardView.setBackgroundResource(R.color.color_heavy_rain);
                break;
            case "11d":
            case "11n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_thunderstorm);
                holder.cardView.setBackgroundResource(R.color.color_thunderstroms);
                break;
            case "13d":
            case "13n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_snow);
                holder.cardView.setBackgroundResource(R.color.color_snow);
                break;
            case "15d":
            case "15n":
                holder.imageViewIcon.setImageResource(R.drawable.ic_weather_mist);
                holder.cardView.setBackgroundResource(R.color.color_mix_snow_and_rain);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private String getTime(Long milliTime) {
        Date currentDate = new Date(milliTime);
        SimpleDateFormat df = new SimpleDateFormat("E, dd.MM.yyyy - hh:mm a");
        String date = df.format(currentDate);
        return date;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView timeStamp;
        TextView textViewDescription;
        TextView textViewWind;
        TextView textViewPressure;
        TextView textViewHumidity;
        ImageView imageViewIcon;
        TextView textViewTemperature;


        public CardViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.card_view);
            this.timeStamp = itemView.findViewById(R.id.timeStamp);
            this.textViewDescription = itemView.findViewById(R.id.textDescription);
            this.textViewWind = itemView.findViewById(R.id.wind);
            this.textViewPressure = itemView.findViewById(R.id.pressure);
            this.textViewHumidity = itemView.findViewById(R.id.humidity);
            this.textViewTemperature = itemView.findViewById(R.id.temp);
            this.imageViewIcon = itemView.findViewById(R.id.icon);
        }
    }
}
