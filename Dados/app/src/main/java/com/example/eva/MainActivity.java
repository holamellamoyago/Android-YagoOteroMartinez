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
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FrgDados frgDado;
    private Button btnPlay;
    private Random rdm = new Random();

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

        initFragments();
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frgDado.listener.onTirar(frgDado, rdm.nextInt(33));
            }
        });



    }

    private void initFragments() {
        FragmentManager fm = getSupportFragmentManager();
        frgDado = (FrgDados) fm.findFragmentById(R.id.frgDado);
        frgDado.setOnFrgDatosListener(new FrgDados.onFrgDatosListener() {
            @Override
            public void onTirar(FrgDados dado, int numero) {
                Toast.makeText(getApplicationContext(), "Salió el num " + numero, Toast.LENGTH_SHORT).show();
                dado.setVisibility();
                dado.setTextButton(String.valueOf(numero));
            }
        });
    }

}