package com.example.eva.view;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.controller.*;
import com.example.eva.model.Candidato;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {
    TextView txtResultado;
    DatabaseController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initWidgets();

        getGanadores();

    }

    private void getGanadores() {
        ArrayList<String> ganadores =  controller.cargarGanadores();

        if (ganadores.size() > 1 ){
            txtResultado.append("Empate, " + ganadores.toString());
        } else {
            txtResultado.append(ganadores.get(0));
        }
    }

    private void initWidgets() {
        txtResultado = findViewById(R.id.txtResultado);
        controller = new DatabaseController(this);
    }
}