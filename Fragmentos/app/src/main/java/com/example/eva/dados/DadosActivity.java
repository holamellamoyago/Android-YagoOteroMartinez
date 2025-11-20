package com.example.eva.dados;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.R;

import java.util.ArrayList;

public class DadosActivity extends AppCompatActivity {
    final int NUM_DADOS = 2;
    FrgDados frgDado;
    ArrayList<FrgDados> fragmentsDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //iniciarFragmentos();
        iniciarFragment();

    }

    private void iniciarFragment() {
        FragmentManager fm = getSupportFragmentManager();
        frgDado = (FrgDados) fm.findFragmentById(R.id.frgDado);
    }
}

