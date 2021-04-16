package com.example.utilities;


import com.example.models.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {
    @GET("forecast")
    Call<Forecast> getWeatherForecastData(@Query("q") StringBuilder cityName, @Query("APPID") String APIKEY, @Query("units") String TempUnit);


    // api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
}
