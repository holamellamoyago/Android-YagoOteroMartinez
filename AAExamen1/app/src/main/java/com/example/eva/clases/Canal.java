package com.example.eva.clases;

import android.content.Context;

import com.example.eva.Utils;
import com.example.eva.presentation.fragments.FrgTelevision;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Canal implements Serializable {
    private String nombre;
    private int numVisualizacionesMaximas;
    private double precio;
    private boolean publico, suscrito;

    // 12:30
    private int numVisualizacionesActuales;

    private Set<FrgTelevision> televisiones = new HashSet<>();


    public Canal(String nombre, int numVisualizacionesMaximas, double precio, boolean publico, boolean suscrito) {
        this.nombre = nombre;
        this.numVisualizacionesMaximas = numVisualizacionesMaximas;
        this.precio = precio;
        this.publico = publico;
        this.suscrito = suscrito;

        numVisualizacionesActuales = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumVisualizacionesMaximas() {
        return numVisualizacionesMaximas;
    }

    public void setNumVisualizacionesMaximas(int numVisualizacionesMaximas) {
        this.numVisualizacionesMaximas = numVisualizacionesMaximas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public boolean isSuscrito() {
        return suscrito;
    }

    public void setSuscrito(boolean suscrito) {
        this.suscrito = suscrito;
    }

    public int getNumVisualizacionesActuales() {
        return numVisualizacionesActuales;
    }

    public void setNumVisualizacionesActuales(int numVisualizacionesActuales) {
        this.numVisualizacionesActuales = numVisualizacionesActuales;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringDetallado() {
        //String totalCuota = String.valueOf(numVisualizacionesMaximas * precio);
        return nombre + " [Visualizaciones compradas: " + numVisualizacionesMaximas + "] Coste: " + precio + " (" +
                suscrito + ")";
    }


    // A partir de aquí  listener
    public boolean comprobarDisponible(Context context, FrgTelevision televoision) {

        if (televisiones.size() >= numVisualizacionesMaximas) {
            Utils.mostrarMensaje(context, "No quedan visualizaciones disponibles");
            return false;

        }
        televisiones.add(televoision);

        return true;
    }
}
