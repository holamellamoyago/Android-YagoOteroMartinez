package com.example.eva.data.openF1;

import com.example.eva.domain.model.Equipo;
import com.example.eva.domain.model.Piloto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GestorOpenF1 {

    final String URL_CONNECTION = "https://api.openf1.org/v1/";

    public String getUrlContents(String urlConnection) {
        StringBuilder str = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(urlConnection).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
                str.append(linea);
        } catch (IOException ex) {
            return "Error al intentar accdeder a la API, " + ex.toString();
        }

        return str.toString();
    }

    public Piloto getInformacionPiloto(int numPiloto) {
        String url = URL_CONNECTION + "drivers?driver_number=%1$d&session_key=latest";
        String json = String.format(url, 1);
        try {
            JSONArray jsonDatos = new JSONArray(getUrlContents(json));
            return getAtributosPiloto((JSONObject) jsonDatos.get(0));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Piloto> getPilotos() {
        String url = URL_CONNECTION + "drivers?session_key=latest";
        ArrayList<Piloto> pilotos = new ArrayList<>();
        try {
            JSONArray datos = new JSONArray(getUrlContents(url));
            for (int i = 0; i < datos.length(); i++) {
                JSONObject piloto = (JSONObject) datos.get(i);

                pilotos.add(getAtributosPiloto(piloto));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return pilotos;
    }

    private Piloto getAtributosPiloto(JSONObject objectPiloto) {

        try {
            String full_name = objectPiloto.getString("full_name");
            int driver_number = objectPiloto.getInt("driver_number");
            String broadcast_name = objectPiloto.getString("broadcast_name");
            String team_name = objectPiloto.getString("team_name");
            String team_colour = objectPiloto.getString("team_colour");
            String headshot_url = objectPiloto.getString("headshot_url");

            Equipo equipo = new Equipo(team_name, team_colour);
            return new Piloto(full_name, driver_number, broadcast_name, equipo, headshot_url);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}
