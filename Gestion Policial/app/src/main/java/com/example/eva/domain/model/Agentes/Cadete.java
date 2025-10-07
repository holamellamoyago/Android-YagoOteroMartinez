package com.example.eva.domain.model.Agentes;

import com.example.eva.domain.model.Equipo;

public class Cadete extends  Agente{

    public Cadete(String nombre, String apellido, String NIF, int anhosExperiencia, Equipo equipo) {
        super(nombre, apellido, NIF, anhosExperiencia, equipo);
    }

    public Cadete(String nombre, String apellido) {
        super(nombre, apellido);
    }


}
