package com.example.eva.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.eva.R;
import com.example.eva.domain.AppDB;
import com.example.eva.fragments.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvAvisos;
    ArrayAdapter<String> adapterAvisos;
    ArrayList<String> palabrasBuscar = new ArrayList<>();

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
        lvAvisos = findViewById(R.id.lvAvisos);
        adapterAvisos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvAvisos.setAdapter(adapterAvisos);

        iniciarFragments();

    }

    private void iniciarFragments() {
        FragmentManager frgManager = getSupportFragmentManager();
        FrgEditTextCNI edEmisor = (FrgEditTextCNI) frgManager.findFragmentById(R.id.frgEdEmisor);
        FrgEditTextCNI  edDestinatario = (FrgEditTextCNI) frgManager.findFragmentById(R.id.frgEdDestinatario);
        FrgEditTextCNI edMensaje = (FrgEditTextCNI) frgManager.findFragmentById(R.id.frgMensaje);

        setListener(edEmisor, "Emisor... ");
        setListener(edDestinatario, "Destinatario... ");
        setListener(edMensaje, "Escribe tu mensaje...");


    }

    private void setListener (FrgEditTextCNI frgEditTextCNI, String hint) {
        frgEditTextCNI.setOnFrgEdTxtCNIListener(new FrgEditTextCNI.OnFrgEditTextCNIListener() {
            @Override
            public boolean onTextoEncontrado(String palabra) {
                if (adapterAvisos.getPosition(palabra) < 0)
                    adapterAvisos.add("Se encontro la palabra " + palabra);
                // Devuelve false si no existe
                return false;
            }
        });

//        frgEditTextCNI.setHint(hint);
    }


}