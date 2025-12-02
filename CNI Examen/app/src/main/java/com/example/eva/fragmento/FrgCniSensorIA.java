package com.example.eva.fragmento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;

public class FrgCniSensorIA extends Fragment {
    private EditText edTxt;
    private OnFrgCniSensorIAListener listener;

    interface OnFrgCniSensorIAListener {
        void onTextoCambiado(FrgCniSensorIA frgCniSensorIA);
    }

    public FrgCniSensorIA() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_cni_sensor_ia, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.edTxt = view.findViewById(R.id.edTxt);
    }


    public void setListener(OnFrgCniSensorIAListener listener) {
        this.listener = listener;
    }

    public void setTextos(String hint) {
        edTxt.setHint(hint);
    }
}





























