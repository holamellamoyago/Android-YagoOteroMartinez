package com.example.eva.ui.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.eva.R;
import com.example.eva.domain.model.Mision;
import com.example.eva.domain.model.usecase.GeneradorMisionThread;
import com.example.eva.ui.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Mision> adapterMisiones;
    private ArrayList<Mision> misiones;
    private ListView lvMisiones;

    private MainActivityViewModel viewModel;
    private Handler handler;
    private Runnable runnable;
    private Mision mision;

    private int contador = 0;

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

        lvMisiones = findViewById(R.id.lvMisiones);


        GeneradorMisionThread generador = new GeneradorMisionThread();
        generador.start();

        viewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MainActivityViewModel.class);

        misiones = viewModel.leerMisiones();

        adapterMisiones = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, misiones);
        lvMisiones.setAdapter(adapterMisiones);

        generarMisiones();
        handler.post(runnable);




    }


    private void generarMisiones (){
         handler = new Handler(Looper.getMainLooper());
         runnable = new Runnable() {
             @Override
             public void run() {
                 mision = new Mision("Accidente nº" + contador++);
                 viewModel.guardarMision(mision);

                 handler.postDelayed(this,5000);
             }
         };
    }

}