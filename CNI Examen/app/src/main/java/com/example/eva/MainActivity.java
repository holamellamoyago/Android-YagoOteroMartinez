package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.fragmento.ControllerFrgCni;
import com.example.eva.fragmento.FrgCniSensorIA;

public class MainActivity extends AppCompatActivity {
    ControllerFrgCni controllerFrgCni = new ControllerFrgCni();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iniciarFragments();






    }

    private void iniciarFragments() {
        FragmentManager frgManager = getSupportFragmentManager();
        FrgCniSensorIA frgDestinatario = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgDestinatario);
        frgDestinatario.setTextos("Escribe el destinatario");

        FrgCniSensorIA frgAsunto = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgAsunto);
        frgAsunto.setTextos("Escribe el asunto");

        FrgCniSensorIA frgMensaje = (FrgCniSensorIA) frgManager.findFragmentById(R.id.frgMensaje);
        frgMensaje.setTextos("Escribe el mensaje");

        controllerFrgCni.setFragment(frgDestinatario, frgAsunto, frgMensaje);
    }

}




























