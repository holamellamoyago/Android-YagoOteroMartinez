package com.example.eva.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Databasecontroller {
    private static SQLiteDatabase db;

    public static ArrayList<String> getTelefonos(Context context) {
        if (db == null) abrirDB(context);

        ArrayList<String> telefonos = new ArrayList<>();
        String palabras_sql = "SELECT telefono FROM telefonos";

        Cursor cursor = db.rawQuery(palabras_sql, null);
        if (cursor.moveToFirst()) {
            do {
                String telefono = cursor.getString(0);
                System.out.println("Telefonos: " + telefono);

                telefonos.add(telefono.toLowerCase());
            } while (cursor.moveToNext());
        };

        return telefonos;
    };

    private static void abrirDB(Context context) {
        db = new AppDB(context).getWritableDatabase();
    }
}
