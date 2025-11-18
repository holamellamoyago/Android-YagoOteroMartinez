package com.example.eva.dados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;

import java.util.ArrayList;

public class FrgDados extends Fragment {

    onFrgDatosListener listener;
    Button button;

    public interface onFrgDatosListener {
        void onTirar(FrgDados dado, int numero, ArrayList<Integer> historial);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_telefono, null);

        button = view.findViewById(R.id.btn);

        return view;
    }

    public void setOnFrgDatosListener(onFrgDatosListener listener) {
        this.listener = listener;
    }
}
