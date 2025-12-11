package com.example.eva.presentation.adapters;

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
import com.example.eva.clases.Canal;

import java.util.ArrayList;
import java.util.List;

public class CanalAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Canal> canales;

    private TextView tv_nombre_canal;

    public CanalAdapter(@NonNull Context context,  @NonNull ArrayList<Canal> canales) {
        super(context, 0, canales);
        this.context = context;
        this.canales = canales;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_canal, null, false);
        }

        tv_nombre_canal = convertView.findViewById(R.id.tv_nombre_canal);






        return convertView;
    }
}

















