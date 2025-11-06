package com.example.eva.presentation;

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
import com.example.eva.fragments.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvAvisos;
    ArrayAdapter<String> adapterAvisos;

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


        iniciarFragments();
        lvAvisos.findViewById(R.id.lvAvisos);
        adapterAvisos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvAvisos.setAdapter(adapterAvisos);

    }

    private void iniciarFragments() {
        FragmentManager frgManager = getSupportFragmentManager();
        FrgEditTextCNI frgDestinatario = (FrgEditTextCNI) frgManager.findFragmentById(R.id.frgEdTxtCNI);

        frgDestinatario.setOnFrgEdTxtCNIListener(new FrgEditTextCNI.OnFrgEditTextCNIListener() {
            @Override
            public boolean onTextoEncontrado(String palabra) {
                adapterAvisos.add(palabra);
                // Devuelve false si no existe
                return false;
            }
        });
    }


}