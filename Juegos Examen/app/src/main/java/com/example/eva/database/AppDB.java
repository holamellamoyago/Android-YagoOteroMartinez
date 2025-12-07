package com.example.eva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eva.Ruta;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 16;
    static final String NOMBRE_DB = "f1";
    private final int NUM_LIBROS_FILA = 5;
    private final int NUM_FILAS = 4;

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String LIBROS_SQL = "CREATE TABLE libros (TEXT nombre, INTEGER f, INTEGER c)";
        final String RUTAS_SQL = "CREATE TABLE rutas (nombre TEXT  PRIMARY KEY, ruta TEXT)";

        //db.execSQL(LIBROS_SQL);
        db.execSQL(RUTAS_SQL);


        insertarEjemplos(db);

        Ruta r1 = new Ruta("Escaleras", "ID");
        Ruta r2 = new Ruta("Baño", "IIDI");

        insertarRutasEjemplo(db, new ArrayList<>(List.of(r1, r2)));
    }

    private void insertarRutasEjemplo(SQLiteDatabase db, ArrayList<Ruta> rutas) {
        for (int i = 0; i < rutas.size(); i++) {
            Ruta r = rutas.get(i);
            cv.clear();
            cv.put("nombre", r.getNombre());
            cv.put("ruta", r.getRuta());
            db.insert("rutas", null, cv);
        }

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
        db.execSQL("DROP TABLE IF EXISTS rutas");

        onCreate(db);
    }
}
