package com.example.eva.presentation.mainActivity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eva.FlechasFragment;
import com.example.eva.Partida;

import java.util.ArrayList;

public class ControllerMainActivity {
    private Context context;
    private ArrayList<Partida> partidas = new ArrayList<>();

    private TextView txtInformacion;
    private static EditText numErroresMAX;

    public ControllerMainActivity(Context context) {
        this.context = context;
        partidas = new ArrayList<>();
    }

    public EditText getNumErroresMAX() {
        return numErroresMAX;
    }

    public static int fallosPermitidos() {
        return Integer.valueOf(numErroresMAX.getText().toString());
    }

    public void setNumErroresMAX(EditText numErroresMAX) {
        this.numErroresMAX = numErroresMAX;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public TextView getTxtInformacion() {
        return txtInformacion;
    }

    public void setTxtInformacion(TextView txtInformacion) {
        this.txtInformacion = txtInformacion;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextWatcher getTextWatcher() {
        return textWatcher;
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }

    // TextWatcher

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            actualizarTextoInformacion(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void actualizarTextoInformacion(String str) {
        final String ENUNCIADO = "HAY %1$d partidas con ese enunciado";

        int contador = 0;
        for (Partida partida : partidas) {
            if (partida.getDificultad() == Integer.valueOf(str)) {
                contador++;
            }
        }

        txtInformacion.setText(String.format(ENUNCIADO, contador));
    }
}
