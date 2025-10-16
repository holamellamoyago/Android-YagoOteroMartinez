package com.example.eva.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.model.AppDB;
import com.example.eva.model.Votante;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
    }

    public boolean addVotante(Votante v) {
        // TODO hacer fun
        return false;
    }

    public int cogerSiguienteIDVotante(){
        db.execSQL("SELECT COUNT(*) FROM votantes");

        return 1;
    }

    public void openDatabase() {
        AppDB appDB = new AppDB(context);
        db = appDB.getWritableDatabase();
    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }
}
