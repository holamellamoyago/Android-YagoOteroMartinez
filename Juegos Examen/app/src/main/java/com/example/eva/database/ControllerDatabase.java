package com.example.eva.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.Ruta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerDatabase {
    private static SQLiteDatabase db;
    private ContentValues cv = new ContentValues();

    private Map<String, Integer> iMaximos = new HashMap<>();

    public ControllerDatabase(SQLiteDatabase db) {
        this.db = db;
        obtenerIndicesMaximos();
    }

    // ERC
    public void agregarRuta(String nombre, String ruta) {
        cv.clear();
        cv.put("nombre", nombre);
        cv.put("ruta", ruta);
        db.insert("rutas", null, cv);
    }

    public static ArrayList<Ruta> obtenerRutas() {
        final String SQL = "SELECT nombre, ruta FROM RUTAS";
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        ArrayList<Ruta> rutas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String nombre, ruta;
                nombre = cursor.getString(0);
                ruta = cursor.getString(1);

                Ruta r = new Ruta(nombre, ruta);
                rutas.add(r);
            } while (cursor.moveToNext());

        }

        return rutas;
    }

    private void obtenerIndicesMaximos() {
        iMaximos = new HashMap<>();

        final String FILAS_TOTALES = "SELECT COUNT(*) FROM libros";
        System.out.println("Yago: " + FILAS_TOTALES);
    }

    public String buscarLibro(String f, String c) {
        Cursor cursor = db.rawQuery("SELECT nombre FROM libros WHERE fila = ? AND columna = ?", new String[]{f, c});

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }

        return null;
    }


}
