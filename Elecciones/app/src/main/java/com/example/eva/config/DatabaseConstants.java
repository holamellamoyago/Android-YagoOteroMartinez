package com.example.eva.config;

import android.content.Context;

import com.example.eva.model.Candidato;
import com.example.eva.model.Partido;
import com.example.eva.controller.*;


import java.util.ArrayList;
import java.util.List;

public class DatabaseConstants {
    private Context contexto;
    private DatabaseController controller;

    public static ArrayList<Candidato> candidatos;
    public static ArrayList<Partido> partidos;

    public DatabaseConstants(Context contexto) {
        controller = new DatabaseController(contexto);

        candidatos = controller.getCandidatos();
        partidos = controller.getPartidos();

    }

    public static final String tableVotantes = "votantes";
    public static final String tableCandidatos = "candidatos";

    public static final String votante_NIF = "NIF";
    public static final String votante_password = "password";
    public static final String votante_terminoVotacion = "terminoVotacion";

}
