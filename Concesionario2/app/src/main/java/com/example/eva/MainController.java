package com.example.eva;

import android.content.Intent;

public class MainController {
    private ControllerDatabase controllerDatabase = new ControllerDatabase();
    private boolean marcaAcertada = false;

    public MainController() {
    }

    public boolean comprobarMarca(String marca) {
        if (marca == null || controllerDatabase == null) return false;

        if (marca.equals(controllerDatabase.MARCA)) {
            marcaAcertada = true;
            return true;
        }

        return false;
    }

    public boolean comprobarModelo(String modelo) {
        if (modelo == null || controllerDatabase == null) return false;

        return modelo.equals(controllerDatabase.MODELO);
    }

    public String cogerSiguientePista(int i) {
        try {
            if (!marcaAcertada) {
                return controllerDatabase.cogerPistaMarcaID(i);
            } else {
                return controllerDatabase.cogerPistaModeloID(i);
            }
        } catch (Exception e) {
            return null;
        }
    }

    // Devuelve true si quedan vidas
    public boolean comprobarVidas(int vidasRestantes) {
        if (vidasRestantes == 1) {
            return false;
        } else{
            return true;
        }
    }

    public String restarVidas(int vidasRestantes) {
        return String.valueOf((vidasRestantes-1));
    }


}
