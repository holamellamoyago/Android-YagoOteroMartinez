package com.example.eva;

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

public class FrgBuscador extends Fragment {
    private Spinner sp_marca, sp_modelo;
    private OnFrgBuscador listener;

    private boolean spinnerIniciado = false;
    private boolean spinnerModeloIniciado = false;

    public interface OnFrgBuscador {
        boolean onMarcaSeleccionada(FrgBuscador fragment, String marca);

        boolean onModeloSeleccionada(FrgBuscador fragment, String modelo);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_buscador, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        sp_marca = view.findViewById(R.id.et_marca);
        sp_marca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ControllerDatabase.cogerTotalMarcas()));
        sp_marca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinnerIniciado) {
                    spinnerIniciado = true;
                    return;
                }

                String marca = parent.getSelectedItem().toString();
                if (listener.onMarcaSeleccionada(FrgBuscador.this, marca)) {
                    cambiarSpinner();
                    spinnerIniciado = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        sp_modelo = view.findViewById(R.id.et_modelo);
        sp_modelo.setVisibility(View.INVISIBLE);
        sp_modelo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ControllerDatabase.cogerTotalCoches()));


        sp_modelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinnerModeloIniciado) {
                    spinnerModeloIniciado = true;
                    return;
                }

                String modelo = parent.getSelectedItem().toString();
                listener.onModeloSeleccionada(FrgBuscador.this, modelo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Esta función llama al método del listener
    private void marcaSeleccionada(String marca) {

    }

    public void setListener(OnFrgBuscador listener) {
        this.listener = listener;
    }


    private void cambiarSpinner() {
        sp_marca.setEnabled(false);
        sp_modelo.setVisibility(View.VISIBLE);

    }


}
