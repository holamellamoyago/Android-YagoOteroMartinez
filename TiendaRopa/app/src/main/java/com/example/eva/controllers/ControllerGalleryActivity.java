package com.example.eva.controllers;

import android.widget.ArrayAdapter;

import com.example.eva.presentation.adapters.ProductoAdapter;

public class ControllerGalleryActivity {
    public ProductoAdapter productoAdapter;

    public ControllerGalleryActivity() {
    }

    public void recargarLista() {
        productoAdapter.notifyDataSetChanged();
    }

}
