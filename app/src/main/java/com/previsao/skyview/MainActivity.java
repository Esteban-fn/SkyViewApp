package com.previsao.skyview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Duração do splash screen em milissegundos (3 segundos)
    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Ativar o modo EdgeToEdge (tela cheia)
        EdgeToEdge.enable(this);

        // 2. Carregar o layout. Isso deve ser feito ANTES de chamar findViewById(R.id.main)
        setContentView(R.layout.activity_main);

        // 3. Configuração do Insets (padding) para evitar que o conteúdo fique atrás da barra de status/navegação.
        // O EdgeToEdge.enable(this) já faz a maior parte, mas esta linha garante que o layout principal
        // responda às áreas seguras.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4. Lógica de Navegação (Splash Screen)
        new Handler().postDelayed(() -> {
            // Cria um Intent para ir para a Principal.java
            Intent intent = new Intent(MainActivity.this, Principal.class);
            startActivity(intent);

            // Finaliza a MainActivity para que o usuário não possa voltar para ela
            finish();
        }, SPLASH_DURATION);
    }
}
