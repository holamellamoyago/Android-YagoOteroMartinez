package com.example.eva.model;


import android.graphics.Color;

import java.io.Serializable;

public class  Candidato implements Serializable {
    private Integer candidatoID;
    private Integer partido_id;
    private String nombre;
    private int totalVotos;


    public Candidato(Integer candidatoID, String nombre, int totalVotos, Integer partido_id)  {
        this.candidatoID = candidatoID;
        this.partido_id = partido_id;
        this.nombre = nombre;
        this.totalVotos = totalVotos;
    }

    public Candidato (String nombre) {
        this(null, nombre, 0 , null);
    }

    public Candidato(int candidatoID, String nombre, int totalVotos)  {
        this(candidatoID, nombre, totalVotos, Color.parseColor("red"));
    }

    public Candidato(int candidatoID, String nombre){
        this(candidatoID, nombre, 0, Color.parseColor("red"));
    }

    @Override
    public String toString() {
        return nombre;
    }


    public int getCandidatoID() {
        return candidatoID;
    }

    public void setCandidatoID(int candidatoID) {
        this.candidatoID = candidatoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public int getPartido_id() {
        return partido_id;
    }

    public void setPartido_id(int partido_id) {
        this.partido_id = partido_id;
    }
}
