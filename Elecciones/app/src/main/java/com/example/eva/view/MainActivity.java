package com.example.eva.view;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.controller.DatabaseController;
import com.example.eva.model.Votante;

public class MainActivity extends AppCompatActivity {

    private EditText etNIF;
    private EditText etPassword;

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

        iniciarWidgets();

        DatabaseController dbController = new DatabaseController(this);
        dbController.openDatabase();


    }

    private void loginIN(){
        if (etNIF.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            System.out.println("No puede añadirse un usuario vacio");
            return;
        }

        //Votante v = new Votante()
    }

    private void iniciarWidgets(){
        etNIF = findViewById(R.id.etNIF);
        etPassword = findViewById(R.id.etPassword);
    }



}