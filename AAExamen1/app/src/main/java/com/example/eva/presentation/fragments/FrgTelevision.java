package com.example.eva.presentation.fragments;

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

import com.example.eva.R;
import com.example.eva.clases.Canal;
import com.example.eva.presentation.adapters.CanalSpinnerAdapter;
import com.example.eva.presentation.controllers.ControllerFrgTelevision;

import java.util.ArrayList;
import java.util.Objects;

public class FrgTelevision extends Fragment {
    private ControllerFrgTelevision controller;

    private Button btn_mirar, btn_suscribirse;
    private TextView tv_informacion;
    private Spinner sp_canales;

    private ArrayList<Canal> canales = new ArrayList<>();

    // Adapters
    private SpinnerAdapter canalAdapter;
    private CanalSpinnerAdapter canalSpinnerAdapter;

    private onFrgTelevisionListener listener;

    // listener
    private Canal antiguoCanal;

    private boolean spIniciado = false;

    public interface onFrgTelevisionListener {
        void OnEmpezarMirarCanal(FrgTelevision frg, Canal canal);

        void OnSuscribirseCanal(FrgTelevision frg, Canal canal);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_television, null, false);

        btn_mirar = view.findViewById(R.id.btn_mirar);
        tv_informacion = view.findViewById(R.id.tv_informacion);
        sp_canales = view.findViewById(R.id.sp_canales);
        btn_suscribirse = view.findViewById(R.id.btn_suscribirse);

//        btn_suscribirse.setVisibility(View.INVISIBLE);
        btn_suscribirse.setEnabled(false);



        sp_canales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spIniciado) {
                    spIniciado = true;
                    return;
                }

                listener.OnEmpezarMirarCanal(FrgTelevision.this, (Canal) sp_canales.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        canalAdapter = new ArrayAdapter<Canal>(getContext(), android.R.layout.simple_spinner_item);
        canalSpinnerAdapter = new CanalSpinnerAdapter(getContext(), canales);

        sp_canales.setAdapter(new ArrayAdapter<Canal>(getContext(), android.R.layout.simple_list_item_1, canales));
        //sp_canales.setAdapter(ckanalAdapter);

        btn_mirar.setOnClickListener(view1 -> {
            listener.OnEmpezarMirarCanal(this, (Canal) sp_canales.getSelectedItem());
        });

        btn_suscribirse.setOnClickListener(view1 -> listener.OnSuscribirseCanal(this, (Canal) sp_canales.getSelectedItem()));

    }

    public void setCanales(ArrayList<Canal> canales) {
        this.canales = canales;
    }

    public void setListener(onFrgTelevisionListener listener) {
        this.listener = listener;
    }

    public void actualizarTexto(String mensaje) {
        tv_informacion.setText(mensaje);
    }

    public void hacerVisibleBoton() {
//        btn_suscribirse.setVisibility(View.VISIBLE);
        btn_suscribirse.setEnabled(true);
    }

    public void hacerInVisibleBoton() {
//        btn_suscribirse.setVisibility(View.VISIBLE);
        btn_suscribirse.setEnabled(false);
    }

    public void hacerInvisibleBotonMirar() {
//        btn_suscribirse.setVisibility(View.VISIBLE);
        btn_mirar.setEnabled(false);
    }

    public void hacervisibleBotonMirar() {
//        btn_suscribirse.setVisibility(View.VISIBLE);
        btn_mirar.setEnabled(true);
    }

}
