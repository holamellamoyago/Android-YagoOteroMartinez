package com.example.eva;

import com.example.eva.model.Marea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.parsers.*;

public class GestorMareasJSON {

    public String getUrlContents(String strURL) {
        StringBuilder str = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(strURL).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
                str.append(linea);
        } catch (IOException ex) {
            return "";
        }
        return str.toString();
    }

    public ArrayList<Marea> getMareas(String json) {
        ArrayList<Marea> mareas = new ArrayList<>();
        try {
            JSONObject datos = new JSONObject(json);
            JSONObject datosPorto = (JSONObject) datos.getJSONArray("mareas").get(0);
            JSONArray listaMareas = (JSONArray) datosPorto.get("listaMareas");
            for (int i = 0; i < listaMareas.length(); i++) {
                JSONObject marea = (JSONObject) listaMareas.get(i);
                boolean pleamar = marea.get("idTipoMarea").toString().equals("1");

                String txtEstado = "estad o ???";

                float altura = Float.parseFloat(marea.get("altura").toString());
                String hora = marea.get("hora").toString();
                mareas.add(new Marea(pleamar, txtEstado, altura, hora));
            }
        } catch (JSONException e) {
            return mareas;
        }
        return mareas;
    }

}



