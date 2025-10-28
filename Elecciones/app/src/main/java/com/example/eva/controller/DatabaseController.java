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
import com.example.eva.model.Partido;
import com.example.eva.view.MainActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseController {
    private SQLiteDatabase db;
    private Context context;
    private ContentValues cv = new ContentValues();

    private boolean puedeVotar = true;

    public DatabaseController(Context context) {
        this.context = context;
    }

    public ArrayList<String> cargarGanadores(){
        ArrayList<String> candidatos = new ArrayList<>();
        openDatabase();

        Cursor getMaxVotos = db.rawQuery("SELECT MAX(total_votos) FROM " +
                DatabaseConstants.tableCandidatos, null);

        getMaxVotos.moveToFirst();
        int maximo = getMaxVotos.getInt(0);

        Cursor getMaxCandidatos = db.rawQuery(
                "SELECT name FROM " +
                    DatabaseConstants.tableCandidatos +
                    " WHERE total_votos = ?", new String[]{String.valueOf(maximo)});

        if (getMaxCandidatos.moveToFirst()){
            do {
                candidatos.add(getMaxCandidatos.getString(0));

            } while (getMaxCandidatos.moveToNext());
        }



        getMaxVotos.close();
        getMaxCandidatos.close();
        closeDatabase();
        return candidatos;
    }

    public boolean puedeVotar(String nifVotante){
        boolean puedeVotar = false;
        openDatabase();

        Cursor cursor = db.rawQuery("SELECT terminoVotacion FROM " +
                DatabaseConstants.tableVotantes + " WHERE NIF = ?", new String[]{nifVotante});

        if (cursor.moveToFirst()){
            if (cursor.getInt(0) == 0){
                puedeVotar = true;
            }
        }


        closeDatabase();

        return puedeVotar;
    }

    public void terminarVotosVotante(String nifVotante){
        openDatabase();
        cv.clear();
        cv.put("terminoVotacion", 1);

        db.update(DatabaseConstants.tableVotantes, cv, "NIF = ? ", new String[]{nifVotante});


        closeDatabase();
    }

    public void registrarVotos(String cod_candidato) {
        openDatabase();
        cv.clear();

        cv.put("total_votos", getTotalVotosCandidato(cod_candidato) + 1);
        System.out.println(getTotalVotosCandidato(cod_candidato) +1 );
        db.update(DatabaseConstants.tableCandidatos, cv, "cod_candidato = ?", new String[]{cod_candidato});

        closeDatabase();
    }

    private float getTotalVotosCandidato(String cod_candidato) {
        Cursor cursor = db.rawQuery("SELECT total_votos FROM " + DatabaseConstants.tableCandidatos + " WHERE cod_candidato = ? ", new String[]{cod_candidato});

        cursor.moveToFirst();
        return cursor.getInt(0);
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


    // TODO Hacer esta función genérica

    public ArrayList<Candidato> getCandidatos() {
        ArrayList<Candidato> candidatos = new ArrayList<>();

        openDatabase();

        Cursor cursor = db.rawQuery("SELECT cod_candidato, name, total_votos, cod_partido FROM candidatos", null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String name = cursor.getString(1);
                int total_votos = cursor.getInt(2);
                int partido_cod = cursor.getInt(3);

                candidatos.add(new Candidato(codigo, name, total_votos, partido_cod));
            } while (cursor.moveToNext());
        }

        closeDatabase();
        return candidatos;
    }


    public ArrayList<Partido> getPartidos(){
        ArrayList<Partido> partidos = new ArrayList<>();

        openDatabase();

        Cursor cursor = db.rawQuery("SELECT cod_partido, name, color FROM partidos", null);

        if (cursor.moveToFirst()){

            do {
                int codPartido = cursor.getInt(0);
                String nombrePartido = cursor.getString(1);
                int color = cursor.getInt(2);

                Partido partido = new Partido(codPartido,nombrePartido,color);
                partidos.add(partido);

            } while (cursor.moveToNext());
        }


        closeDatabase();
        return partidos;
    }


    private boolean comprobarExistencia(String nif) {
        Cursor cursor = db.rawQuery("SELECT terminoVotacion FROM votantes WHERE NIF = ?", new String[]{nif});

        //De primeras si existe
        if (cursor.moveToFirst()) {

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
