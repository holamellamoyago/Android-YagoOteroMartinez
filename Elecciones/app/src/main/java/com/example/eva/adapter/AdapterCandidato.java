package com.example.eva.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eva.R;
import com.example.eva.model.Candidato;
import com.example.eva.utils.*;

import java.util.ArrayList;

public class AdapterCandidato extends ArrayAdapter<Candidato> {

    public static class ViewHolder {
        TextView texto;
    }

    private Context contexto;
    private ArrayList<Candidato> candidatos;

    public AdapterCandidato(@NonNull Context contexto, ArrayList<Candidato> candidatos) {
        super(contexto, R.layout.fila_candidato, candidatos);
        this.contexto = contexto;
        this.candidatos = candidatos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Candidato candidato = (Candidato) candidatos.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(R.layout.fila_candidato, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.texto = (TextView) convertView.findViewById(R.id.txtNombreCandidato);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.texto.setText(candidato.getNombre());
        viewHolder.texto.setBackgroundColor(Utils.buscarColorPartido(candidato.getPartido_id()));

        return convertView;
    }
}































