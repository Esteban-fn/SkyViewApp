package com.previsao.skyview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mapa extends AppCompatActivity {

    private ImageButton btnMenu;
    private Button btnPrevisao;
    private Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        btnMenu = findViewById(R.id.btn_menu);
        btnPrevisao = findViewById(R.id.btn_previsao);
        btnRetornar = findViewById(R.id.btn_mapa); // The ID is btn_mapa in the XML

        // Set click listeners
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Sobre activity
                Intent intent = new Intent(Mapa.this, Sobre.class);
                startActivity(intent);
            }
        });

        btnPrevisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Previsao activity
                Intent intent = new Intent(Mapa.this, Previsao.class);
                startActivity(intent);
            }
        });

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity and go back to the previous one
                finish();
            }
        });
    }
}