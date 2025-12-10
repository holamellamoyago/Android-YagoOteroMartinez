package com.example.eva;

import java.util.ArrayList;
import java.util.List;

public class ControllerDatabase {
    public static final int TOTAL_VIDAS = 3;


    final String MARCA = "BMW";
    final String MODELO = "BMW Serie 1";

    final ArrayList<String> PISTAS_MARCA = new ArrayList<>(List.of("ALEMANA", "AZUL Y BLANCO", "M..."));
    final ArrayList<String> PISTAS_MODELO = new ArrayList<>(List.of("EL COCHE ZAPATILLA", "ES UN SERIE ...", "UNO DE LOS PRIMEROS..."));

    public ControllerDatabase() {}

    public String cogerPistaMarcaID(int i) {
        return PISTAS_MARCA.get(i);
    }

    public String cogerPistaModeloID(int i) {
        return PISTAS_MODELO.get(i);
    }

    public String cogerMarca() {
        // En un futuro hacer la sentencia SQL
        return MARCA.toUpperCase();
    }

    public static ArrayList<String> cogerTotalMarcas() {
        return new ArrayList<>(List.of("Selecciona la marca", "Aston Martin", "Peugeot", "BMW", "MERCEDES", "AUDI"));
    }

    public static ArrayList<String> cogerTotalCoches() {
        return new ArrayList<>(List.of("Selecicona el coche", "BMW Serie 1","BMW X1","BMW i4","BMW M2"));
    }

    public String cogerModelo() {
        return MODELO;
    }
}
