package com.example.eva;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FrgBuscador extends Fragment {
    private Spinner sp_marca, sp_modelo;
    private OnFrgBuscador listener;
    private Button btn_pista;

    private SpinnerAdapter spinnerAdapter;

    public interface OnFrgBuscador{
        void onMarcaSeleccionada(FrgBuscador fragment, String marca);
        boolean onModeloSeleccionada(FrgBuscador fragment);
        void onPistaSolicitada(FrgBuscador fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_buscador, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sp_marca = view.findViewById(R.id.et_marca);
        sp_modelo = view.findViewById(R.id.et_modelo);
        btn_pista = view.findViewById(R.id.btn_pista);

        poblarSpinners();
    }

    private void poblarSpinners() {
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ControllerDatabase.cogerTotalMarcas());
    }

    public void setListener(OnFrgBuscador listener) {
        this.listener = listener;
    }

    public void desactivarSpinnerMarca(){

    }


}
