package com.example.eva.domain.model.Agentes;

import com.example.eva.domain.model.Equipo;
import com.example.eva.domain.model.Persona;

public abstract class Agente extends Persona {
    int  anhosExperiencia;
    Equipo equipo;

    public Agente(String nombre, String apellido, String NIF, int anhosExperiencia, Equipo equipo) {
        super(nombre, apellido, NIF);
        this.anhosExperiencia = anhosExperiencia;
        this.equipo = equipo;
    }

    public Agente(String nombre, String apellido) {
        super(nombre,apellido, "No nif");
        this.anhosExperiencia = 0;
        this.equipo = null;
    }
}
