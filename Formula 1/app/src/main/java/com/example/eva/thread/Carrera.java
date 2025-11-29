package com.example.eva.thread;

import android.content.Context;

import com.example.eva.controller.ControllerDatabaser;
import com.example.eva.domain.model.Piloto;
import com.example.eva.presentation.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class Carrera extends Thread {
    private Context context;
    private ControllerDatabaser controllerDatabaser;

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

        controllerDatabaser = new ControllerDatabaser(context);
        ArrayList<Piloto> pilotos = controllerDatabaser.getPilotos();

        for (int j = 0; j < MainActivity.NUMERO_VUELTAS; j++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            // 1º Guardamos su posicion anterior
            pilotos.get(0).setPosicionAnterior(pilotos.get(0).getPosicionActual());
            pilotos.get(1).setPosicionAnterior(pilotos.get(1).getPosicionActual());
            pilotos.get(2).setPosicionAnterior(pilotos.get(2).getPosicionActual());

            int n = new Random().nextInt(10) + 1;
            int comodin;
            if (n < 3) {
                comodin = pilotos.get(0).getPosicionActual();


                // 2º Actualizamos la actual
                pilotos.get(0).setPosicionActual(pilotos.get(1).getPosicionActual());


                // 4º Actualizamos la nueva
                pilotos.get(1).setPosicionActual(comodin);
            } else if (n > 7) {
                comodin = pilotos.get(1).getPosicionActual();

                // Nueva
                pilotos.get(1).setPosicionActual(pilotos.get(2).getPosicionActual());

                // Nueva
                pilotos.get(2).setPosicionActual(comodin);
            } else {
                // No se cambian las posiciones de nadie
            }

            controllerDatabaser.setPilotos(pilotos); // 1º Se actualizan los pilotos
            MainActivity.frgPilotos.getActivity().runOnUiThread(() -> MainActivity.cambiarPosiciones());
        }

    }
}
