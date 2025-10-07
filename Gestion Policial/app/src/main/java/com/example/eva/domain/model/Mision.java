package com.example.eva.domain.model;

import com.example.eva.domain.model.Agentes.Agente;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mision {
    Persona solicitante;
    ArrayList<Agente> agentesAcudiendo;
    LocalDate fecha;
    String titulo, descripcion;

    public Mision(Persona solicitante, ArrayList<Agente> agentesAcudiendo, LocalDate fecha, String titulo, String descripcion) {
        this.solicitante = solicitante;
        this.agentesAcudiendo = agentesAcudiendo;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Mision(String titulo) {
        this(new Persona("39511342X"), new ArrayList<Agente>(), LocalDate.now(), titulo, "");
    }


    public Persona getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Persona solicitante) {
        this.solicitante = solicitante;
    }

    public ArrayList<Agente> getAgentesAcudiendo() {
        return agentesAcudiendo;
    }

    public void setAgentesAcudiendo(ArrayList<Agente> agentesAcudiendo) {
        this.agentesAcudiendo = agentesAcudiendo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
