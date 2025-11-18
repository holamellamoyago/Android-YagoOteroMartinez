package com.example.eva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 3;
    static final String NOMBRE_DB = "telefonos";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String telefonos_sql = "CREATE TABLE telefonos (telefono INTEGER PRIMARY KEY)";
        db.execSQL(telefonos_sql);

        ArrayList<String> telefonosEjemplo = new ArrayList<>(List.of("133", "22", "37", "44"));
        insertarEjemplos(db, new ArrayList<String>(telefonosEjemplo));

    }

    private void insertarEjemplos(SQLiteDatabase db, ArrayList<String> telefonos) {
        //long referenciaPartido = db.insert("partidos", null, cv);
        System.out.println("Telefonos: " + telefonos);

        for (int i = 0; i < telefonos.size(); i++) {
            cv.clear();
            cv.put("telefono", telefonos.get(i));
            db.insert(NOMBRE_DB, null, cv);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS telefonos");
        onCreate(db);
    }
}
