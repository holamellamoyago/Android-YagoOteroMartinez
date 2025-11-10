package com.example.eva.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Databasecontroller {
    private static SQLiteDatabase db;

    public static ArrayList<String> getPalabras(Context context) {
        if (db == null) abrirDB(context);

        ArrayList<String> palabrasBuscar = new ArrayList<>();
        String palabras_sql = "SELECT codPalabra, palabra FROM palabras";

        Cursor cursor = db.rawQuery(palabras_sql, null);
        if (cursor.moveToFirst()) {
            String palabra = cursor.getString(1);
            System.out.println("Palabras: " + palabra);

            palabrasBuscar.add(palabra.toLowerCase());
        };

        return palabrasBuscar;
    };

    private static void abrirDB(Context context) {
        db = new AppDB(context).getWritableDatabase();
    }
}
