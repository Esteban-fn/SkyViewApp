package com.previsao.skyview.api;

import com.previsao.skyview.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("key") String key,
            @Query("city_name") String cityName
    );
}
