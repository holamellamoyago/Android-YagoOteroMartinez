package com.example.eva.model;

public class Partido {
    Integer partidoID;
    String nombre;
    int color;

    public Partido(Integer partidoID, String nombre, int color) {
        this.partidoID = partidoID;
        this.nombre = nombre;
        this.color = color;
    }

    public Partido(String nombre, int color){
        this(null, nombre, color);
    }

    public Integer getPartidoID() {
        return partidoID;
    }

    public void setPartidoID(Integer partidoID) {
        this.partidoID = partidoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
