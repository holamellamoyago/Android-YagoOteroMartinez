package com.example.eva.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piloto {
    private String nombre;
    private int numPiloto;
    private int posicionActual;
    private Integer posicionAnterior;

    public Piloto(String nombre, int numPiloto, int posicionActual, Integer posicionAnterior) {
        this.nombre = nombre;
        this.numPiloto = numPiloto;
        this.posicionActual = posicionActual;
        this.posicionAnterior = posicionAnterior;
    }


    public Piloto(String nombre, int numPiloto, Integer posicionActual) {
        this(nombre, numPiloto, posicionActual, null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPiloto() {
        return numPiloto;
    }

    public void setNumPiloto(int numPiloto) {
        this.numPiloto = numPiloto;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return numPiloto == piloto.numPiloto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numPiloto);
    }

    @Override
    public String toString() {
        return nombre + " (" + numPiloto + ")";
    }


    public Integer getPosicionAnterior() {
        return posicionAnterior;
    }

    public void setPosicionAnterior(Integer posicionAnterior) {
        this.posicionAnterior = posicionAnterior;
    }
}
