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

    public ControllerLibro(Context context) {
        this.context = context;
        this.controllerDatabase = new ControllerDatabase(new AppDB(context).getWritableDatabase());
    }

    private void comprobarPosiciones() {
        if (f == 0) {
            frgFlechas.getBtnIzquierda().setEnabled(false);
        }
    }

    public String buscarLibro(int fila, int columna) {
        String f = String.valueOf(fila);
        String c = String.valueOf(columna);
        return controllerDatabase.buscarLibro(f,c);
    }

}
