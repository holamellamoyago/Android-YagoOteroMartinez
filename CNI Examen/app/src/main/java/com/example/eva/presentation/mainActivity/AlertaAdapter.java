package com.example.eva.presentation.mainActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eva.R;
import com.example.eva.clases.Alerta;

import java.util.ArrayList;
import java.util.List;

public class AlertaAdapter extends ArrayAdapter<Alerta> {
    private ArrayList<Alerta> alertas = new ArrayList<>();

    public AlertaAdapter(@NonNull Context context, @NonNull ArrayList<Alerta> alertas) {
        super(context, 0, alertas);
        this.alertas = alertas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_alerta, null, false);

        Alerta a = alertas.get(position);

        TextView txtMensaje = convertView.findViewById(R.id.txtAlerta);
        txtMensaje.setText(a.toString());

        if (a.isValida()) {
            txtMensaje.setTextColor(Color.parseColor("green"));
        } else {
            txtMensaje.setTextColor(Color.parseColor("red"));
        }


        return convertView;
    }
}
