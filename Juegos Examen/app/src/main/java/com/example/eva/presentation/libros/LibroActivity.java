package com.example.eva.presentation.libros;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.FlechasFragment;
import com.example.eva.R;

public class LibroActivity extends AppCompatActivity {
    private ControllerLibro controller;
    private FlechasFragment frgFlechas;
    private TextView txtLibro, txtFila, txtColumna;
    private int fila = 0, columna = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new ControllerLibro(this);
        iniciarTextView();
        iniciarFragment();

    }

    private void iniciarFragment() {
        FragmentManager frgManager = getSupportFragmentManager();
        frgFlechas = (FlechasFragment) frgManager.findFragmentById(R.id.frgFlechas);

        frgFlechas.setListener(new FlechasFragment.OnFlechListener() {
            @Override
            public void onBotonPulsado(FlechasFragment frgFlecha, char direccion) {
                switch (direccion) {
                    // Esto es así porqué las filas cada vez que suman significa que estan mas abajo
                    case 'U': {
                        fila--;
                    }

                    break;
                    case 'D': {
                        fila++;
                    }
                    break;
                    case 'L': {
                        columna--;
                    }
                    break;
                    case 'R': {
                        columna++;
                    }
                    break;
                    default: {
                    }
                }

                actualizarTextViews();
                comprobarSiguientesPosiciones();
            }

            @Override
            public void onBotonEnterPulsado(FlechasFragment frgFlecha) {
            }
        });
    }

    private void actualizarTextViews() {
        String nombreLibroDatabase = controller.buscarLibro(fila, columna);
        txtLibro.setText(String.format(getString(R.string.libro), nombreLibroDatabase));

        txtFila.setText(String.valueOf(fila));
        txtColumna.setText(String.valueOf(columna));
    }

    private void iniciarTextView() {
        txtColumna = findViewById(R.id.txtColumna);
        txtFila = findViewById(R.id.txtFila);
        txtLibro = findViewById(R.id.txtLibro);

        txtColumna.setText(String.format(this.getString(R.string.columna), "0"));
        txtFila.setText(String.format(this.getString(R.string.fila), "0"));
        txtLibro.setText(String.format(this.getString(R.string.libro), ""));
    }

    public void comprobarSiguientesPosiciones() {
        if (controller.buscarLibro(fila, columna + 1) == null) {
            frgFlechas.btnDerecha.setVisibility(View.INVISIBLE);
        } else {
            frgFlechas.btnDerecha.setVisibility(View.VISIBLE);
        }

        if (controller.buscarLibro(fila, columna - 1) == null) {
            frgFlechas.btnIzquierda.setVisibility(View.INVISIBLE);
        } else {
            frgFlechas.btnIzquierda.setVisibility(View.VISIBLE);
        }

        if (controller.buscarLibro(fila + 1, columna) == null) {
            frgFlechas.btnAbajo.setVisibility(View.INVISIBLE);
        } else {
            frgFlechas.btnAbajo.setVisibility(View.VISIBLE);
        }

        if (controller.buscarLibro(fila - 1, columna + 1) == null) {
            frgFlechas.btnArriba.setVisibility(View.INVISIBLE);
        } else {
            frgFlechas.btnArriba.setVisibility(View.VISIBLE);
        }
    }


}