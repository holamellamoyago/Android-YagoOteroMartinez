package com.example.eva.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.data.database.AppDB;
import com.example.eva.domain.model.Piloto;

import java.util.ArrayList;

public class ControllerDatabaser {
    private SQLiteDatabase db;

    public ControllerDatabaser(Context context) {
        this.db = new AppDB(context).getWritableDatabase();
    }


    public ArrayList<Piloto> getPilotos() {
        final String sql_pilotos = "SELECT numPiloto, nombre, posicion, posicionAnterior FROM pilotos ORDER BY posicion";

        ArrayList<Piloto> pilotos = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql_pilotos, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                int numero = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int posicionActual = cursor.getInt(2);

                int posicionAnterior;
                if (!cursor.isNull(3)) {
                    posicionAnterior = cursor.getInt(3);
                    pilotos.add(new Piloto(nombre, numero, posicionActual, posicionAnterior));
                } else {
                    pilotos.add(new Piloto(nombre, numero, posicionActual));
                }

            } while (cursor.moveToNext());
        }

        System.out.println("Pilotos ordenados por posicion: " + pilotos);

        return pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        if (getPilotos() == pilotos) return;

        final String DELETE_PILOTOS_SQL = "DELETE FROM pilotos";

        db.delete("pilotos", "", new String[]{});


        ContentValues cv;
        for (int i = 0; i < pilotos.size(); i++) {
            Piloto p = pilotos.get(i);

            cv = new ContentValues();
            cv.put("numPiloto", p.getNumPiloto());
            cv.put("nombre", p.getNombre());
            cv.put("posicion", p.getPosicionActual());
            cv.put("posicionAnterior", p.getPosicionAnterior());

            db.insert("pilotos", null, cv);
        }
    }
}
