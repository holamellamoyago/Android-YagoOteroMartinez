package com.example.eva.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.model.AppDB;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
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
