package com.example.eva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AsistenteBD extends SQLiteOpenHelper {
    static final String NOMBRE_DB = "clientes.db";
    static final int VERSION_DB = 1;

    /*public AsistenteBD(Context context,String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/
    public AsistenteBD(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateProvincias = "create table provincias (pipipipi )";
        String sqlCreateClientes = "create table gente (pipipipi )";

        db.execSQL(sqlCreateProvincias);
        db.execSQL(sqlCreateClientes);
        //db.rawQuery("");
        //db.execSQL();

        //objetos clave valor
        ContentValues cv = new ContentValues();
        cv.put("nombre","A Coruña");
        db.insert("provincias",null,cv);

        cv.put("nombre","Lugo");
        db.insert("provincias",null,cv);

        cv.put("nombre","Ourense");
        db.insert("provincias",null,cv);

        cv.put("nombre","Pontevedra");
        db.insert("provincias",null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionVieja, int version2) {

    }
}
