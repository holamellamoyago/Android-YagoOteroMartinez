package com.example.eva.presentation.perfil;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.R;
import com.example.eva.domain.model.Piloto;
import com.example.eva.presentation.main.FrgPilotos;
import com.example.eva.presentation.resultado.FrgPerfil;

import java.util.IllegalFormatCodePointException;

public class PerfilActivity extends AppCompatActivity {
    private FrgPerfil frgPerfil;
    private Piloto piloto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            piloto = (Piloto) bundle.get("piloto");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        frgPerfil = (FrgPerfil) fragmentManager.findFragmentById(R.id.frgPerfil);
        frgPerfil.setPiloto(piloto);

    }
}