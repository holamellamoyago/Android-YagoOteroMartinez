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

public class MainActivity extends AppCompatActivity {

    private static final int MAX_INTENTOS = 3;
    EditText etUsername, etPassword;
    FrgBotonLimitado btnEntrar;

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
        initWigets();



    }

    private void initWigets() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        FragmentManager fm =  getSupportFragmentManager();

        btnEntrar = (FrgBotonLimitado) fm.findFragmentById(R.id.btnEntrar);

        btnEntrar.setMaxClicks(MAX_INTENTOS);
        btnEntrar.setOnFrgBotonLimitadoListener(new FrgBotonLimitado.OnFrgBLClickListener() {
            @Override
            public boolean onClick() {
                return entrar();
            }
        });
    }

    private boolean entrar() {
        String usuario = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (usuario.equals("")  || password.equals("")){
            btnEntrar.deshacerClick();
            Toast.makeText(this, "No cuenta", Toast.LENGTH_SHORT).show();
            return false;

        }


        if (usuario.equals("yago") && password.equals("123")){
            Toast.makeText(this, "Logueado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "login falliddo", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}