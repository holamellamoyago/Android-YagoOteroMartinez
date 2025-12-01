package com.example.eva.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Piloto implements Serializable {
    private String fullName;
    private int driverNumber;
    private String broadcastName;
    private Equipo equipo;
    private String imageURL;

    private Integer posicionActual;
    private Integer posicionAnterior;

    public Piloto(String fullName, int driverNumber, String broadcastName, Equipo equipo, String imageURL, Integer posicionActual, Integer posicionAnterior) {
        this.fullName = fullName;
        this.driverNumber = driverNumber;
        this.broadcastName = broadcastName;
        this.equipo = equipo;
        this.imageURL = imageURL;
        this.posicionActual = posicionActual;
        this.posicionAnterior = posicionAnterior;
    }

    public Piloto(String fullName, int driverNumber, String broadcastName, Equipo equipo, String imageURL, Integer posicionActual) {
        this(fullName, driverNumber, broadcastName, equipo, imageURL, posicionActual, null);
    }


    public Piloto(String fullName, int driverNumber, String broadcastName, Equipo equipo, String imageURL) {
        this(fullName, driverNumber, broadcastName, equipo, imageURL, null, null);
    }

    public String getBroadcastName() {
        return broadcastName;
    }

    public void setBroadcastName(String broadcastName) {
        this.broadcastName = broadcastName;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    //    String headshot_url = objectPiloto.getString("headshot_url");

//    public Piloto(String full_name, int driver_number, int posicionActual, Integer posicionAnterior) {
//        this.full_name = full_name;
//        this.driver_number = driver_number;
//        this.posicionActual = posicionActual;
//        this.posicionAnterior = posicionAnterior;
//    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public Integer getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return driverNumber == piloto.driverNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(driverNumber);
    }

    @Override
    public String toString() {
        return fullName + " (" + driverNumber + ")";
    }


    public Integer getPosicionAnterior() {
        return posicionAnterior;
    }

    public void setPosicionAnterior(Integer posicionAnterior) {
        this.posicionAnterior = posicionAnterior;
    }
}
