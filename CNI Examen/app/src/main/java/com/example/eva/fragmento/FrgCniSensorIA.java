package com.example.eva.fragmento;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
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
    private TextWatcher textWatcher;

    private String hint;

    interface OnFrgCniSensorIAListener {
        void onTextoCambiado(FrgCniSensorIA frgCniSensorIA, String texto);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.frg_cni_sensor_ia, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        edTxt = view.findViewById(R.id.edTxt);
        iniciarTextWatcher();


        // Error 1:  problema al inicar variables de widgets
        if (hint != null) setTextos(hint);
    }

    private void iniciarTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String texto = String.valueOf(s);
                listener.onTextoCambiado(FrgCniSensorIA.this, String.valueOf(edTxt.getText()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        edTxt.addTextChangedListener(textWatcher);

    }


    public void setListener(OnFrgCniSensorIAListener listener) {
        this.listener = listener;
    }

    public void setTextos(String hint) {
        if (edTxt != null) {
            edTxt.setHint(hint);
        } else {
            this.hint = hint;
        }
    }

    public String getText() {
        return edTxt.getText().toString();
    }
}





























