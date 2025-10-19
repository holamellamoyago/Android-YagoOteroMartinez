package com.example.eva.model;


public class Votante {
    private Integer votanteID;
    private String NIF;
    private boolean puedeVotar;

    public Votante(Integer votanteID, String NIF, boolean puedeVotar) {
        this.votanteID = votanteID;
        this.NIF = NIF;
        this.puedeVotar = puedeVotar;
    }

    public Votante(String NIF, boolean puedeVotar) {
        this(null, NIF, puedeVotar);
    }



    public boolean puedeVotar(){
        return puedeVotar;
    }

    public void terminarVotacion() {
        this.puedeVotar = false;
    }


    public int getVotanteID() {
        return votanteID;
    }

    public String getNIF() {
        return NIF;
    }

    public boolean isPuedeVotar() {
        return puedeVotar;
    }
}
