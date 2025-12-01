package com.example.eva.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;
import com.example.eva.controller.GestorDatabase;
import com.example.eva.domain.model.Piloto;
import com.example.eva.domain.model.PilotoAdapter;
import com.example.eva.presentation.perfil.PerfilActivity;

import java.util.ArrayList;

public class FrgPilotos extends Fragment {
    private ListView lvPilotos;
    public onPilotosListener listener;
    private PilotoAdapter pilotoAdapter;

    private GestorDatabase DB_CONTROLLER;
    private ArrayList<Piloto> pilotos = new ArrayList<>();

    private TextView tvTitleVueltas;
    private int vuelta = 0;


    // Los fragments necesitan un constructor vacío por defecto
    public FrgPilotos() {
    }

    interface onPilotosListener {
        void onPosicionCambiada(FrgPilotos frgPilotos);

        void onCarreraTerminada(FrgPilotos frgPiloto);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
            1.
            Un fallo que tuve aquí fue que al crear el controller estaba iniciandolo directamente
            afuera (donde private), esto causaba que al coger el context diese null porqué se creaba
            antes el controller el context

            2.
            Decidí coger aquí a los pilotos por qué me dio la gana, cargarlos aquí y pasarlos al adapter abajo
         */
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_pilotos, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lvPilotos = view.findViewById(R.id.lvPilotos);
        tvTitleVueltas = view.findViewById(R.id.tvTitleVueltas);

        getPilotos();

        lvPilotos.setOnItemClickListener((adapterView, view1, i, l) -> mostrarPerfilPiloto(pilotos.get(i)));

    }

    private void actualizarVueltas() {
        if (vuelta == 0) {
            tvTitleVueltas.setText("La carrera todavía no comenzó");
        } else {
            tvTitleVueltas.setText(getString(R.string.title_vuelta, vuelta, MainActivity.NUMERO_VUELTAS));
        }

        vuelta++;
    }


    public void getPilotos() {
        if (DB_CONTROLLER == null) DB_CONTROLLER = new GestorDatabase(getActivity());

        if (pilotoAdapter == null) {
            DB_CONTROLLER.getPilotos().forEach(piloto -> pilotos.add(piloto));
            pilotoAdapter = new PilotoAdapter(getActivity(), pilotos);
            lvPilotos.setAdapter(pilotoAdapter);
        }

        actualizarVueltas();

        // Se cogen los pilotos, estos con la posicion anterior
        ArrayList<Piloto> PILOTOS_ACTUALES = DB_CONTROLLER.getPilotos();

        pilotoAdapter.clear(); // aquí se deberían de eliminar todos

        // se añaden al adapter , ¿Cuando se actualiza la lista?
        pilotoAdapter.addAll(PILOTOS_ACTUALES);

        pilotoAdapter.notifyDataSetChanged();

    }

    public void setListener(onPilotosListener listener) {
        this.listener = listener;
    }

    private void mostrarPerfilPiloto(Piloto piloto) {
        Intent perfilIntent = new Intent(getContext(), PerfilActivity.class);
        perfilIntent.putExtra("piloto", piloto);

        startActivity(perfilIntent);
    }


}
