package com.example.eva.domain.model;

import com.example.eva.domain.model.Agentes.Agente;

import java.util.ArrayList;

public class Equipo {
    String nombre;
    ArrayList<Agente> agentes;

    public Equipo(String nombre, ArrayList<Agente> agentes) {
        this.nombre = nombre;
        this.agentes = agentes;
    }
}
