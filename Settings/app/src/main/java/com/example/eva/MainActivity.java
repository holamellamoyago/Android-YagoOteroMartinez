package com.example.eva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etName, etEmail, etEdad;
    public SharedPreferences prefs;

    Button btnUpdate;
    CheckBox chkPublicidad;


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

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etEdad = findViewById(R.id.etEdad);
        btnUpdate = findViewById(R.id.btnUpdate);
        chkPublicidad = findViewById(R.id.chkPublicidad);

        prefs = getSharedPreferences("preferencias", 0);

        readPrefs();

        btnUpdate.setOnClickListener(view -> writePrefs());

    }

    private void readPrefs() {
        etName.setText(prefs.getString("nombre", ""));
        etEmail.setText(prefs.getString("email", ""));
        etEdad.setText(prefs.getString("edad", ""));
        chkPublicidad.setChecked(prefs.getBoolean("publicidad", false));

        prefs.getBoolean("publicidad", false);

        Toast.makeText(this, "Cargar prefs", Toast.LENGTH_SHORT).show();


    }

    private boolean writePrefs() {
        SharedPreferences.Editor editor = prefs.edit();

        if (etName.getText().toString().equals("")) {
            return false;
        }

        if (!etEdad.getText().toString().equals("")) {
            if (Integer.parseInt(etEdad.getText().toString()) < 0) {
                return false;
            }
        }

        editor.putString("nombre", etName.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("edad", etEdad.getText().toString());
        editor.putBoolean("publicidad", chkPublicidad.isChecked());
        editor.apply();

        Toast.makeText(this, "update prefs", Toast.LENGTH_SHORT).show();


        return true;


    }

}