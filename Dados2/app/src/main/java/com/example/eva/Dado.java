package com.example.eva;

public class Dado {
    private int id;
    private int numCaras;
    private int ultNumero;

    public Dado(int id, int numCaras) {
        this.id = id;
        this.numCaras = numCaras;
    }

    public Dado(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCaras() {
        return numCaras;
    }

    public void setNumCaras(int numCaras) {
        this.numCaras = numCaras;
    }

    public int getUltNumero() {
        return ultNumero;
    }

    public void setUltNumero(int ultNumero) {
        this.ultNumero = ultNumero;
    }
}
