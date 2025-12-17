package com.example.eva.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.Config;
import com.example.eva.R;
import com.example.eva.controllers.GestorServidor;

public class LoginActivity extends AppCompatActivity {

    private EditText ed_direccion, ed_puerto;
    private Button btn_conectarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed_direccion = findViewById(R.id.ed_direccion);
        ed_puerto = findViewById(R.id.ed_puerto);
        btn_conectarse = findViewById(R.id.btn_conectarse);

        ed_direccion.setText(Config.DIRECCION_PREDETERMINADA);
        ed_puerto.setText(String.valueOf(Config.PUERTO_PREDETERMINADO));

        btn_conectarse.setOnClickListener(v -> {
            String direccion = ed_direccion.getText().toString();
            int puerto =  Integer.valueOf(ed_puerto.getText().toString());

            GestorServidor.initialize(direccion, puerto);

            Intent gallery = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(gallery);
        });
    }
}