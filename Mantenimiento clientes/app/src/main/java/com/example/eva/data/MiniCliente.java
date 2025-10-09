package com.example.eva.data;

public class MiniCliente {
    private int cod;
    private String nombre;
    private String apellidos;

    public MiniCliente(int cod, String nombre, String apellidos) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
