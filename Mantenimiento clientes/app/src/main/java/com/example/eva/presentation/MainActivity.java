package com.example.eva.presentation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.data.AsistenteDB;
import com.example.eva.data.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvClientes;
    FloatingActionButton btnAddClient;
    ArrayList<Cliente> clientes = new ArrayList<>();

    AsistenteDB asistente;
    SQLiteDatabase db;
    Intent intent;


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

        lvClientes = findViewById(R.id.lvClientes);
        btnAddClient = findViewById(R.id.btnAddClient);

        asistente = new AsistenteDB(this);
        db = asistente.getReadableDatabase();
        recorrerClientes();

        ArrayAdapter<Cliente> adapterClientes = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        lvClientes.setAdapter(adapterClientes);
        lvClientes.setOnItemClickListener((parent, view, position, id) -> modificarCliente(position));


        intent = new Intent(this, AddClient.class);
        btnAddClient.setOnClickListener(v -> startActivity(intent));

        asistente.close();
    }

    private void recorrerClientes() {
        Cursor cursor = db.rawQuery("SELECT * FROM clientes", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String apellidos = cursor.getString(2);

            clientes.add(new Cliente(id, nombre, apellidos));

        }
    }

    private void modificarCliente(int i){
        Cliente c = clientes.get(i);
        intent.putExtra("cliente", c);
        startActivity(intent);
    }

}