package com.example.eva.presentation.pasillo;

import android.content.Context;

import com.example.eva.FlechasFragment;
import com.example.eva.database.AppDB;
import com.example.eva.database.ControllerDatabase;

public class ControllerPasillo {
    private Context context;
    private FlechasFragment frgFlechas;

    public ControllerPasillo(Context context, FlechasFragment frgFlechas) {
        this.context = context;
        this.frgFlechas = frgFlechas;
    }
}
