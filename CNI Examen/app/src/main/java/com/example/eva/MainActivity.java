package com.example.eva;


import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.clases.Alerta;
import com.example.eva.database.AppDB;
import com.example.eva.database.GestorDatabase;
import com.example.eva.fragmento.ControllerFrgCni;

public class MainActivity extends AppCompatActivity {
    ControllerFrgCni controllerFrgCni = new ControllerFrgCni(getSupportFragmentManager(), this);

    private static ListView lvAlertas;
    private static ArrayAdapter<Alerta> alertaArrayAdapter;


    private GestorDatabase gestorDatabase;

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

        controllerFrgCni.iniciarFragments();

        // Inicio el listview
        lvAlertas = findViewById(R.id.lvAlertas);
        alertaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GestorAlertas.alertas);
        lvAlertas.setAdapter(alertaArrayAdapter);
    }

    public static void reiniciarList() {
        alertaArrayAdapter.notifyDataSetChanged();
    }


}




























