package com.example.eva.model;

public class Marea {
    boolean pleamar;
    String txtEstado;
    float altura;
    String hora;


    public Marea(boolean pleamar, String txtEstado, float altura, String hora) {
        this.pleamar = pleamar;
        this.txtEstado = txtEstado;
        this.altura = altura;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Marea{" +
                "hora='" + hora + '\'' +
                ", altura=" + altura +
                ", txtEstado='" + txtEstado + '\'' +
                ", pleamar=" + pleamar +
                '}';
    }
}
