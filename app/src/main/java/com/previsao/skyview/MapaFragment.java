package com.previsao.skyview;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationViewModel locationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_container);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Observe a mudança de localização
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocation().observe(getViewLifecycleOwner(), this::showCityOnMap);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Mostra a localização inicial
        showCityOnMap("São Paulo");
    }

    public void showCityOnMap(String cityName) {
        if (mMap == null || cityName == null || cityName.isEmpty()) return;

        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addresses = geocoder.getFromLocationName(cityName, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                LatLng cityLocation = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.clear(); // Limpa marcadores antigos
                mMap.addMarker(new MarkerOptions().position(cityLocation).title("Marcador em " + cityName));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 12));
            } else {
                Toast.makeText(getContext(), "Cidade não encontrada no mapa.", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(getContext(), "Erro ao buscar localização no mapa.", Toast.LENGTH_SHORT).show();
        }
    }
}
