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
import com.example.eva.controller.*;
import com.example.eva.model.Candidato;
import com.example.eva.model.Votante;

import java.util.ArrayList;

public class VotarActivity extends AppCompatActivity {

    DatabaseController dbC;
    ListView lvCandidatos;
    String nifVotante = "Error";


    ArrayAdapter<Candidato> candidatoAdapter;
    ArrayList<Candidato> candidatosVotados = new ArrayList<>();

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
        lvCandidatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void registrarVotos(int i) {
        Candidato candidato = candidatoAdapter.getItem(i);
        dbC.registrarVotos(String.valueOf(candidato.getCandidatoID()));


        candidatosVotados.add(candidato);
        Toast.makeText(this, "Se registro el voto a: " + candidato, Toast.LENGTH_SHORT).show();
        candidatoAdapter.remove(candidato);

        if (candidatosVotados.size() == 3) {
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
        candidatoAdapter = new ArrayAdapter<Candidato>(this, android.R.layout.simple_list_item_1, dbC.getCandidatos());
        lvCandidatos.setAdapter(candidatoAdapter);
    }
}