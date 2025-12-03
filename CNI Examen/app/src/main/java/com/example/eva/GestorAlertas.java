package com.example.eva;

import com.example.eva.clases.Alerta;
import com.example.eva.database.GestorDatabase;

import java.util.ArrayList;

public class GestorAlertas {
    public static ArrayList<Alerta> alertas = new ArrayList<>();

    public static void getAlertasFromDatabase(ArrayList<Alerta> alertas) {
        GestorAlertas.alertas.clear();
        GestorAlertas.alertas.addAll(alertas);
        MainActivity.reiniciarList();

    }
}
