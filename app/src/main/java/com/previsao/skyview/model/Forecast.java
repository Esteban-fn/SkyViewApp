package com.previsao.skyview.model;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("date")
    private String date;

    @SerializedName("weekday")
    private String weekday;

    @SerializedName("max")
    private int max;

    @SerializedName("min")
    private int min;

    @SerializedName("description")
    private String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
