package com.example.eva;

import static androidx.core.content.ContextCompat.getString;

import com.example.eva.model.Marea;

import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.parsers.*;

public class GestorMareasXML {
    String strUrlMareasXML = MainActivity.strURL + "/mgrss/predicion/rssMareas.action?idPorto=";

    public ArrayList<Marea> getMareas(Document doc) {
        ArrayList<Marea> mareas = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName("Mareas:mareas");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            boolean pleamar = e.getAttribute("idTipoMarea").equals("1");
            //String txtEstado=getString(pleamar?R.string.pleamar:R.string.bajamar);
            String txtEstado = "Estado??";
            float altura = Float.valueOf(e.getAttribute("altura").replace(",", "."));
            String hora = e.getAttribute("hora");
            mareas.add(new Marea(pleamar, txtEstado, altura, hora));
        }
        return mareas;
    }

    public String getXmlMareas(int codPorto) {
        StringBuilder xml = new StringBuilder("");
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(strUrlMareasXML + codPorto).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
                xml.append(linea);
        } catch (IOException ex) {
            return "";
        }
        return xml.toString();
    }


    public Document loadXMLFromString(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);
        } catch (Exception e) {
            return null;
        }
    }

//    private void poblarListaMareas(int codPuerto) {
//        new Thread(new Runnable() {
//            public void run() {
//                String xml = getXmlMareas(codPuerto);
//                Document doc = loadXMLFromString(xml);
//                ArrayList<Marea> mareas = getMareasConHora(getMareas(doc));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        updateUI(mareas);
//                    }
//                });
//            }
//        }).start();

}
