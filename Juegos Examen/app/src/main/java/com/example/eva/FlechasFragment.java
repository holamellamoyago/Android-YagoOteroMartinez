package com.example.eva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FlechasFragment extends Fragment {
    public Button btnArriba, btnAbajo, btnDerecha, btnIzquierda, btnEnter;
    private OnFlechListener listener;

    public interface OnFlechListener {
        void onBotonPulsado(FlechasFragment frgFlecha, char direccion);

        void onBotonEnterPulsado(FlechasFragment frgFlecha);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_flechas, null, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        iniciarBotones(view);
    }

    private void iniciarBotones(View view) {
        btnArriba = view.findViewById(R.id.btnArriba);
        btnAbajo = view.findViewById(R.id.btnAbajo);
        btnEnter = view.findViewById(R.id.btnEnter);
        btnIzquierda = view.findViewById(R.id.btnIzquierda);
        btnDerecha = view.findViewById(R.id.btnDerecha);


        btnArriba.setOnClickListener(v -> listener.onBotonPulsado(this, 'U')); // ingles
        btnAbajo.setOnClickListener(v -> listener.onBotonPulsado(this, 'D'));
        btnIzquierda.setOnClickListener(v -> listener.onBotonPulsado(this, 'L'));
        btnDerecha.setOnClickListener(v -> listener.onBotonPulsado(this, 'R'));

        btnEnter.setOnClickListener(v -> listener.onBotonEnterPulsado(this));
    }


    public void setListener(OnFlechListener listener) {
        this.listener = listener;
    }

    public Button getBtnArriba() {
        return btnArriba;
    }

    public void setBtnArriba(Button btnArriba) {
        this.btnArriba = btnArriba;
    }

    public Button getBtnAbajo() {
        return btnAbajo;
    }

    public void setBtnAbajo(Button btnAbajo) {
        this.btnAbajo = btnAbajo;
    }

    public Button getBtnDerecha() {
        return btnDerecha;
    }

    public void setBtnDerecha(Button btnDerecha) {
        this.btnDerecha = btnDerecha;
    }

    public Button getBtnIzquierda() {
        return btnIzquierda;
    }

    public void setBtnIzquierda(Button btnIzquierda) {
        this.btnIzquierda = btnIzquierda;
    }

    public Button getBtnEnter() {
        return btnEnter;
    }

    public void setBtnEnter(Button btnEnter) {
        this.btnEnter = btnEnter;
    }
}
