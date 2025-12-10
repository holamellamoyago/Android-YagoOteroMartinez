package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private MainController controller;
    private FrgBuscador frgBuscador;


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
        controller = new MainController(getApplicationContext());

        iniciarLista();
        iniciarfragments();
        controller.anadirPista();


    }

    private void iniciarLista() {
        controller.setLv_ayudas(findViewById(R.id.lv_ayudas));

        controller.getLv_ayudas()
                .setAdapter(controller.getAdapterArray());
    }

    private void iniciarfragments() {
        FragmentManager frgManager = getSupportFragmentManager();

        frgBuscador = (FrgBuscador) frgManager.findFragmentById(R.id.frgBuscador);
        frgBuscador.setListener(new FrgBuscador.OnFrgBuscador() {
            @Override
            public void onMarcaSeleccionada(FrgBuscador frgBuscador, String marca) {
                if (marca.equals("Selecciona la marca")) return;

                if (!controller.comprobarMarca(marca)) {
                    restarVida();
                } else {
                    Utils.mostrarToast(getApplicationContext(), "Acertaste la marca");
                    frgBuscador.cambiarSpinner();
                }
            }

            @Override
            public boolean onModeloSeleccionada(FrgBuscador fragment, String modelo) {
                if (modelo.equals("Selecicona el coche")) return false;


                if (!controller.comprobarModelo(modelo)){
                    restarVida();
                } else {
                    Utils.mostrarToast(getApplicationContext(), "Acertaste el modelo");
                    return true;
                }

                return false;
            }

            @Override
            public void onPistaSolicitada(FrgBuscador fragment) {
                controller.anadirPista();
            }
        });


    }

    private void restarVida() {
        if (frgBuscador.restarVida()) {
            // Si esta funcióin devuelve true , significa que el contador de vidas llegó  a 0
            return;
        }

        controller.anadirPista();
        Utils.mostrarToast(getApplicationContext(), "Vaya... no acertaste...");
    }

}


























