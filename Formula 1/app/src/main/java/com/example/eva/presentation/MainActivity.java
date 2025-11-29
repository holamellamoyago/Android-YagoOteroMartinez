package com.example.eva.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.R;
import com.example.eva.data.database.AppDB;
import com.example.eva.domain.model.Piloto;
import com.example.eva.thread.Carrera;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public final static int NUMERO_VUELTAS = 10;


    public static FrgPilotos frgPilotos;
    private Carrera carrera;
    private ExtendedFloatingActionButton fab;

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

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> empezarCarrera());

        FragmentManager fm = getSupportFragmentManager();
        frgPilotos = (FrgPilotos) fm.findFragmentById(R.id.frgPilotos);
        frgPilotos.setListener(new FrgPilotos.onPilotosListener() {
            @Override
            public void onPosicionCambiada(FrgPilotos frgPilotos) {
                frgPilotos.getPilotos();
            }

            @Override
            public void onCarreraTerminada(FrgPilotos frgPiloto) {

            }
        });

        carrera = new Carrera(getApplicationContext());
        carrera.start();

    }

    private void empezarCarrera() {
        synchronized (carrera) {
            carrera.notify();
        }
    }

    public static void cambiarPosiciones() { // 2º Se llama a la función del fragment
        frgPilotos.listener.onPosicionCambiada(frgPilotos);
    }

}