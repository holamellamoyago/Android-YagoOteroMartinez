package com.example.eva.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.Utils;
import com.example.eva.clases.Canal;
import com.example.eva.database.ControllerDatabase;

public class SuscribirseActivity extends AppCompatActivity {
    private TextView tv_titulo, tv_precio, tv_visualizaciones, tv_precio_visualizacviones;
    private EditText ed_visualizaciones;

    private Button btn_contratar;

    private Canal canal;
    private TextWatcher textWatcher;

    private ControllerDatabase controllerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suscribirse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            canal = (Canal) bundle.getSerializable("canal");
        }

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualizarPrecioVisualizaciones();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        controllerDatabase = new ControllerDatabase(getApplicationContext());

        iniciarWidgets();
        formatearTextos();
        ed_visualizaciones.addTextChangedListener(textWatcher);

        btn_contratar.setOnClickListener(view -> {
            if (comprobacionescontratacion()) {
                suscribirseCanal();
            }
        });




    }

    private void suscribirseCanal() {
        int numvisualizacviones = Integer.valueOf(ed_visualizaciones.getText().toString());
        canal.setNumVisualizacionesMaximas(numvisualizacviones);

        controllerDatabase.suscribirseCanal(canal);

        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
    }

    private boolean comprobacionescontratacion() {
        int numvisualizacviones = Integer.valueOf(ed_visualizaciones.getText().toString());
        if (numvisualizacviones <= 0) {
            Utils.mostrarMensaje(getApplicationContext(), "No puedes contratar un paquete sin visuazalicioens o negativo");
            return false;
        }

        return true;

    }

    private void actualizarPrecioVisualizaciones() {
        String coste = String.valueOf(canal.getPrecio() * Double.valueOf(ed_visualizaciones.getText().toString()));

        String visPrecio = getString(R.string.str_precio_visualizaciones, coste);
        tv_precio_visualizacviones.setText(visPrecio);

    }

    private void formatearTextos() {
        String titulo = String.format(getString(R.string.str_suscribirse), canal.getNombre());
        String precio = String.format(getString(R.string.str_precio), String.valueOf(canal.getPrecio()));

        //String visMax = String.valueOf(canal.getNumVisualizacionesMaximas());
        //String viosualziaciones = getString(R.string.str_visualizaciones);


        tv_titulo.setText(titulo);
        tv_precio.setText(precio);
        tv_precio_visualizacviones.setText(String.format(getString(R.string.str_precio), ""));
        ed_visualizaciones.setHint("¿Cuantas visualizaciones?");
    }

    private void iniciarWidgets() {
        tv_titulo = findViewById(R.id.tv_titulo);
        tv_precio = findViewById(R.id.tv_precio);
        tv_visualizaciones = findViewById(R.id.tv_visualizaciones);
        tv_precio_visualizacviones = findViewById(R.id.tv_precio_visualizacviones);
        ed_visualizaciones = findViewById(R.id.ed_visualizaciones);

        btn_contratar = findViewById(R.id.btn_contratar);

    }
}