package com.example.eva.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

public class ControllerDatabase {
    private SQLiteDatabase db;
    private Map<String, Integer> iMaximos = new HashMap<>();

    public ControllerDatabase(SQLiteDatabase db) {
        this.db = db;
        obtenerIndicesMaximos();
    }

    private void obtenerIndicesMaximos() {
        iMaximos = new HashMap<>();

        final String FILAS_TOTALES = "SELECT COUNT(*) FROM libros";
        System.out.println("Yago: " + FILAS_TOTALES);
    }


}
