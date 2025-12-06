package com.example.eva;

import android.widget.ArrayAdapter;

import com.example.eva.clases.Alerta;
import com.example.eva.database.GestorDatabase;
import com.example.eva.presentation.mainActivity.MainActivity;

import java.util.ArrayList;

public class GestorAlertas {
    public static ArrayList<Alerta> alertas = new ArrayList<>();
    public static ArrayAdapter<Alerta> alertaArrayAdapter;


    public static void getAlertasFromDatabase(ArrayList<Alerta> alertas) {
        GestorAlertas.alertas.clear();
        GestorAlertas.alertas.addAll(alertas);
        MainActivity.reiniciarList();
    }

    public static void limpiarAlertas(GestorDatabase gestorDatabase) {
        alertas.clear();
        gestorDatabase.limpiarAlertas();
        MainActivity.reiniciarList();

    }
}
