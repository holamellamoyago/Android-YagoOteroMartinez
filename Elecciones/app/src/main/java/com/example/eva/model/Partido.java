package com.example.eva.model;

public class Partido {
    int partidoID;
    String nombre;

    public Partido(int partidoID, String nombre) {
        this.partidoID = partidoID;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
