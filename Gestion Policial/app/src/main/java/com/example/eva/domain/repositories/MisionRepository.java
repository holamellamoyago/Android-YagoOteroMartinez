package com.example.eva.domain.repositories;

import com.example.eva.domain.model.Mision;

import java.util.ArrayList;

public interface MisionRepository {
     boolean guardarMision(Mision mision);
     ArrayList<Mision> leerMisiones();
}
