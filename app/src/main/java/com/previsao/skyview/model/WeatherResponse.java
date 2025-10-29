package com.previsao.skyview.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("valid_key")
    private boolean validKey;

    @SerializedName("results")
    private Results results;

    public boolean isValidKey() {
        return validKey;
    }

    public void setValidKey(boolean validKey) {
        this.validKey = validKey;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
