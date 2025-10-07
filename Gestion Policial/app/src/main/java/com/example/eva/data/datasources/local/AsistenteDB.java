package com.example.eva.data.datasources.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AsistenteDB extends SQLiteOpenHelper {
    final static String NOMBRRE_BD = "POLICIA";
    final static int VERSION_BD = 1;

    public AsistenteDB(@Nullable Context context) {
        super(context, NOMBRRE_BD, null, VERSION_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String agentes_sql = "CREATE TABLE agentes (cod INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, nif TEXT, anhosExperiencia INTEGER)";
        String misiones_sql = "CREATE TABLE misiones (cod INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descripcion TEXT, fecha TEXT )";

        db.execSQL(agentes_sql);
        db.execSQL(misiones_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropAgentes_sql = "DROP TABLE IF EXISTS agentes";
        String dropMisiones_sql = "DROP TABLE IF EXISTS misiones";
        db.execSQL(dropAgentes_sql);
        db.execSQL(dropMisiones_sql);

        onCreate(db);
    }
}
