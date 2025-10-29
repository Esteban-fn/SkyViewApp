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

public class Previsao extends AppCompatActivity {

    private ImageButton btnMenu;
    private Button btnPrevisao;
    private Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_previsao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        btnMenu = findViewById(R.id.btn_menu);
        btnPrevisao = findViewById(R.id.btn_previsao); // This is the "Retornar" button
        btnMapa = findViewById(R.id.btn_mapa);

        // Set click listeners
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Previsao.this, Sobre.class);
                startActivity(intent);
            }
        });

        btnPrevisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity and go back to the previous one
                finish();
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Mapa activity
                Intent intent = new Intent(Previsao.this, Mapa.class);
                startActivity(intent);
            }
        });
    }
}