package com.example.eva.controller;

import com.example.eva.GestorMareasXML;
import com.example.eva.MainActivity;
import com.example.eva.model.HTTP;
import com.example.eva.model.Marea;

import org.w3c.dom.Document;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;


public class Hilo extends Thread {

    @Override
    public void run() {
        final int PUERTO_MARIN = 15;

        GestorMareasXML gestorMareasXML = new GestorMareasXML();
        String xml = gestorMareasXML.getXmlMareas(PUERTO_MARIN);
        Document document = gestorMareasXML.loadXMLFromString(xml);
        ArrayList<Marea> mareas = gestorMareasXML.getMareas(document);

        System.out.println("MAreas: " + mareas);


    }
}
