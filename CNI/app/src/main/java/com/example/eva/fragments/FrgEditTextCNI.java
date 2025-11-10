package com.example.eva.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;
import com.example.eva.domain.Databasecontroller;

import java.util.ArrayList;

public class FrgEditTextCNI extends Fragment {

    private OnFrgEditTextCNIListener listener;
    private EditText edTxtDestinatario;
    private ArrayList<String> palabrasBuscar;

    public interface OnFrgEditTextCNIListener {
        boolean onTextoEncontrado(String palabra);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.frg_edit_text_cni, null);
        edTxtDestinatario = (EditText) layout.findViewById(R.id.edTxtDestinatario);
        palabrasBuscar = Databasecontroller.getPalabras(getContext());

        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println(String.valueOf(charSequence));
                haCambiado(charSequence.toString());
            }
        };

        edTxtDestinatario.addTextChangedListener(watcher);
    }

    private void haCambiado(String textoEscrito) {
        StringBuilder palabrasTotales = new StringBuilder();
        for (int i = 0; i < palabrasBuscar.size(); i++) {
            palabrasTotales.append(palabrasBuscar.get(i));
        }

        for (int i = 0; i < palabrasBuscar.size(); i++) {
            if (textoEscrito.toLowerCase().contains(palabrasBuscar.get(i).toLowerCase())){
                listener.onTextoEncontrado(palabrasBuscar.get(i).toLowerCase());
            }
        }
    }

    public void setOnFrgEdTxtCNIListener(OnFrgEditTextCNIListener listener) {
        this.listener = listener;
    }

    public void setHint(String hint) {
        edTxtDestinatario.setHint(hint);
    }
}
