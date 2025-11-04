package com.example.eva.telefonos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;

public class FrgTelefono extends Fragment {
    String numTelefono;
    boolean isCalling;

    public interface frgTelefonoListener {
        /*
            boolean: valor de retorno, me dice si puedo llamar entrar en la llamada
         */
         boolean onCall(FrgTelefono fragment, String numEntrante, String numSaliente);
         void onColgar(FrgTelefono fragment, String numEntrante, String numSaliente);
    }

    TextView txtNumTlfn;
    EditText edNumTlfn;
    ImageButton imgBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_telefono, null);

        txtNumTlfn = view.findViewById(R.id.txtNumTlfn);
        edNumTlfn = view.findViewById(R.id.edNumTlfn);
        imgBtn = view.findViewById(R.id.imgBtn);

        return view;
    }
}
