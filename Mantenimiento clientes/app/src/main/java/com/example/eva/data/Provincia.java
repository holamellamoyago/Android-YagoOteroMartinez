package com.example.eva.data;

public class Provincia {
    int codigo;
    String nombre;

    public Provincia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
