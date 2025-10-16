package com.example.eva.model;

public class Votante {
    private int votanteID;
    private String NIF;
    private boolean puedeVotar;

    public Votante(int votanteID, String NIF) {
        this.votanteID = votanteID;
        this.NIF = NIF;

        puedeVotar = true;
    }

    public boolean puedeVotar(){
        return puedeVotar;
    }

    public void terminarVotacion() {
        this.puedeVotar = false;
    }
}
