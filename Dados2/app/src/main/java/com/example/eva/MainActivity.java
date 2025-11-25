package com.example.eva;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final int NUM_DADOS = 1;

    int[] ids = {
            R.id.frgDado0,
            R.id.frgDado01,
    };

    private static int contadorRondas = 0;
    private static Map<Integer, ArrayList<Integer>> historial = new HashMap<>();

    private FrgDado[] fragments = new FrgDado[2];
    private TextView tv;


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

        tv = findViewById(R.id.tv);
        actualizarTitulo(contadorRondas);
        iniciarFragments();


    }

    private void iniciarFragments() {
        FragmentManager fm = getSupportFragmentManager();
        final int NUM_CARAS_DADO = 2;

        for (int i = 0; i < fragments.length; i++) {
            FrgDado frgDado = (FrgDado) fm.findFragmentById(ids[i]);

            frgDado.setListener(new FrgDado.onFrgDadoListener() {
                @Override
                public void onDadoTirado(FrgDado frgDado, int numero) {
                    frgDado.setTextoBoton(String.valueOf(numero));
                }

                @Override
                public void onRondaTerminada() {
                    if (comprobarResultados()){
                        ganarPartida();
                        return;
                    }

                    contadorRondas++;
                }


            });

            frgDado.setDado(new Dado(i, NUM_CARAS_DADO));
            fragments[i] = frgDado;
        }
    }

    private void ganarPartida() {
        for (int i = 0; i < fragments.length; i++) {
            fragments[i].setVisible(false);
        }

        tv.setText("Has ganado la partida en la ronda " + contadorRondas);
    }

    private void actualizarTitulo(int numeroRonda) {
        final String TITULO = "Rondas: ";
        String tituloFinal = TITULO + " " + String.valueOf(numeroRonda);
        tv.setText(tituloFinal);
    }

    private boolean comprobarResultados() {
        ArrayList<Integer> resultados = historial.get(contadorRondas);
        int numeroIgual = -1;
        for (int i = 0; i < resultados.size(); i++) {
            if (i == 0) {
                numeroIgual = resultados.get(i);
            } else {
                if (resultados.get(i) != numeroIgual) {
                    return false;
                }
            }
        }
        System.out.println("Termino la partida, numeros iguales");
        return true;

    }

    public static void anadirNumero(Integer numero) {
        if (historial.containsKey(contadorRondas)) {
            historial.get(contadorRondas).add(numero);
        } else {
            ArrayList<Integer> array = new ArrayList<>(List.of(numero));
            historial.put(contadorRondas, array);
        }

        System.out.println("Historial: \n" + historial);
    }

}