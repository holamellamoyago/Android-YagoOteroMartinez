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

        ArrayList<String> respuestas = new ArrayList<>(List.of("Paella"));

        EditText edTxt = findViewById(R.id.edTxt);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView lv = findViewById(R.id.lv);

        ArrayAdapter<String> adapter  =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, respuestas);
        lv.setAdapter(adapter);


        btnAdd.setOnClickListener(view -> {
            System.out.println(edTxt.getText());
            if (!edTxt.getText().equals("")){
                respuestas.add(edTxt.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Respuesta añadida", Toast.LENGTH_SHORT);
            }
        });






    }



}