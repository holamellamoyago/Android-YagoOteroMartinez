package com.example.eva.thread;

import android.content.Context;

import com.example.eva.controller.GestorDatabase;
import com.example.eva.domain.model.Piloto;
import com.example.eva.presentation.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class Carrera extends Thread {
    private Context context;
    private GestorDatabase gestorDatabase;

    public Carrera(Context context) {
        this.context = context;
    }

    @Override
    public void run() {

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        gestorDatabase = new GestorDatabase(context);
        ArrayList<Piloto> pilotos = gestorDatabase.getPilotos();

        for (int j = 0; j < MainActivity.NUMERO_VUELTAS; j++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            // 1º Guardamos su posicion anterior
            for (int i = 0; i < pilotos.size(); i++) {
                Piloto p = pilotos.get(i);

                if (p.getPosicionActual() == null) {
                    p.setPosicionAnterior(i);
                } else {
                    p.setPosicionAnterior(p.getPosicionActual());
                }
            }

            // 2º Cambiamos el orden
            // Mi idea es coger siempre el anteior (a no ser de que i == 0) y en base a eso intentar cambiar el orden
            int rdm;
            int comodin;
            for (int i = 1; i < pilotos.size(); i++) {
                rdm  = new Random().nextInt(10) + 1;
                if (rdm < 3) {
                    comodin = pilotos.get(i).getPosicionActual();


                    // 2º Actualizamos la actual
                    pilotos.get(i).setPosicionActual(pilotos.get(i - 1).getPosicionActual());


                    // 4º Actualizamos la nueva
                    pilotos.get(i -1 ).setPosicionActual(comodin);
                }
            }

//            else if (rdm > 7) {
//                comodin = pilotos.get(1).getPosicionActual();
//
//                // Nueva
//                pilotos.get(1).setPosicionActual(pilotos.get(2).getPosicionActual());
//
//                // Nueva
//                pilotos.get(2).setPosicionActual(comodin);
//            } else {
//                // No se cambian las posiciones de nadie
//            }

            gestorDatabase.setPilotos(pilotos); // 1º Se actualizan los pilotos
            MainActivity.frgPilotos.getActivity().runOnUiThread(() -> MainActivity.cambiarPosiciones());
        }

    }
}
