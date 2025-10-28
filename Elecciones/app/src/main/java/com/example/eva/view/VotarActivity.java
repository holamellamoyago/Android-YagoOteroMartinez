package com.example.eva.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.adapter.AdapterCandidato;
import com.example.eva.config.DatabaseConstants;
import com.example.eva.controller.*;
import com.example.eva.model.Candidato;
import com.example.eva.model.Votante;

import java.util.ArrayList;

public class VotarActivity extends AppCompatActivity {

    private final int NUM_MAX_VOTOS = 3;
    private DatabaseController dbC;
    private ListView lvCandidatos;
    private String nifVotante = "Error";


    ArrayList<Candidato> candidatosVotados = new ArrayList<>();

    AdapterCandidato newCandidatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_votar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initWidgets();
        poblarLista();


        lvCandidatos.setOnItemClickListener((adapterView, view, i, l) -> registrarVotos(i));


    }

    private void registrarVotos(int i) {
        Candidato candidato = newCandidatoAdapter.getItem(i);
        dbC.registrarVotos(String.valueOf(candidato.getCandidatoID()));


        candidatosVotados.add(candidato);
        Toast.makeText(this, "Se registro el voto a: " + candidato, Toast.LENGTH_SHORT).show();
        newCandidatoAdapter.remove(candidato);

        if (candidatosVotados.size() == NUM_MAX_VOTOS) {
            dbC.terminarVotosVotante(nifVotante);

            Intent resultadoActivity = new Intent(this, ResultadoActivity.class);
            startActivity(resultadoActivity);
        }


    }

    private void initWidgets() {
        dbC = new DatabaseController(this);
        lvCandidatos = findViewById(R.id.lvCandidatos);

        try {
            Bundle extras = getIntent().getExtras();

            nifVotante = extras.getString("nifVotante");

            Toast.makeText(this, nifVotante, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new ArithmeticException("Problemas al cargar el votante a votar");
        }
    }


    private void poblarLista() {
//        candidatoAdapter = new ArrayAdapter<Candidato>(this, android.R.layout.simple_list_item_1, dbC.getCandidatos());
        DatabaseConstants constants = new DatabaseConstants(this);
        newCandidatoAdapter = new AdapterCandidato(this, DatabaseConstants.candidatos);
        lvCandidatos.setAdapter(newCandidatoAdapter);
    }
}