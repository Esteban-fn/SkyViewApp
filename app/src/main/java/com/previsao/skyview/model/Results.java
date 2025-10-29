package com.previsao.skyview.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Results {

    @SerializedName("temp")
    private int temp;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("forecast")
    private List<Forecast> forecast;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
