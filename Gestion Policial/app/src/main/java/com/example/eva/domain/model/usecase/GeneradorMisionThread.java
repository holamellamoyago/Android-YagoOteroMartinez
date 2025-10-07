package com.example.eva.domain.model.usecase;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModelProvider;

import com.example.eva.domain.model.Mision;
import com.example.eva.ui.viewmodel.MainActivityViewModel;

import java.util.Random;

public class GeneradorMisionThread extends Thread {
    Mision mision;

    Random rdm = new Random();
    boolean apagado = false;
    int contador = 0;

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
                mision = new Mision("Accidente nº" + contador);
                contador++;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Hola");
        }
    }
}
