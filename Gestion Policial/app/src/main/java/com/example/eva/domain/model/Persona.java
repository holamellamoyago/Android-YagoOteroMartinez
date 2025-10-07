package com.example.eva.domain.model;

public class Persona {
    String nombre, apellido;
    String NIF;

    public Persona(String nombre, String apellido, String NIF) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.NIF = NIF;
    }

    Persona(String NIF) {
        this("No nombre", "No apellido", NIF);
    }


}
