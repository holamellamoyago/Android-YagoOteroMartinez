package com.example.eva.data.repositories;

import android.content.Context;

import com.example.eva.data.datasources.MisionesDatasource;
import com.example.eva.domain.model.Mision;
import com.example.eva.domain.repositories.MisionRepository;

import java.util.ArrayList;

public class MisionRepository_impl implements MisionRepository {
    MisionesDatasource datasource;

    public MisionRepository_impl(MisionesDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public boolean guardarMision(Mision mision) {
        return datasource.guardarMision(mision);
    }

    @Override
    public ArrayList<Mision> leerMisiones() {
        return datasource.leerMisiones();
    }
}
