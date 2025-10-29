package com.previsao.skyview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationViewModel extends ViewModel {
    private final MutableLiveData<String> locationData = new MutableLiveData<>();

    public void setLocation(String location) {
        locationData.setValue(location);
    }

    public LiveData<String> getLocation() {
        return locationData;
    }
}
