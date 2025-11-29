package com.example.eva.domain.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eva.R;
import com.example.eva.presentation.MainActivity;

import java.util.List;

public class PilotoAdapter extends ArrayAdapter<Piloto> {
    private Context context;
    private List<Piloto> pilotos;
    private TextView tvEstado;

    public PilotoAdapter(@NonNull Context context, @NonNull List<Piloto> pilotos) {
        super(context, 0, pilotos);
        this.context = context;
        this.pilotos = pilotos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_piloto, null, false);
        }

        TextView tvPosicion = convertView.findViewById(R.id.tvPosicion);
        TextView tvNombreCompleto = convertView.findViewById(R.id.tvNombreCompleto);
        tvEstado = convertView.findViewById(R.id.tvEstado);

        /*
            3. Cuando crear tus propios adapters hay que tener cuidado por qué no te avisa de cuando
            peudes o no poner un Object ejemnplo: (int -> String)

            tvPosicion.setText(3) -> ERROR

         */

        Piloto p = pilotos.get(position);
        tvPosicion.setText(p.getPosicionActual() + "º");
        tvNombreCompleto.setText(p.toString());

        int posicionActual = p.getPosicionActual();
        Integer posicionAnterior = p.getPosicionAnterior();
        if (posicionAnterior != null) {
            if (posicionAnterior < posicionActual) {
                setBajoPosicion();
            } else if (posicionAnterior > posicionActual) {
                setPosicionSubida();
            } else {
                // TODO Se mantiene
            }
        }


        return convertView;

    }

    public void setPosicionSubida() {
        tvEstado.setText("Subió una posición");
    }

    public void setBajoPosicion() {
        tvEstado.setText("Bajo una posición");
    }
}
