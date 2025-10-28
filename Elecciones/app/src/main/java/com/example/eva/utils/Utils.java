package com.example.eva.utils;

import android.content.Context;
import android.graphics.Color;

import com.example.eva.config.DatabaseConstants;
import com.example.eva.model.Partido;

import java.util.ArrayList;

public class Utils {

    public static int buscarColorPartido(int codPartido) {
        ArrayList<Partido> partidos = DatabaseConstants.partidos;
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);

            if (p.getPartidoID() == codPartido){
                return p.getColor();
            }

        }

        return Color.parseColor("orange");
    }
}
