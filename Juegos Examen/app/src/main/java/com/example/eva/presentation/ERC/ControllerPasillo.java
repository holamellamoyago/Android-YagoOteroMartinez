package com.example.eva.presentation.ERC;

import android.content.Context;

import com.example.eva.FlechasFragment;

public class ControllerPasillo {
    private Context context;
    private FlechasFragment frgFlechas;


    public ControllerPasillo(Context context, FlechasFragment frgFlechas) {
        this.context = context;
        this.frgFlechas = frgFlechas;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public FlechasFragment getFrgFlechas() {
        return frgFlechas;
    }

    public void setFrgFlechas(FlechasFragment frgFlechas) {
        this.frgFlechas = frgFlechas;
    }
}
