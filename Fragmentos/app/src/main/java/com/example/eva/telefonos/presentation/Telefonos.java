package com.example.eva.telefonos.presentation;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eva.R;
import com.example.eva.telefonos.fragments.FrgTelefono;

import java.util.ArrayList;
import java.util.List;

public class Telefonos extends AppCompatActivity {


    List<FrgTelefono> telefonos = new ArrayList<>();
    LinearLayout ll;
    final int NUM_CONTACTOS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telefonos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ll = findViewById(R.id.main);

        iniciarContactos();

    }

    private void iniciarContactos() {
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = frgManager.beginTransaction();

        for (int i = 0; i < NUM_CONTACTOS; i++) {
            //telefonoIndividual = (FrgTelefono) frgManager.findFragmentById(R.id.telefonoIndividual);
            FrgTelefono telefonoIndividual = new FrgTelefono(String.valueOf(i));

            telefonoIndividual.setOnFrgTelefonoListener(new FrgTelefono.onFrgTelefonoListener() {
                @Override
                public boolean onCall(FrgTelefono fragment, String numSaliente) {
                    if (!comprobarDisponibilidad(fragment.getNumTelefono(), numSaliente)){
                        return false;
                    }

                    if (fragment.getNumTelefono().equals(numSaliente)){
                        Toast.makeText(Telefonos.this, "Que haces llamandote a ti mismo", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    telefonoIndividual.empezarLlamada(numSaliente);
                    // 1: a quien busca, 2: cual añade (->)
                    cambiarEstadoTelefono(numSaliente, fragment.getNumTelefono());
                    return true;
                }

                @Override
                public void onColgar(FrgTelefono fragment, String numSaliente) {
                    telefonoIndividual.terminarLlmada();
                    // 1: a quien busca, 2: cual añade (->)
                    cambiarEstadoTelefono(numSaliente, fragment.getNumTelefono());
                }
            });

            fragmentTransaction.add(R.id.main, telefonoIndividual, "telefono" + i);
            telefonos.add(telefonoIndividual);
        }

        fragmentTransaction.commit();
    }

    private boolean comprobarDisponibilidad(String n1, String n2) {
        for (int i = 0; i < telefonos.size(); i++) {
        FrgTelefono tel = telefonos.get(i);
            if (tel.getNumTelefono().equals(n1) && tel.isCalling()){
                Toast.makeText(this, "El tlfn entrante se encuentra en una llamada", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (tel.getNumTelefono().equals(n2) && tel.isCalling()){
                Toast.makeText(this, "El tlfn saliente se encuentra en una llamada", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private void cambiarEstadoTelefono(String numTelefono, String numSaliente) {
        for (int i = 0; i < telefonos.size(); i++) {
            if (telefonos.get(i).getNumTelefono().equals(numTelefono)) {
                if (telefonos.get(i).isCalling()) {
                    telefonos.get(i).terminarLlmada();
                } else {
                    telefonos.get(i).empezarLlamada(numSaliente);
                }
                return;
            }
        }

        Toast.makeText(this, "No se encontro el numero ", Toast.LENGTH_SHORT).show();
    }
}






















