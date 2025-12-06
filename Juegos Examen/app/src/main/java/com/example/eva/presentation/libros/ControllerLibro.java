package com.example.eva.presentation.libros;

import android.content.Context;

import com.example.eva.FlechasFragment;
import com.example.eva.database.AppDB;
import com.example.eva.database.ControllerDatabase;

public class ControllerLibro {
    private Context context;
    private FlechasFragment frgFlechas;
    private ControllerDatabase controllerDatabase;

    // Fila y columna
    private int f = 0, c = 0;

    public ControllerLibro(Context context, FlechasFragment frgFlechas) {
        this.context = context;
        this.frgFlechas = frgFlechas;
        this.controllerDatabase = new ControllerDatabase(new AppDB(context).getWritableDatabase());
    }

    private void comprobarPosiciones() {
        if (f == 0) {
            frgFlechas.getBtnIzquierda().setEnabled(false);
        }
    }
}
