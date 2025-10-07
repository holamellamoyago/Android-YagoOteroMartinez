package com.example.eva.data.datasources.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;


import com.example.eva.data.datasources.MisionesDatasource;
import com.example.eva.domain.model.Mision;

import java.time.LocalDate;
import java.util.ArrayList;

public class localMisionesDatasource implements MisionesDatasource {

    AsistenteDB asistenteDB;
    SQLiteDatabase db;
    ContentValues values;

    public localMisionesDatasource(Context context) {
        this.asistenteDB = new AsistenteDB(context);

        db = asistenteDB.getWritableDatabase();

    }

    @Override
    public boolean guardarMision(Mision mision) {
        if (db != null && mision != null) {

            values.put("titulo", mision.getTitulo());
            values.put("descripcion", mision.getDescripcion());
            values.put("fecha", String.valueOf(mision.getFecha()));

            db.insert("misiones", null, values);
            values.clear();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Mision> leerMisiones() {
        ArrayList<Mision> misiones = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM misiones", null);

        if (cursor.moveToFirst()){
            do {
                String titulo = cursor.getString(1);
                String descripcion = cursor.getString(2);
                LocalDate fecha = LocalDate.parse(cursor.getString(3));

                misiones.add(new Mision(titulo));
            } while (cursor.moveToNext());
        }

        return misiones;

    }
}
