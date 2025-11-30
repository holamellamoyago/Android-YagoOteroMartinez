package com.example.eva.data.openF1;

import android.content.Context;

import com.example.eva.controller.GestorDatabase;
import com.example.eva.domain.model.Piloto;

import java.util.ArrayList;

public class HiloConector extends Thread {
    private Context context;

    public HiloConector(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        final GestorOpenF1 gestorOpenF1 = new GestorOpenF1();
        final GestorDatabase gestorDatabase = new GestorDatabase(context);

        gestorDatabase.setPilotos(gestorOpenF1.getPilotos());
    }
}
