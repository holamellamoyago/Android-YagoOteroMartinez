package com.example.eva.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.Utils;
import com.example.eva.clases.Canal;
import com.example.eva.presentation.controllers.ControllerMainActivity;
import com.example.eva.presentation.fragments.FrgTelevision;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ControllerMainActivity controller;

    private ListView lv_registro;
    private ArrayAdapter<String> registroAdapter;
    private Button btn_resetear;

    private ArrayList<FrgTelevision> fragments = new ArrayList<>();
    private FrgTelevision frg_television0;
    private FrgTelevision frg_television1;
    private FrgTelevision frg_television2;

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
        // controller y button
        controller = new ControllerMainActivity(getApplicationContext());
        btn_resetear = findViewById(R.id.btn_resetear);

        // Inicio lista
        registroAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, controller.getCanalesSuscritos());
        lv_registro = findViewById(R.id.lv_registro);
        lv_registro.setAdapter(registroAdapter);

        // inicio fragments
        iniciarFragments();

    }

    private void iniciarFragments() {
        frg_television0 = (FrgTelevision) getSupportFragmentManager().findFragmentById(R.id.frg_television1);
        frg_television1 = (FrgTelevision) getSupportFragmentManager().findFragmentById(R.id.frg_television2);
        frg_television2 = (FrgTelevision) getSupportFragmentManager().findFragmentById(R.id.frg_television3);

        fragments.add(frg_television0);
        fragments.add(frg_television1);
        fragments.add(frg_television2);

        for (FrgTelevision frg : fragments) {
            frg.setCanales(controller.getCanales());
            frg.setListener(new FrgTelevision.onFrgTelevisionListener() {
                @Override
                public void OnEmpezarMirarCanal(FrgTelevision frg, Canal canal) {
                    if (!canal.isSuscrito()) {
                        Utils.mostrarMensaje(getApplicationContext(), "No estás suscrito");
                        frg.hacerVisibleBoton();
                        frg.actualizarTexto("No estás suscrito a este canal");
                        frg.hacerInvisibleBotonMirar();
                        return;
                    }

                    boolean disponible =  canal.comprobarDisponible(getApplicationContext(), frg);

                    if (disponible) {
                        frg.actualizarTexto("Estás mirando el canal " + canal.getNombre());
                    } else {
                        frg.actualizarTexto("Apague otra TV");
                    }

                    frg.hacerInVisibleBoton();
                    frg.hacervisibleBotonMirar();

                }

                @Override
                public void OnSuscribirseCanal(FrgTelevision frg, Canal canal) {
                    // Iniciar activity
                    Intent suscribirseIntent = new Intent(getApplicationContext(), SuscribirseActivity.class);
                    suscribirseIntent.putExtra("canal", canal);
                    startActivity(suscribirseIntent);
                }
            });
        }
    }

}