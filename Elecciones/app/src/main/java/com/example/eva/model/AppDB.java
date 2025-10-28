package com.example.eva.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    static final int VERSION_DB = 17;
    static final String NOMBRE_DB = "elecciones";

    ContentValues cv = new ContentValues();


    public AppDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String votantes_sql = "CREATE TABLE votantes (NIF TEXT PRIMARY KEY, password TEXT, terminoVotacion INTEGER DEFAULT 0)";

        final String partidos_sql = "CREATE TABLE partidos (cod_partido INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, color INTEGER UNIQUE)";

        final String candidato_sql = "CREATE TABLE candidatos (cod_candidato INTEGER PRIMARY KEY AUTOINCREMENT, total_votos INTEGER DEFAULT 0, " +
                "name TEXT, cod_partido INTEGER, FOREIGN KEY (cod_partido) REFERENCES partidos(cod_partido))";


        db.execSQL(votantes_sql);
        db.execSQL(partidos_sql);
        db.execSQL(candidato_sql);

        Partido pp = new Partido( "PP", Color.parseColor("blue"));
        Partido vox = new Partido( "VOX", Color.parseColor("green"));
        Partido psoe = new Partido( "PSOE", Color.parseColor("red"));

        ArrayList<Candidato> candidatosVOX = new ArrayList<>(List.of(new Candidato("Santiago Abascal")));
        ArrayList<Candidato> candidatosPSOE = new ArrayList<>(List.of(new Candidato("PEdro Sanchez"), new Candidato("María Anzar")));
        ArrayList<Candidato> candidatosPP = new ArrayList<>(List.of(new Candidato("Francisco frnaco"), new Candidato("Mariano rajoy")));

        insertarEjemplos(db, pp, candidatosPP);
        insertarEjemplos(db, psoe, candidatosPSOE);
        insertarEjemplos(db, vox, candidatosVOX );

    }

    private void insertarEjemplos(SQLiteDatabase db, Partido partido, ArrayList<Candidato> candidatos) {
        cv.clear();

        cv.put("name", partido.nombre);
        cv.put("color", partido.color);

        long referenciaPartido = db.insert("partidos", null, cv);

        for (int i = 0; i < candidatos.size(); i++) {
            cv.clear();
            Candidato candidato = candidatos.get(i);
            cv.put("name", candidato.getNombre());
            cv.put("cod_partido", referenciaPartido);
            db.insert("candidatos", null, cv);
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS votantes");
        db.execSQL("DROP TABLE IF EXISTS partidos");
        db.execSQL("DROP TABLE IF EXISTS candidatos");
        onCreate(db);
    }
}
