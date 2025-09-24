package com.example.eva;

import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VoteActivity extends AppCompatActivity {
    ArrayList<String> options;
    ListView lvOptions;
    ArrayAdapter<String> adapter;

    Map<String, Integer> mapOptions = new TreeMap<>();
    int contadorPersonas = 0;
    int contadorRespuestas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vote);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            options = extras.getStringArrayList("listOptions");
            contadorPersonas = extras.getInt("contadorPersonas");

        }

        lvOptions = findViewById(R.id.ltvOptions);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);

        lvOptions.setAdapter(adapter);

        lvOptions.setOnItemClickListener((adapterView, view, i, l) -> anhadirConteo(adapter.getItem(i)));

    }

    private void anhadirConteo(String option) {
        if (mapOptions.containsKey(option)) {
            int valor = mapOptions.get(option);
            mapOptions.put(option, valor++);
        }

        comprobarPersonas();
    }

    private void comprobarPersonas() {
        if (contadorRespuestas >= contadorPersonas) {
            Intent intentResult = new Intent(this, ResultActivity.class);
            intentResult.putExtra("ganador", calcularGanador());
            startActivity(intentResult);

        }
    }

    private String calcularGanador() {
        Set<Map.Entry<String, Integer>> set = mapOptions.entrySet();
        Iterator<Map.Entry<String, Integer>> it = set.iterator();

        Map.Entry<String, Integer> mayor = null;

        while (it.hasNext()) {
            Map.Entry<String, Integer> i = it.next();

            if (mayor == null) {
                mayor = i;
            } else {
                if (i.getValue() > mayor.getValue()) {
                    mayor = i;
                }
            }


        }

        return mayor.getKey();
    }

}