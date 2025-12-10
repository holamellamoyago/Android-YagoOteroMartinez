package com.example.eva.main;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;

import com.example.eva.ControllerDatabase;
import com.example.eva.FrgBuscador;
import com.example.eva.R;
import com.example.eva.ResultadoActivity;
import com.example.eva.Utils;

import java.util.ArrayList;

public class MainController {
    private Context context;
    private ControllerDatabase controllerDatabase;

    private ListView lv_ayudas;
    private ArrayList<String> ayudas;
    private ArrayAdapter<String> adapterArray;
    private FrgBuscador frgBuscador;

    private boolean marcaAcertada;

    public MainController(Context context) {
        this.context = context;
        this.controllerDatabase = new ControllerDatabase();
        this.ayudas = new ArrayList<>();
        this.adapterArray = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ayudas);
        this.marcaAcertada = false;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<String> getAyudas() {
        return ayudas;
    }

    public void setAyudas(ArrayList<String> ayudas) {
        this.ayudas = ayudas;
    }

    public ArrayAdapter<String> getAdapterArray() {
        return adapterArray;
    }

    public void setAdapterArray(ArrayAdapter<String> adapterArray) {
        this.adapterArray = adapterArray;
    }

    public ListView getLv_ayudas() {
        return lv_ayudas;
    }

    public void setLv_ayudas(ListView lv_ayudas) {
        this.lv_ayudas = lv_ayudas;
    }

    public void anadirPista() {
        int i = adapterArray.getCount();

        if (!marcaAcertada) {
            adapterArray.add(controllerDatabase.cogerPistaMarcaID(i));
        } else {
            adapterArray.add(controllerDatabase.cogerPistaModeloID(i));
        }
    }

    public boolean comprobarMarca(String marca) {

        if (marca.equals(controllerDatabase.cogerMarca())) {
            marcaAcertada = true;
            return true;
        }

        return false;
    }


    public boolean comprobarModelo(String modelo) {

        if (modelo.equals(controllerDatabase.cogerModelo())) {
            marcaAcertada = true;
            return true;
        }

        return false;
    }

    // 10-12

    public boolean restarVida() {
        int vidas = Integer.valueOf(tv_vidas.getText().toString());
        int vidasRestantes = vidas - 1;
        System.out.println(vidasRestantes);

        if (vidasRestantes == 0) {
            mostrarPantallaResultado(false);
            return true;
        }

        actualizarVidas(vidasRestantes);
        return false;
    }

    public void mostrarPantallaResultado(boolean gano) {
        Intent resultadoActivity = new Intent(getContext(), ResultadoActivity.class);

        if (gano) {
            resultadoActivity.putExtra("resultado", "Gano la partida");
        } else {
            resultadoActivity.putExtra("resultado", "Perdio la partida");
        }

        startActivity(resultadoActivity);
    }


    public void actualizarVidas(int vidas) {
        vidasRestantes = vidas;
        tv_vidas.setText(vidasRestantes + "");
    }

    public void restarVida() {
        if (frgBuscador.restarVida()) {
            // Si esta funcióin devuelve true , significa que el contador de vidas llegó  a 0
            return;
        }

        anadirPista();
        Utils.mostrarToast(context, "Vaya... no acertaste...");
    }

    private void iniciarfragments() {
        FragmentManager frgManager = getSupportFragmentManager();

        frgBuscador = (FrgBuscador) frgManager.findFragmentById(R.id.frgBuscador);
        frgBuscador.setListener(new FrgBuscador.OnFrgBuscador() {
            @Override
            public void onMarcaSeleccionada(FrgBuscador frgBuscador, String marca) {
                if (marca.equals("Selecciona la marca")) return;

                if (!controller.comprobarMarca(marca)) {
                    restarVida();
                } else {
                    Utils.mostrarToast(getApplicationContext(), "Acertaste la marca");
                    frgBuscador.cambiarSpinner();

                }
            }

            @Override
            public boolean onModeloSeleccionada(FrgBuscador fragment, String modelo) {
                if (modelo.equals("Selecicona el coche")) return false;


                if (!controller.comprobarModelo(modelo)){
                    restarVida();
                } else {
                    Utils.mostrarToast(getApplicationContext(), "Acertaste el modelo");
                    return true;
                }

                return false;
            }

            @Override
            public void onPistaSolicitada(FrgBuscador fragment) {
                controller.anadirPista();
            }
        });


    }

}
