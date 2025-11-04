package com.example.eva.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;

public class FrgBotonLimitado extends Fragment {

    private OnFrgBLClickListener listener;
    private int maxClicks, numClicks = 0;

    public void setMaxClicks(int maxIntentos) {
        this.maxClicks = maxIntentos;
    }

    interface OnFrgBLClickListener {
        public boolean onClick();
    }

    Button boton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.frg_boton_limitado, null);

        boton = layout.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click();
            }
        });

        return layout;
    }

    private void Click() {
        numClicks++;
        if (numClicks <= maxClicks) {
            if (!listener.onClick()) {
                numClicks--;
            }
        }
    }

    public void setText(String texto) {
        boton.setText(texto);
    }

    public void setOnFrgBotonLimitadoListener(OnFrgBLClickListener listener) {
        this.listener = listener;
    }
}
