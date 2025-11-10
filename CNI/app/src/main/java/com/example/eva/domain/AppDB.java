package com.example.eva.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 19;
    static final String NOMBRE_DB = "palabras";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String palabras_sql = "CREATE TABLE palabras (codPalabra INTEGER PRIMARY KEY AUTOINCREMENT, palabra TEXT NOT NULL)";
        db.execSQL(palabras_sql);

        ArrayList<String> palabrasEjemplo = new ArrayList<>(List.of("Bomba"));
        insertarEjemplos(db, new ArrayList<String>(palabrasEjemplo) );

    }

    private void insertarEjemplos(SQLiteDatabase db, ArrayList<String> palabras) {
        //long referenciaPartido = db.insert("partidos", null, cv);

        for (int i = 0; i < palabras.size(); i++) {
            cv.clear();
            cv.put("palabra", palabras.get(i));
            db.insert(NOMBRE_DB, null, cv);
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS palabras");
        onCreate(db);
    }
}
