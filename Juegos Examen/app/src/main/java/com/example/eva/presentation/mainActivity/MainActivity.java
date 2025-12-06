package com.example.eva.presentation.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.presentation.libros.LibroActivity;
import com.example.eva.presentation.pasillo.PasilloActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnPasillo, btnLibros;

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

        btnLibros = findViewById(R.id.btnLibros);
        btnPasillo = findViewById(R.id.btnPasillo);

        btnLibros.setOnClickListener(v -> {
            Intent librosIntent = new Intent(this, LibroActivity.class);
            startActivity(librosIntent);
        });

        btnPasillo.setOnClickListener(v -> {
            Intent pasilloIntent = new Intent(this, PasilloActivity.class);
            startActivity(pasilloIntent);
        });


    }

}