package com.example.eva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eva.clases.Canal;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 12;
    static final String NOMBRE_DB = "canales";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CANALES_SQL = "CREATE TABLE canales (nombre TEXT PRIMARY KEY, precio TEXT, visualizaciones INTEGER, isPublico INTEGER, isSuscrito INTEGER)";
        db.execSQL(CANALES_SQL);


        Canal tv1 = new Canal("TVE", 99, 0, true, true);
        Canal tvg = new Canal("TVG", 99, 0, true, true);
        Canal f1 = new Canal("F1", 1, 10, false, true);
        Canal cp = new Canal("CAZA Y PESCA", 3, 10, false, true);
        Canal cplus = new Canal("CANAL PLUS", 0, 20, false, false);

        ArrayList<Canal> canales = new ArrayList<>(List.of(tv1, tvg, f1, cp, cplus));


        insertarEjemplos(db, canales);
    }

    private void insertarEjemplos(SQLiteDatabase db, ArrayList<Canal> canales) {

        for (int i = 0; i < canales.size(); i++) {
            cv.clear();
            Canal p = canales.get(i);
            cv.put("nombre", p.getNombre());
            cv.put("precio", String.valueOf(p.getPrecio()));
            cv.put("visualizaciones", p.getNumVisualizacionesMaximas());

            if (p.isPublico()) {
                cv.put("isPublico", 1);
            } else {
                cv.put("isPublico", 0);
            }

            if (p.isSuscrito()) {
                cv.put("isSuscrito", 1);
            } else {
                cv.put("isSuscrito", 0);
            }

            db.insert("canales", null, cv);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS canales");
        onCreate(db);
    }
}
