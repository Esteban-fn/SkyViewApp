package com.previsao.skyview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Principal extends AppCompatActivity {

    private ImageButton btnMenu;
    private FloatingActionButton fabQrCode;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LocationViewModel locationViewModel;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
                } else {
                    String scannedData = result.getContents();
                    locationViewModel.setLocation(scannedData);
                    Toast.makeText(this, "Localização atualizada para: " + scannedData, Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ViewModel
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        // Initialize views
        btnMenu = findViewById(R.id.btn_menu);
        fabQrCode = findViewById(R.id.fab_qr_code);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // Set up ViewPager and Adapter
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Link TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Previsão");
            } else {
                tab.setText("Mapa");
            }
        }).attach();

        // Set click listeners
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(Principal.this, Sobre.class);
            startActivity(intent);
        });

        fabQrCode.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Aponte para o QR Code");
            options.setBeepEnabled(true);
            options.setOrientationLocked(false);
            barcodeLauncher.launch(options);
        });
    }
}