package com.example.eva.telefonos.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eva.R;

public class FrgTelefono extends Fragment {
    onFrgTelefonoListener listener;
    String numTelefono;
    String numTelefonoSaliente;
    boolean isCalling;

    public interface onFrgTelefonoListener {
        /*
            boolean: valor de retorno, me dice si puedo llamar entrar en la llamada
         */
         boolean onCall(FrgTelefono fragment, String numSaliente);
         void onColgar(FrgTelefono fragment, String numSaliente);
    }

    TextView txtNumTlfn;
    EditText edNumTlfn;
    ImageButton imgBtn;

        public FrgTelefono(String numTelefono){
            this.numTelefono = numTelefono;
            isCalling = false;
        }

    // Porque hace falta uno vacío ? Bundle?
    public FrgTelefono() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_telefono, null);

        txtNumTlfn = view.findViewById(R.id.txtNumTlfn);
        edNumTlfn = view.findViewById(R.id.edNumTlfn);
        imgBtn = view.findViewById(R.id.imgBtn);

        txtNumTlfn.setText(numTelefono);
        imgBtn.setImageResource(R.drawable.call);
        imgBtn.setOnClickListener(v -> llamar());

        return view;
    }

    public void setOnFrgTelefonoListener(onFrgTelefonoListener listener) {
        this.listener = listener;
    }

    private void llamar(){
        if (isCalling) {
            listener.onColgar(this, numTelefonoSaliente);
        } else if (!edNumTlfn.getText().toString().equals("")){
            listener.onCall(this, edNumTlfn.getText().toString());
        } else{
            Toast.makeText(getActivity(), "No puedes llamar a un número vacío! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void empezarLlamada(String numSaliente){
        setCalling(true);
        setTxtNumTlfn(getNumTelefono().concat(" -> " + numSaliente));
        setImgBtn(R.drawable.callend);
        setNumTelefonoSaliente(numSaliente);
        edNumTlfn.setText("");

    }


    public void terminarLlmada(){
        setCalling(false);
        setTxtNumTlfn(numTelefono);
        setImgBtn(R.drawable.call);
        setNumTelefonoSaliente(null);
    }


    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public boolean isCalling() {
        return isCalling;
    }

    public void setCalling(boolean calling) {
        isCalling = calling;
    }

    public TextView getTxtNumTlfn() {
        return txtNumTlfn;
    }

    public void setTxtNumTlfn(String txtNumTlfn) {
        this.txtNumTlfn.setText(txtNumTlfn);
    }

    public EditText getEdNumTlfn() {
        return edNumTlfn;
    }

    public void setEdNumTlfn(EditText edNumTlfn) {
        this.edNumTlfn = edNumTlfn;
    }

    public void setImgBtn(int imgBtn) {
        this.imgBtn.setImageResource(imgBtn);
    }

    public String getNumTelefonoSaliente() {
        return numTelefonoSaliente;
    }

    public void setNumTelefonoSaliente(String numTelefonoSaliente) {
        this.numTelefonoSaliente = numTelefonoSaliente;
    }
}
