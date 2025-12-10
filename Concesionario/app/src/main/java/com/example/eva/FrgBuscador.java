package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FrgBuscador extends Fragment {
    private Spinner sp_marca, sp_modelo;
    private OnFrgBuscador listener;
    private Button btn_pista;
    private TextView tv_vidas;

    private SpinnerAdapter spinnerAdapterMarcas;

    private int vidasRestantes;


    public interface OnFrgBuscador {
        void onMarcaSeleccionada(FrgBuscador fragment, String marca);

        boolean onModeloSeleccionada(FrgBuscador fragment, String modelo);

        void onPistaSolicitada(FrgBuscador fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_buscador, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_pista = view.findViewById(R.id.btn_pista);
        tv_vidas = view.findViewById(R.id.tv_vidas);

        sp_marca = view.findViewById(R.id.et_marca);

        //sp_marca.setOnItemClickListener((parent, view1, position, id) -> marcaSeleccionada(position));
        sp_marca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String marca = parent.getSelectedItem().toString();
                marcaSeleccionada(marca);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        sp_modelo = view.findViewById(R.id.et_modelo);
        sp_modelo.setVisibility(View.INVISIBLE);
        sp_modelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String modelo = parent.getSelectedItem().toString();
                if (listener.onModeloSeleccionada(FrgBuscador.this, modelo)) {
                    mostrarPantallaResultado(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        actualizarVidas(ControllerDatabase.TOTAL_VIDAS);
        poblarSpinners();
    }

    // Esta función llama al método del listener
    private void marcaSeleccionada(String marca) {
        listener.onMarcaSeleccionada(this, marca);
    }

    private void poblarSpinners() {
        spinnerAdapterMarcas = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ControllerDatabase.cogerTotalMarcas());
        sp_marca.setAdapter(spinnerAdapterMarcas);
        //sp_marca.setSelection();
        sp_marca.setSelected(false);
    }

    public void setListener(OnFrgBuscador listener) {
        this.listener = listener;
    }

    public void actualizarVidas(int vidas) {
        vidasRestantes = vidas;
        tv_vidas.setText(vidasRestantes + "");
    }

    public boolean restarVida() {
        int vidas = Integer.valueOf(tv_vidas.getText().toString());
        int vidasRestantes = vidas - 1;
        System.out.println(vidasRestantes);

        if (vidasRestantes == 0) {
            mostrarPantallaResultado(false);
            return true;
        }

        actualizarVidas(vidasRestantes);
        return false;
    }

    public void mostrarPantallaResultado(boolean gano) {
        Intent resultadoActivity = new Intent(getContext(), ResultadoActivity.class);

        if (gano) {
            resultadoActivity.putExtra("resultado", "Gano la partida");
        } else {
            resultadoActivity.putExtra("resultado", "Perdio la partida");
        }

        startActivity(resultadoActivity);
    }

    public void cambiarSpinner() {
        sp_marca.setEnabled(false);
        sp_modelo.setVisibility(View.VISIBLE);

        SpinnerAdapter spinnerAdapterModelos = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ControllerDatabase.cogerTotalCoches());
        sp_modelo.setAdapter(spinnerAdapterModelos);

    }


}
