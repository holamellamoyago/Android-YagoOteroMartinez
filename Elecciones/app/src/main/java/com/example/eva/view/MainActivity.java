package com.example.eva.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



import com.example.eva.R;
import com.example.eva.controller.*;

public class MainActivity extends AppCompatActivity {

    private EditText etNIF;
    public static EditText etPassword;
    private Button btnLogIn;
    DatabaseController controller = new DatabaseController(this);

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

        btnLogIn.setOnClickListener(v -> {
            Intent votarActivity = new Intent(this, VotarActivity.class);

            if (loginIN()) {
                startActivity(votarActivity);
            }
        });

    }

    private boolean loginIN(){

        if (etNIF.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            System.out.println("No puede añadirse un usuario vacio");
            return false;
        }

        return controller.checkVotante(etNIF.getText().toString(), etPassword.getText().toString());

    }

    private void iniciarWidgets(){
        etNIF = findViewById(R.id.etNIF);
        etPassword = findViewById(R.id.etPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
    }



}