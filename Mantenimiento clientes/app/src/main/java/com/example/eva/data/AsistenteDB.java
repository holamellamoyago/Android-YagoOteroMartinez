package com.example.eva.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class  AsistenteDB extends SQLiteOpenHelper {
    static final int version = 10;
    static final String NAME_DB = "CLIENTES_DB";
    ContentValues cv = new ContentValues();

    //private final SQLiteDatabase db = new AsistenteDB(this);


    public AsistenteDB(@Nullable Context context) {
        super(context, NAME_DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String clientes_sql = "CREATE TABLE clientes (cod_clientes INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(30), apellidos VARCHAR(50), vip INTEGER)";
        String provincias_sql =
                "CREATE TABLE provincias (cod_provincias INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT)";

        db.execSQL(clientes_sql);
        db.execSQL(provincias_sql);

        agregarProvincias(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS clientes");
        db.execSQL("DROP TABLE IF EXISTS provincias");
        onCreate(db);
    }

    private void agregarProvincias(SQLiteDatabase db){
        cv.put("nombre", "A Coruña");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Lugo");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Ourense");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Pontevedra");
        db.insert("provincias", null, cv);
    }

    public ArrayList<Provincia> getProvincias (){
        Cursor c = getDB().rawQuery("SELECT * FROM provincias", null);
        ArrayList<Provincia> provincias  = new ArrayList<>(List.of(new Provincia(-1, "")));

        if (c.moveToFirst()){
            do {
                Integer cod = Integer.valueOf(c.getString(0));
                String nombre = c.getString(1);

                provincias.add(new Provincia(cod,nombre));
            } while (c.moveToNext());
        }

        return provincias;

    }

    public SQLiteDatabase getDB(){
        return this.getWritableDatabase();
    }
}
