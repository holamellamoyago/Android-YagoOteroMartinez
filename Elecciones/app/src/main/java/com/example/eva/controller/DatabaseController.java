package com.example.eva.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.eva.config.DatabaseConstants;
import com.example.eva.model.AppDB;
import com.example.eva.model.Votante;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;
    private ContentValues cv = new ContentValues();

    public DatabaseController(Context context) {
        this.context = context;
    }

    public boolean addVotante(String nif , String password) {

        openDatabase();

        cv.put(DatabaseConstants.votante_NIF, nif);
        cv.put(DatabaseConstants.votante_password, password);

        db.insertOrThrow("votantes", null, cv);

        closeDatabase();

        Toast.makeText(context, "Usuario añadido", Toast.LENGTH_SHORT).show();
        return true;
    }



    private int cogerSiguienteIDVotante(){
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM votantes", null);

        if (cursor.moveToFirst()) {
            return (cursor.getInt(0)) + 1;
        } else {
            throw  new ArithmeticException("Error al coger el siguiente ID para guardar al usuario");
        }

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
