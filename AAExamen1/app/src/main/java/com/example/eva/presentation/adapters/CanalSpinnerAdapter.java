package com.example.eva.presentation.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.eva.R;
import com.example.eva.clases.Canal;
import android.graphics.Color;


import java.util.ArrayList;

public class CanalSpinnerAdapter implements SpinnerAdapter {
    private ArrayList<Canal> canales = new ArrayList<>();
    private Context context;
    private TextView tv_nombre_canal;

    public CanalSpinnerAdapter(Context context, ArrayList<Canal> canales) {
        this.canales = canales;
        this.context = context;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_canal, null, false);

        tv_nombre_canal = view.findViewById(R.id.tv_nombre_canal);

        Canal canal = canales.get(i);

        tv_nombre_canal.setText(canal.getNombre());
        if (canal.isPublico()) {
            tv_nombre_canal.setTextColor(Color.parseColor("GREEN"));
        } else {
            if (canal.isSuscrito()) {
                tv_nombre_canal.setTextColor(Color.parseColor("YELLOW"));
            } else {
                tv_nombre_canal.setTextColor(Color.parseColor("RED"));
            }
        }

        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_canal, null, false);

        tv_nombre_canal = view.findViewById(R.id.tv_nombre_canal);

        Canal canal = canales.get(i);

        tv_nombre_canal.setText(canal.getNombre());
        if (canal.isPublico()) {
            tv_nombre_canal.setTextColor(Color.parseColor("GREEN"));
        } else {
            if (canal.isSuscrito()) {
                tv_nombre_canal.setTextColor(Color.parseColor("YELLOW"));
            } else {
                tv_nombre_canal.setTextColor(Color.parseColor("RED"));
            }
        }

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
