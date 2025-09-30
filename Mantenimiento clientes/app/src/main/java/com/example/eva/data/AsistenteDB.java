package com.example.eva.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class  AsistenteDB extends SQLiteOpenHelper {
    static final int version = 3;
    static final String NAME_DB = "CLIENTES_DB";
    //private final SQLiteDatabase db = new AsistenteDB(this);


    public AsistenteDB(@Nullable Context context) {
        super(context, NAME_DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE clientes (cod INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(30), apellidos VARCHAR(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS clientes");
        onCreate(db);
    }
}
