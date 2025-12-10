package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainController controller;
    private ListView lv_ayudas;
    private TextView tv_vidas;
    private FrgBuscador frgBuscador;
    private ArrayList<String> pistas = new ArrayList<>();

    private ArrayAdapter<String> adapterAyuda;

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

        controller = new MainController();
        lv_ayudas = findViewById(R.id.lv_ayudas);

        adapterAyuda = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, pistas);
        lv_ayudas.setAdapter(adapterAyuda);
        anadirPista();

        frgBuscador = (FrgBuscador) getSupportFragmentManager().findFragmentById(R.id.frgBuscador);
        frgBuscador.setListener(new FrgBuscador.OnFrgBuscador() {
            @Override
            public boolean onMarcaSeleccionada(FrgBuscador fragment, String marca) {
                if (!controller.comprobarMarca(marca)) {
                    int vidasRestantes = Integer.valueOf(tv_vidas.getText().toString());

                    if (controller.comprobarVidas(vidasRestantes)) {
                        actualizarContador(controller.restarVidas(vidasRestantes));
                        anadirPista();
                    } else {
                        ensenarResultados(false);
                    }

                    return false;
                }

                adapterAyuda.clear();
                return true;
            }

            @Override
            public boolean onModeloSeleccionada(FrgBuscador fragment, String modelo) {
                if (!controller.comprobarModelo(modelo)) {
                    int vidasRestantes = Integer.valueOf(tv_vidas.getText().toString());

                    if (controller.comprobarVidas(vidasRestantes)) {
                        actualizarContador(controller.restarVidas(vidasRestantes));
                        anadirPista();
                    } else {
                        ensenarResultados(false);
                    }

                    return false;
                }

                ensenarResultados(true);
                return true;
            }

        });


        tv_vidas = findViewById(R.id.tv_vidas);
        tv_vidas.setText("3");
    }


    public void actualizarContador(String n) {
        if (n != null) tv_vidas.setText(n);
    }

    private void anadirPista() {
        String pista = controller.cogerSiguientePista(lv_ayudas.getAdapter().getCount());
        if (pista != null) {
            adapterAyuda.add(pista);
        } else {
            Utils.mostrarToast(this, "No quedan más pistas");
        }
    }

    public void ensenarResultados(boolean gano) {
        Intent resultadoActivity = new Intent(this, ResultadoActivity.class);

        if (gano) {
            resultadoActivity.putExtra("resultado", "Ganaste la partida!");
        } else {
            resultadoActivity.putExtra("resultado", "Pêrdiste la partida :(");
        }

        startActivity(resultadoActivity);
    }

}