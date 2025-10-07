package com.example.eva.domain.model.usecase;

import com.example.eva.domain.model.Mision;

import java.util.Random;

public class GeneradorMisionThread extends Thread {
    Mision mision;

    Random rdm = new Random();
    boolean apagado = false;

    @Override
    public void run() {
        generarMision();

        super.run();
    }

    private synchronized void generarMision(){
        int numRdm = rdm.nextInt(5000) + 3001;

        while (!apagado) {
            try {
                wait(numRdm);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Hola");
        }
    }
}
