package com.example.eva.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 1;
    static final String  NOMBRE_DB = "elecciones";


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String usuarios_sql = "CREATE TABLE votantes (cod_user INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "name TEXT, email TEXT UNIQUE, password TEXT, terminoVotacion INTEGER)";

        final String partidos_sql = "CREATE TABLE partidos (cod_partido PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE)";

        final String candidato_sql = "CREATE TABLE candidatos (cod_candidato PRIMARY KEY AUTOINCREMENT, " +
                                        "name TEXT, cod_partido INTEGER, FOREIGN KEY (cod_partido) REFERENCES users(cod_user))";


        db.execSQL(usuarios_sql);
        db.execSQL(partidos_sql);
        db.execSQL(candidato_sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS votantes");
        db.execSQL("DROP TABLE IF EXISTS partidos");
        db.execSQL("DROP TABLE IF EXISTS candidatos");
        onCreate(db);
    }
}
