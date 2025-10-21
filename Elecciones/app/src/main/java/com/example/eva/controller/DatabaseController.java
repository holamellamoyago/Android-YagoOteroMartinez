package com.example.eva.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.Toast;

import com.example.eva.config.DatabaseConstants;
import com.example.eva.model.AppDB;
import com.example.eva.model.Candidato;
import com.example.eva.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;
    private ContentValues cv = new ContentValues();

    private boolean puedeVotar = true;

    public DatabaseController(Context context) {
        this.context = context;
    }

    public void registrarVotos(){

    }

    public boolean checkVotante(String nif, String password) {

        openDatabase();

        cv.put(DatabaseConstants.votante_NIF, nif);
        cv.put(DatabaseConstants.votante_password, password);

        // "Compruebo si el usuario existe en la bd ... "
        if (comprobarExistencia(nif)) {

            // Compruebo si la contraseña no es la que debería sino retorna false
            if (!comprobarContrasenha(nif, password)) {
                Toast.makeText(context, "usuario y/o contraseña erroneo", Toast.LENGTH_LONG).show();
                MainActivity.etPassword.setBackgroundColor(Color.parseColor("red"));
                return false;
            }

            // Ahora que esta logueado compruebo si puede votar
            return puedeVotar;
        }

        db.insertOrThrow("votantes", null, cv);
        Toast.makeText(context, "Usuario añadido", Toast.LENGTH_SHORT).show();

        closeDatabase();

        return true;
    }

    private boolean comprobarContrasenha(String nif, String contrasenha) {
        Cursor cursor = db.rawQuery("SELECT password FROM votantes WHERE NIF = ? ", new String[]{nif});

        if (cursor.moveToNext()) {
            if (contrasenha.equals(cursor.getString(0))) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Candidato> getCandidatos() {
        ArrayList<Candidato> candidatos = new ArrayList<>();

        openDatabase();

        Cursor cursor = db.rawQuery("SELECT cod_candidato, name, total_votos FROM candidatos", null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String name = cursor.getString(1);
                int total_votos = cursor.getInt(2);

                candidatos.add(new Candidato(codigo, name, total_votos));
            } while (cursor.moveToNext());
        }

        closeDatabase();
        return candidatos;
    }


    private boolean comprobarExistencia(String nif) {
        Cursor cursor = db.rawQuery("SELECT terminoVotacion FROM votantes WHERE NIF = ?", new String[]{nif});

        //De primeras si existe
        if (cursor.moveToFirst()) {
            // Si es 0 es que puede seguir votando porque no termino
            if (cursor.getInt(0) > 0) {
                puedeVotar = false;
            }

            // Pero retornamos que true porque si existe
            return true;
        }

        return false;
    }


    private void openDatabase() {
        AppDB appDB = new AppDB(context);
        db = appDB.getWritableDatabase();
    }

    private void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }


}
