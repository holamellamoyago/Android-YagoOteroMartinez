package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edTxt;
    Button btnAdd,btnContinuar,btnAddPersonas, btnMinusPersonas;
    ListView lv;

    ArrayList<String> respuestas;

    ArrayAdapter<String> adapter;

    TextView txtContador;

    int contador = 0;


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

        respuestas = new ArrayList<>(List.of("Paella"));

         edTxt = findViewById(R.id.edTxt);
         btnAdd = findViewById(R.id.btnAdd);
         lv = findViewById(R.id.lv);
         btnContinuar = findViewById(R.id.btnContinuar);
         btnAddPersonas = findViewById(R.id.btnAddPersonas);
         btnMinusPersonas = findViewById(R.id.btnMinusPersonas);
         txtContador = findViewById(R.id.txtContador);

         txtContador.setText(String.valueOf(contador));


        ArrayAdapter<String> adapter  =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, respuestas);
        lv.setAdapter(adapter);


        btnAdd.setOnClickListener(view -> addItemsToList());
        btnAddPersonas.setOnClickListener(view -> addPersonas());
        btnMinusPersonas.setOnClickListener(view -> minusPersonas());
        btnContinuar.setOnClickListener(view -> startVoteActivity());
    }

    private void startVoteActivity() {
        Intent intent = new Intent(this, VoteActivity.class);
        intent.putExtra("listOptions", respuestas);
        intent.putExtra("contadorPersonas", contador);
        startActivity(intent);
    }


    private void addItemsToList(){
        System.out.println(edTxt.getText());
        if (!edTxt.getText().equals("")){
            respuestas.add(edTxt.getText().toString());
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Respuesta añadida", Toast.LENGTH_SHORT);
        }
    }

    private void minusPersonas() {
        if (contador>0) {contador--;};
        txtContador.setText(String.valueOf(contador));
    }

    private void addPersonas(){
        contador++;
        txtContador.setText(String.valueOf(contador));
    }



}