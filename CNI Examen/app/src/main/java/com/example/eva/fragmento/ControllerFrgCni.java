package com.example.eva.fragmento;

import static androidx.core.content.ContextCompat.getString;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager;


import com.example.eva.DeteccionActivity;
import com.example.eva.R;

import java.util.ArrayList;

public class ControllerFrgCni {
    private ArrayList<FrgCniSensorIA> fragmentos = new ArrayList<>();

    private FragmentManager frgManager;
    private Context context;
    private final String correoMalicioso = "@ot.com";

    public ControllerFrgCni(FragmentManager frgManager, Context context) {
        this.frgManager = frgManager;
        this.context = context;
    }

    public void iniciarFragments() {
        FrgCniSensorIA frgDestinatario = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgDestinatario);
        FrgCniSensorIA frgAsunto = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgAsunto);
        FrgCniSensorIA frgMensaje = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgMensaje);

        fragmentos.add(frgDestinatario);
        fragmentos.add(frgAsunto);
        fragmentos.add(frgMensaje);

        frgDestinatario.setTextos(context.getString(R.string.escribe_el_destinatario, "destinatario"));
        frgAsunto.setTextos(context.getString(R.string.escribe_el_destinatario, "asunto"));
        frgMensaje.setTextos(context.getString(R.string.escribe_el_destinatario, "mensaje"));

        for (FrgCniSensorIA frg : fragmentos) {
            frg.setListener((frgCniSensorIA, texto) -> comprobarTextos(frgCniSensorIA, texto));
        }
    }

    private void comprobarTextos(FrgCniSensorIA frg, String texto) {
        switch (frg.getTag()) {
            case "destinatario":
                checkDestinatario(frg, texto);
                break;
            case "asunto":
                checkAsunto(frg, texto);
                break;
            case "mensaje":
                checkMensaje(frg, texto);

            default:
                break;
        }
    }

    private void checkMensaje(FrgCniSensorIA frg, String texto) {
    }

    private void checkAsunto(FrgCniSensorIA frg, String texto) {
        // Esto lo hago para evitar f u e g o
        texto = texto.replaceAll(" ", "");
        String[] palabras = new String[]{"ascensor", "fuego"};

        for (String p : palabras) {
            if (texto.contains(p)) {
                Toast.makeText(context, "detectado", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void checkDestinatario(FrgCniSensorIA frg, String texto) {
        if (frg.getText().endsWith(correoMalicioso)) {
            Toast.makeText(context, "detectado", Toast.LENGTH_LONG).show();
            abrirPantallaDeteccion(frg, correoMalicioso, texto);
        }
    }

    private void abrirPantallaDeteccion(FrgCniSensorIA frg, String token, String contexto) {
        Intent detectionIntent = new Intent(context, DeteccionActivity.class);

        detectionIntent.putExtra("control", frg.getTag());
        detectionIntent.putExtra("token", token);
        detectionIntent.putExtra("contexto", contexto);

        context.startActivity(detectionIntent);
    }

}
