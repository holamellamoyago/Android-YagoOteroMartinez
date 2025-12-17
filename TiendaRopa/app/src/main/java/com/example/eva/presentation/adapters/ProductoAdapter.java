package com.example.eva.presentation.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eva.R;
import com.example.eva.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private ArrayList<Producto> productos;

    TextView tv_title, tv_subtitle, tv_precio;
    ImageView iv_imagen;

    public ProductoAdapter(@NonNull Context context, @NonNull ArrayList<Producto> productos) {
        super(context, 0, productos);
        this.productos = productos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.producto_adapter, null, false);

        tv_title = view.findViewById(R.id.tv_title);
        tv_subtitle = view.findViewById(R.id.tv_subtitle);
        tv_precio = view.findViewById(R.id.tv_precio);
        iv_imagen = view.findViewById(R.id.iv_imagen);

        Producto pr = productos.get(position);

        tv_title.setText(pr.getTitulo());
        tv_subtitle.setText(pr.getSubtitulo());
        tv_precio.setText(String.valueOf(pr.getPrecio()));

        int id = getContext().getResources().getIdentifier(pr.getRutaImagen(), "drawable", getContext().getPackageName());
        iv_imagen.setImageResource(id);

        return view;

    }
}
