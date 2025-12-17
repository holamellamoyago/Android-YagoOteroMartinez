package com.example.eva.presentation.screens;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.controllers.ControllerDatabase;
import com.example.eva.controllers.ControllerGalleryActivity;
import com.example.eva.controllers.GestorServidor;
import com.example.eva.presentation.adapters.ProductoAdapter;

public class GalleryActivity extends AppCompatActivity {
    private GestorServidor gestorServidor;
    private ListView lv_productos;
    private ControllerGalleryActivity controller;

    private ControllerDatabase controllerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new ControllerGalleryActivity();

        lv_productos = findViewById(R.id.lv_productos);
        controller.productoAdapter = new ProductoAdapter(getApplicationContext(), ControllerDatabase.productos);
        lv_productos.setAdapter(controller.productoAdapter);

        gestorServidor = GestorServidor.getInstance();

        gestorServidor.solicitarProductos((gestorServidor1, productos) -> {
            ControllerDatabase.productos.addAll(productos);
            System.out.println("Productos: " + ControllerDatabase.productos);

            runOnUiThread(() -> controller.productoAdapter.notifyDataSetChanged());

        });


    }

}