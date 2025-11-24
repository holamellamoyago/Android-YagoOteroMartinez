package com.example.eva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class FrgDado extends Fragment {
    private Dado dado;
    private Button button;
    private onFrgDadoListener listener;

    interface onFrgDadoListener{
        void onDadoTirado(FrgDado frgDado, int numero);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.frg_dado, null);
        button = view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        button.setText(R.string.jugar);
        button.setOnClickListener(v -> listener.onDadoTirado(this, new Random().nextInt(6)));
    }

    public void setListener(onFrgDadoListener listener) {
        this.listener = listener;
    }


    public void setTextoBoton(String texto){
        button.setText(texto);
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }
}
