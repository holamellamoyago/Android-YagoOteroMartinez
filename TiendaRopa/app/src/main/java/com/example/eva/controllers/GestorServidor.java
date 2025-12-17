package com.example.eva.controllers;

import com.example.eva.models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class GestorServidor {
    private String direccion;
    private int puerto;

    private static GestorServidor gestor;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private OnProductosListener listener;

    public interface OnProductosListener {
        void onProductosActualizados(GestorServidor gestorServidor, ArrayList<Producto> productos);
    }



    private GestorServidor(String direccion, int puerto) {
        this.direccion = direccion;
        this.puerto = puerto;

        new Thread(() -> {
            try {
                socket = new Socket(direccion, puerto);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Cliente nuevo conectado");
            } catch (IOException e) {
                System.out.println("Problemas al conectarse al servidor");
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static GestorServidor initialize(String direccion, int puerto) {
        gestor = new GestorServidor(direccion, puerto);
        return gestor;
    }



    public static GestorServidor getInstance() {
        if (gestor == null) throw new ArithmeticException("Gestor Servidor no iniciado");

        return gestor;
    }

    public ArrayList<Producto> solicitarProductos(OnProductosListener listener) {
        ArrayList<Producto> productos = new ArrayList<>();

        new Thread(() -> {
            try {
                out.writeUTF("STOCK");
                String stockJSON = in.readUTF();
                System.out.println(stockJSON);

                JSONArray jsonArray = new JSONArray(stockJSON);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String titulo = obj.getString("titulo");
                    String subtitulo = obj.getString("subtitulo");
                    double precio = obj.getDouble("precio");
                    String rutaImagen = obj.getString("rutaImagen");

                    Producto pro = new Producto(titulo, subtitulo, precio, rutaImagen);
                    productos.add(pro);
                }

                listener.onProductosActualizados(this, productos);

            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();

        return productos;
    }

    public void actualizarProductos(ArrayList<Producto> productos) {
        ControllerDatabase.productos.addAll(productos);
    }


}
