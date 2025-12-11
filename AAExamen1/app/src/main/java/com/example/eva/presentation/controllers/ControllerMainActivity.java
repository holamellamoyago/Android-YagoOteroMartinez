package com.example.eva.presentation.controllers;

import android.content.Context;

import com.example.eva.clases.Canal;
import com.example.eva.database.ControllerDatabase;

import java.util.ArrayList;

public class ControllerMainActivity {
    public static ArrayList<Canal> canalesFijos = new ArrayList<>();
    private ControllerDatabase controllerDatabase;

    public ControllerMainActivity(Context context) {
        this.controllerDatabase = new ControllerDatabase(context);
    }


    public ArrayList<String> getCanalesSuscritos() {
        // TODO Mejorar: hacer remove
        StringBuilder sb = new StringBuilder();
        ArrayList<String> canalesInformacion = new ArrayList<>();

        ArrayList<Canal> canalesSuscritos = new ArrayList<>();
        ArrayList<Canal> canales = controllerDatabase.getCanales();

        for (Canal c : canales) {
            if (c.isSuscrito()) {
                canalesSuscritos.add(c);
            }
        }

        for (Canal c : canalesSuscritos) {
            canalesInformacion.add(c.toStringDetallado());
        }

        return canalesInformacion;
    }

    public ArrayList<Canal> getCanales() {
        if (canalesFijos.isEmpty()) {
            canalesFijos = controllerDatabase.getCanales();
        }

        return canalesFijos;
    }

    public ControllerDatabase getControllerDatabase() {
        return controllerDatabase;
    }

}
