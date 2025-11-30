package com.example.eva.controller;

import com.example.eva.GestorMareasJSON;
import com.example.eva.MainActivity;
import com.example.eva.model.Marea;

import java.util.ArrayList;

public class HiloJSON extends Thread {
    String strUrlMareasJSON = MainActivity.strURL + "/mgrss/predicion/mareas/jsonMareas.action?idPorto=";

    @Override
    public void run() {
        GestorMareasJSON gestorMareasJSON = new GestorMareasJSON();

        String strContents = gestorMareasJSON.getUrlContents(strUrlMareasJSON);
        ArrayList<Marea> mareas =  gestorMareasJSON.getMareas(strContents);
        System.out.println("json " + mareas);
    }
}
