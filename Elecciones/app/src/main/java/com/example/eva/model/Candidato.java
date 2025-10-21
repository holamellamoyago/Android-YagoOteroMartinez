package com.example.eva.model;

import java.io.Serializable;

public class Candidato implements Serializable {
    private int candidatoID;
    private String nombre;
    private int totalVotos;

    public Candidato(int candidatoID, String nombre, int totalVotos)  {
        this.candidatoID = candidatoID;
        this.nombre = nombre;
        this.totalVotos = totalVotos;
    }

    public Candidato(int candidatoID, String nombre){
        this(candidatoID, nombre, 0);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
