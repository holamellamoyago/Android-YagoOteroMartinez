package com.example.eva.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 15;
    static final String  NOMBRE_DB = "elecciones";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB,null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String votantes_sql = "CREATE TABLE votantes (NIF TEXT PRIMARY KEY, password TEXT, terminoVotacion INTEGER DEFAULT 0)";

        final String partidos_sql = "CREATE TABLE partidos (cod_partido INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE)";

        final String candidato_sql = "CREATE TABLE candidatos (cod_candidato INTEGER PRIMARY KEY AUTOINCREMENT, total_votos INTEGER DEFAULT 0, " +
                                        "name TEXT, cod_partido INTEGER, FOREIGN KEY (cod_partido) REFERENCES partidos(cod_partido))";


        db.execSQL(votantes_sql);
        db.execSQL(partidos_sql);
        db.execSQL(candidato_sql);

        isertarPartidosEjemplo(db);
        insertarCandidatosEjemplo(db);


    }

    private void isertarPartidosEjemplo(SQLiteDatabase db) {
        cv.clear();

        cv.put("name", "pp"); db.insert("partidos" , null , cv);
        cv.put("name", "psoe"); db.insert("partidos" , null , cv);
        cv.put("name", "VOX"); db.insert("partidos" , null , cv);

    }

    private void insertarCandidatosEjemplo(SQLiteDatabase db){
        cv.clear();

        cv.put("name", "Santiago abascal"); cv.put("cod_partido", "3");
        db.insert("candidatos", null, cv);

        cv.put("name", "Pedrito beca"); cv.put("cod_partido", "2");
        db.insert("candidatos", null, cv);

        cv.put("name", "Maria Rajoy"); cv.put("cod_partido", "1");
        db.insert("candidatos", null, cv);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS votantes");
        db.execSQL("DROP TABLE IF EXISTS partidos");
        db.execSQL("DROP TABLE IF EXISTS candidatos");
        onCreate(db);
    }
}
