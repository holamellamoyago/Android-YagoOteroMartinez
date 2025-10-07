package com.example.eva.data.datasources;

import com.example.eva.domain.model.Mision;

import java.util.ArrayList;

public  interface MisionesDatasource {
    boolean guardarMision(Mision mision);
    ArrayList<Mision> leerMisiones();
}
