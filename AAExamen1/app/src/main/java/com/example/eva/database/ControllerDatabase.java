package com.example.eva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.clases.Canal;

import java.util.ArrayList;

public class ControllerDatabase {
    private SQLiteDatabase db;
    private ContentValues cv;


    public ControllerDatabase(Context context) {
        this.db = new AppDB(context).getWritableDatabase();
        this.cv = new ContentValues();
    }

    public ArrayList<Canal> getCanales() {
        ArrayList<Canal> canales = new ArrayList<>();
        final String SQL = "SELECT nombre, precio, visualizaciones, isPublico, isSuscrito FROM canales";
        Cursor cursor = db.rawQuery(SQL, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                double precio = Double.valueOf(cursor.getString(1));
                int visualizaciones = cursor.getInt(2);
                boolean isPublico = cursor.getInt(3) == 1 ? true : false;

                boolean isSuscrito  = cursor.getInt(4) == 1 ? true : false;
                canales.add(new Canal(nombre, visualizaciones, precio, isPublico, isSuscrito));
            } while (cursor.moveToNext());
        }

        return canales;
    }

    public void suscribirseCanal(Canal canal) {
        cv.clear();
        cv.put("isSuscrito", 1);
        cv.put("visualizaciones", canal.getNumVisualizacionesMaximas());


        db.update("canales", cv, "nombre=?", new String[]{canal.getNombre()});
    }

}
