package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private final int NUM_DADOS = 1;
    private final String TITULO = "Rondas: ";

    private int contadorRondas = 0;


    private FrgDado[] fragments = new FrgDado[2];
    private TextView tv;

    int[] ids = {
            R.id.frgDado0,
            R.id.frgDado01,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.tv);
        setTextoTitle(TITULO + String.valueOf(contadorRondas));
        iniciarFragments();


    }

    private void iniciarFragments() {
        FragmentManager fm = getSupportFragmentManager();
        final int NUM_CARAS_DADO = 6;

        for (int i = 0; i < fragments.length; i++) {
            FrgDado frgDado = (FrgDado) fm.findFragmentById(ids[i]);

            frgDado.setListener(new FrgDado.onFrgDadoListener() {
                @Override
                public void onDadoTirado(FrgDado frgDado, int numero) {
                    frgDado.setTextoBoton(String.valueOf(numero));
                }
            });

            frgDado.setDado(new Dado(ids[i], NUM_CARAS_DADO));
            fragments[i] = frgDado;
        }
    }

    private void setTextoTitle(String texto) {
        tv.setText(texto);
    }

}