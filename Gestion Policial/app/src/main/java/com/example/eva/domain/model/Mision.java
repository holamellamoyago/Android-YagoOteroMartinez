package com.example.eva.domain.model;

import com.example.eva.domain.model.Agentes.Agente;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mision {
    Persona solicitante;
    ArrayList<Agente> agentesAcudiendo;
    LocalDate fecha;
    String titulo,descripcion;

    public Mision(Persona solicitante, ArrayList<Agente> agentesAcudiendo, LocalDate fecha, String titulo, String descripcion) {
        this.solicitante = solicitante;
        this.agentesAcudiendo = agentesAcudiendo;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
}
