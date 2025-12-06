package com.example.eva.presentation.mainActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.GestorAlertas;
import com.example.eva.R;
import com.example.eva.database.AppDB;
import com.example.eva.database.GestorDatabase;
import com.example.eva.fragmento.ControllerFrgCni;

public class MainActivity extends AppCompatActivity {
    ControllerFrgCni controllerFrgCni;
    GestorDatabase gestorDatabase;

    private static ListView lvAlertas;
    private Button btnResetear;

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

        gestorDatabase = new GestorDatabase(new AppDB(this).getWritableDatabase());
        controllerFrgCni = new ControllerFrgCni(getSupportFragmentManager(), this);

        controllerFrgCni.iniciarFragments();

        btnResetear = findViewById(R.id.btnResetear);
        btnResetear.setOnClickListener(v -> GestorAlertas.limpiarAlertas(gestorDatabase));

        // Inicio el listview
        lvAlertas = findViewById(R.id.lvAlertas);
        GestorAlertas.alertaArrayAdapter = new AlertaAdapter(this, GestorAlertas.alertas);
        lvAlertas.setAdapter(GestorAlertas.alertaArrayAdapter);
        GestorAlertas.getAlertasFromDatabase(gestorDatabase.getAlertas());


    }

    public static void reiniciarList() {
        GestorAlertas.alertaArrayAdapter.notifyDataSetChanged();
    }


}




























