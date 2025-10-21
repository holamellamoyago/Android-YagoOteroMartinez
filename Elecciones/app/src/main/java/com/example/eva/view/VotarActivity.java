package com.example.eva.view;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.controller.DatabaseController;
import com.example.eva.model.Candidato;

public class VotarActivity extends AppCompatActivity {

    DatabaseController dbC;
    ListView lvCandidatos;

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

        //lvCandidatos.setOnItemClickListener((adapterView, view, i, l) ->  );
    }

    private void initWidgets(){
        dbC = new DatabaseController(this);
        lvCandidatos = findViewById(R.id.lvCandidatos);
    }

    private void poblarLista(){
        ListAdapter arrayAdapter = new ArrayAdapter<Candidato>(this, android.R.layout.simple_list_item_1, dbC.getCandidatos() );
         lvCandidatos.setAdapter(arrayAdapter);
    }
}