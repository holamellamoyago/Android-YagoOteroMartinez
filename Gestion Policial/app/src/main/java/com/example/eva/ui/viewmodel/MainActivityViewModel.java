package com.example.eva.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.eva.data.datasources.MisionesDatasource;
import com.example.eva.data.datasources.local.localMisionesDatasource;
import com.example.eva.data.repositories.MisionRepository_impl;
import com.example.eva.domain.model.Mision;
import com.example.eva.domain.repositories.MisionRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private final MisionRepository repository;

    public MainActivityViewModel(Application application) {
        repository = new MisionRepository_impl(new localMisionesDatasource(application.getApplicationContext()));
    }

    public boolean guardarMision(Mision mision){
        return repository.guardarMision(mision);
    }

    public ArrayList<Mision> leerMisiones(){
        return repository.leerMisiones();
    }


}
