package com.example.eva.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 9;
    static final String NOMBRE_DB = "f1";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String pilotos_sql = "CREATE TABLE pilotos (driverNumber INTEGER PRIMARY KEY, fullName TEXT UNIQUE NOT NULL, broadcastName TEXT UNIQUE," +
                " teamName TEXT, imageURL TEXT UNIQUE, posicionActual INTEGER UNIQUE, posicionAnterior INTEGER UNIQUE)";
        db.execSQL(pilotos_sql);

//        ArrayList<Piloto> pilotos = new ArrayList<>();
//        pilotos.add(new Piloto("Fernando Alonso", 14, 1));
//        pilotos.add(new Piloto("Carlos Sainz", 55, 2));
//        pilotos.add(new Piloto("SUPER MAAAAX VERSTAPPEN", 1, 3));

//        insertarEjemplos(db, pilotos);
    }

//    private void insertarEjemplos(SQLiteDatabase db, ArrayList<Piloto> pilotos) {
//        /*
//            Posible mejora: añadir primero los equipos y después
//            añadir cada piloto a cada piloto de esa referencia
//         */
//
//        for (int i = 0; i < pilotos.size(); i++) {
//            cv.clear();
//            Piloto p = pilotos.get(i);
//            cv.put("numPiloto", p.getDriverNumber());
//            cv.put("nombre", p.getFullName());
//            cv.put("posicion", p.getPosicionActual());
//            db.insert("pilotos", null, cv);
//        }
//    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS pilotos");
        onCreate(db);
    }
}
