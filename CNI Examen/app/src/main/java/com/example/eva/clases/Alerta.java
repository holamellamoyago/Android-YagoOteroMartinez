package com.example.eva.clases;

import java.io.Serializable;

public class Alerta implements Serializable {
    private String token, contexto, control;
    private boolean valida;

    public Alerta(String token, String contexto, String control, boolean valida) {
        this.token = token;
        this.contexto = contexto;
        this.control = control;
        this.valida = valida;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    @Override
    public String toString() {
        return "Alerta: " + token + " detectada en el " + control + ", contexto: (" + contexto + ")";
    }
}
