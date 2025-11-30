package com.example.eva.model;

import com.example.eva.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;


public class HTTP {
    String strUrlMareasJSON = MainActivity.strURL + "/mgrss/predicion/mareas/jsonMareas.action?idPorto=";

    public static String getUrlContents(String strURL) {
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
}




