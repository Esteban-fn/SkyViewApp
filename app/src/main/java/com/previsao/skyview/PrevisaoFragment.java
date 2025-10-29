package com.previsao.skyview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.previsao.skyview.api.RetrofitClient;
import com.previsao.skyview.api.WeatherService;
import com.previsao.skyview.model.Forecast;
import com.previsao.skyview.model.Results;
import com.previsao.skyview.model.WeatherResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrevisaoFragment extends Fragment {

    private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    private WeatherService weatherService;
    private LocationViewModel locationViewModel;

    private TextView textCityName, textCurrentTemp, textCurrentCondition;

    private static final String API_KEY = "4740221f"; // Sua chave da HG Brasil

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_previsao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Binding dos novos TextViews
        textCityName = view.findViewById(R.id.text_city_name);
        textCurrentTemp = view.findViewById(R.id.text_current_temp);
        textCurrentCondition = view.findViewById(R.id.text_current_condition);

        recyclerView = view.findViewById(R.id.recycler_view_previsao);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ForecastAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        weatherService = RetrofitClient.getClient().create(WeatherService.class);

        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocation().observe(getViewLifecycleOwner(), this::fetchWeatherData);

        fetchWeatherData("São Paulo,SP");
    }

    private void fetchWeatherData(String cityName) {
        if (cityName == null || cityName.isEmpty()) return;
        Toast.makeText(getContext(), "Buscando previsão para: " + cityName, Toast.LENGTH_SHORT).show();

        Call<WeatherResponse> call = weatherService.getWeather(API_KEY, cityName);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (getContext() == null) return;

                if (response.isSuccessful() && response.body() != null) {
                    Results results = response.body().getResults();
                    if (results != null) {
                        // Preenche os campos do tempo atual
                        textCityName.setText(results.getCityName());
                        textCurrentTemp.setText(results.getTemp() + "°");

                        if (results.getForecast() != null && !results.getForecast().isEmpty()) {
                            textCurrentCondition.setText(results.getForecast().get(0).getDescription());
                            adapter.updateData(results.getForecast());
                        }
                    } else {
                         Toast.makeText(getContext(), "Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Erro ao buscar previsão.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                if (getContext() == null) return;
                Toast.makeText(getContext(), "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
