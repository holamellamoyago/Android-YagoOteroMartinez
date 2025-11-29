package com.example.eva.data.openF1;

import com.example.eva.domain.model.Piloto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GestorOpenF1 {

    final String URL_CONNECTION = "https://api.openf1.org/v1/drivers?driver_number=%1$d&session_key=latest";

    public String getUrlContents(String urlConnection) {
        StringBuilder str = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(urlConnection).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
                str.append(linea);
        } catch (IOException ex) {
            return "json Error: ";
        }

        return str.toString();
    }

    public Piloto getInformacionPiloto(int numPiloto) {
        String json = String.format(URL_CONNECTION, 1);
        try {
//            JSONObject datos2 = new JSONObject(getUrlContents(json));
            JSONArray datos2 = new JSONArray(getUrlContents(json));
//            String strMax = datos2.getString(0);
//            JSONObject objMax = new JSONObject(strMax);

//            System.out.println("json" + objMax.toString());


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
