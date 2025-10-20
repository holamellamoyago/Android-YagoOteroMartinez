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
import com.example.eva.model.Votante;
import com.example.eva.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;
    private ContentValues cv = new ContentValues();

    public DatabaseController(Context context) {
        this.context = context;
    }

    public boolean addVotante(String nif, String password) {

        openDatabase();

        cv.put(DatabaseConstants.votante_NIF, nif);
        cv.put(DatabaseConstants.votante_password, password);

        if (comprobarExistencia(nif)) {
            System.out.println("Comprobanndo existencia ... ");
            if (comprobarContrasenha(nif, password)) {
                System.out.println("Misma contrasela ... ");
                MainActivity.etPassword.setBackgroundColor(Color.parseColor("red"));
            }

            return false;
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


//    private int cogerSiguienteIDVotante() {
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM votantes", null);
//
//        if (cursor.moveToFirst()) {
//            return (cursor.getInt(0)) + 1;
//        } else {
//            throw new ArithmeticException("Error al coger el siguiente ID para guardar al usuario");
//        }
//
//    }

    public List<Candidato> getCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();

        openDatabase();

        Cursor cursor = db.rawQuery("SELECT cod_candidato, name, total_votos FROM candidatos", null);

        if (cursor.moveToFirst()){
            do {
                int codigo = cursor.getInt(0);
                String name = cursor.getString(1);
                int total_votos = cursor.getInt(2);

                candidatos.add(new Candidato(codigo, name, total_votos));
            } while (cursor.moveToNext());
        }

        closeDatabase();
        return candidatos;
    };

    public void guardarVotacionCandidatos(Candidato c ) {

    }



    private boolean comprobarExistencia(String nif) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM votantes WHERE NIF = ?", new String[]{nif});

        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) != 0) {
                return true;
            }
        }

        return false;
    };

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
