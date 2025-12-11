package com.example.eva.presentation.controllers;

import android.content.Context;

import com.example.eva.clases.Canal;
import com.example.eva.database.ControllerDatabase;

import java.util.ArrayList;

public class ControllerFrgTelevision {
    private Context context;

    public ControllerFrgTelevision(Context context) {
        this.context = context;
    }

    public ArrayList<Canal> getCanales(ControllerDatabase controllerDatabase) {
        return controllerDatabase.getCanales();
    }
}
