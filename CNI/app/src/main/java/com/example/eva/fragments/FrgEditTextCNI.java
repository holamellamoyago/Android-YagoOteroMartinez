package com.example.eva.fragments;

import android.os.Bundle;
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

public class FrgEditTextCNI extends Fragment {

    OnFrgEditTextCNIListener listener;
    EditText edTxtDestinatario;
    ListView lvAvisos;

     public interface OnFrgEditTextCNIListener {
        boolean onTextoEncontrado(String palabra);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.frg_edit_text_cni, null);
        edTxtDestinatario = (EditText) layout.findViewById(R.id.edTxtDestinatario);
//        };

        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println(String.valueOf(charSequence));
                haCambiado(charSequence.toString());
            }};

        edTxtDestinatario.addTextChangedListener(watcher);
    }

    private void haCambiado(String texto){
        if (texto.contains("bomba")){
            listener.onTextoEncontrado("bomba");
        }
    }

    public void setOnFrgEdTxtCNIListener(OnFrgEditTextCNIListener listener){
        this.listener = listener;
    }
}
