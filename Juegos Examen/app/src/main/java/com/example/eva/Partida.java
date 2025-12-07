package com.example.eva;

public class Partida {
    private String juego;
    private boolean ganada;
    private int dificultad;

    public Partida(String juego, boolean ganada, int dificultad) {
        this.juego = juego;
        this.ganada = ganada;
        this.dificultad = dificultad;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public boolean isGanada() {
        return ganada;
    }

    public void setGanada(boolean ganada) {
        this.ganada = ganada;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
