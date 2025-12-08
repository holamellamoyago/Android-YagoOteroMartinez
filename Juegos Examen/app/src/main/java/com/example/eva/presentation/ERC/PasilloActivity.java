package com.example.eva.presentation.ERC;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.FlechasFragment;
import com.example.eva.R;
import com.example.eva.Ruta;
import com.example.eva.presentation.mainActivity.ControllerMainActivity;

public class PasilloActivity extends AppCompatActivity {
    private ControllerPasillo controller;
    private Ruta ruta;
    private StringBuilder strBuilder = new StringBuilder();

    private TextView txtRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pasillo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtRespuesta = findViewById(R.id.txtRespuesta);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) ruta = (Ruta) bundle.getSerializable("ruta");


        controller = new ControllerPasillo(this, inicarFragment());


    }

    private FlechasFragment inicarFragment() {
        FragmentManager frgManager = getSupportFragmentManager();
        FlechasFragment frgFlechas = (FlechasFragment) frgManager.findFragmentById(R.id.frgFlechas);

        frgFlechas.setListener(new FlechasFragment.OnFlechListener() {
            @Override
            public void onBotonPulsado(FlechasFragment frgFlecha, char direccion) {
                actualizarTexto(direccion);
            }

            @Override
            public void onBotonEnterPulsado(FlechasFragment frgFlecha) {
                String respuesta = strBuilder.toString();
                int resta = (respuesta.length() - ruta.getRuta().length());
                int erroresPermitidos = ControllerMainActivity.fallosPermitidos();


                int errores = 0;
                if (resta >= 0) {
                    // Las respuestas es mayor o igual a 0
                    errores += iguales(ruta.getRuta(), respuesta);
                } else if (resta < 0) {
                    errores += iguales(respuesta, ruta.getRuta());
                }

                errores += Math.abs(resta);
                System.out.println("Errores: " + Math.abs(resta));

                if (errores == 0 || errores <= erroresPermitidos) {
                    Toast.makeText(getApplicationContext(), "Ha ganado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Perdiste la partida", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return frgFlechas;
    }

    private int iguales(String str1, String str2) {
        int errores = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                errores++;
            }
        }

        return errores;
    }

    private void actualizarTexto(char direccion) {
        strBuilder.append(direccion);
        txtRespuesta.setText(strBuilder.toString());
    }

}