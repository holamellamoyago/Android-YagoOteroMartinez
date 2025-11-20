package com.example.eva;

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
import java.util.Random;

public class FrgDados extends Fragment {

    public onFrgDatosListener listener;
    private Button button;
    private final int numCaras = 6;

    public interface onFrgDatosListener {
        void onTirar(FrgDados dado, int numero);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_dados, null);

        button = view.findViewById(R.id.btn);
        button.setVisibility(View.INVISIBLE);

        return view;
    }

    public void setOnFrgDatosListener(onFrgDatosListener listener) {
        this.listener = listener;
    }

    public void setTextButton(String texto) {
        button.setText(texto);
    }

    public void setVisibility() {
        button.setVisibility(View.VISIBLE);
    }
}
