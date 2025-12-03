package com.example.eva.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.clases.Alerta;

import java.util.ArrayList;

public class GestorDatabase {
    private SQLiteDatabase db;
    private ContentValues cv = new ContentValues();

    public GestorDatabase(SQLiteDatabase db) {
        this.db = db;
    }

    public void anadirAlerta(Alerta alerta) {
        cv.clear();
        cv.put("token", alerta.getToken());
        cv.put("contexto", alerta.getContexto());
        cv.put("control", alerta.getControl());

        long ref = db.insert("alertas", null, cv);
    }

    public ArrayList<Alerta> getAlertas() {
        ArrayList<Alerta> alertas = new ArrayList<>();

        final String ALERTAS_SQL = "SELECT token, contexto, control FROM alertas";
        Cursor cursor = db.rawQuery(ALERTAS_SQL, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                String token = cursor.getString(0);
                String contexto = cursor.getString(1);
                String control = cursor.getString(2);

                Alerta a = new Alerta(token, contexto, control);
                alertas.add(a);
            } while (cursor.moveToNext());
        }

        return alertas;
    }
}
