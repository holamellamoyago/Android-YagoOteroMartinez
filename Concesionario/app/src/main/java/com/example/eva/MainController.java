package com.example.eva;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainController {
    private Context context;
    private ControllerDatabase controllerDatabase;

    private ListView lv_ayudas;
    private ArrayList<String> ayudas;
    private ArrayAdapter<String> adapterArray;


    private boolean marcaAcertada;

    public MainController(Context context) {
        this.context = context;
        this.controllerDatabase = new ControllerDatabase();
        this.ayudas = new ArrayList<>();
        this.adapterArray = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ayudas);
        this.marcaAcertada = false;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<String> getAyudas() {
        return ayudas;
    }

    public void setAyudas(ArrayList<String> ayudas) {
        this.ayudas = ayudas;
    }

    public ArrayAdapter<String> getAdapterArray() {
        return adapterArray;
    }

    public void setAdapterArray(ArrayAdapter<String> adapterArray) {
        this.adapterArray = adapterArray;
    }

    public ListView getLv_ayudas() {
        return lv_ayudas;
    }

    public void setLv_ayudas(ListView lv_ayudas) {
        this.lv_ayudas = lv_ayudas;
    }

    public void anadirPista() {
        int i = adapterArray.getCount();

        if (!marcaAcertada) {
            adapterArray.add(controllerDatabase.cogerPistaMarcaID(i));
        } else {
            adapterArray.add(controllerDatabase.cogerPistaModeloID(i));
        }
    }

    public boolean comprobarMarca(String marca) {

        if (marca.equals(controllerDatabase.cogerMarca())) {
            marcaAcertada = true;
            return true;
        }

        return false;
    }


    public boolean comprobarModelo(String modelo) {

        if (modelo.equals(controllerDatabase.cogerModelo())) {
            marcaAcertada = true;
            return true;
        }

        return false;
    }
}
