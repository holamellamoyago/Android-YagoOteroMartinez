package com.example.eva.presentation.resultado;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;
import com.example.eva.data.openF1.GestorOpenF1;
import com.example.eva.domain.model.Piloto;

import java.sql.SQLOutput;

public class FrgPerfil extends Fragment {
    private GestorOpenF1 gestorOpenF1 = new GestorOpenF1();
    private Piloto piloto;

    private TextView txtBroadcastName, txtTeamName, txtNumber, txtFullName;
    private ImageView imvPiloto;

    public FrgPerfil(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_perfil, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        txtBroadcastName = view.findViewById(R.id.txtBroadcastName);
        txtNumber = view.findViewById(R.id.txtNumber);
        txtTeamName = view.findViewById(R.id.txtTeamName);
        txtFullName = view.findViewById(R.id.txtFullName);

        if (piloto != null) {
            actualizarDatos();
        }


    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    private void actualizarDatos() {
        txtBroadcastName.setText(piloto.getBroadcastName());
        txtFullName.setText(piloto.getFullName());
        txtNumber.setText(String.valueOf(piloto.getDriverNumber()));
        txtTeamName.setText(piloto.getEquipo().getTeamName());
    }
}



























