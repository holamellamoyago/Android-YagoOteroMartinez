package com.example.eva.presentation.ERC;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.eva.R;
import com.example.eva.Ruta;
import com.example.eva.database.AppDB;
import com.example.eva.database.ControllerDatabase;

import java.util.ArrayList;

public class SeleccionadorJuegoActivity extends AppCompatActivity {
    private ListView lvRutas;
    private ArrayAdapter<Ruta> adapterRuta;

    private ControllerDatabase controllerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionador_juego);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controllerDatabase = new ControllerDatabase(new AppDB(this).getWritableDatabase());
        ArrayList<Ruta> rutas = controllerDatabase.obtenerRutas();

        adapterRuta = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rutas);

        lvRutas = findViewById(R.id.lvRutas);
        lvRutas.setAdapter(adapterRuta);



    }


}