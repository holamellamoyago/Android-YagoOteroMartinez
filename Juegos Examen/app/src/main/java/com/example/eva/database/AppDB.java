package com.example.eva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 9;
    static final String NOMBRE_DB = "f1";
    private final int NUM_LIBROS_FILA = 5;
    private final int NUM_FILAS = 4;

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String libros_sql = "CREATE TABLE libros (String nombre, int f, int c)";
        db.execSQL(libros_sql);


        insertarEjemplos(db);
    }

    private void insertarEjemplos(SQLiteDatabase db) {
        /*
            Posible mejora: añadir primero los equipos y después
            añadir cada piloto a cada piloto de esa referencia
         */

        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_LIBROS_FILA; j++) {
                cv.clear();
                cv.put("nombre", "Libro " + i);
                cv.put("f", i);
                cv.put("c", j);
                db.insert("libros", null, cv);
            }

        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS libros");
        onCreate(db);
    }
}
