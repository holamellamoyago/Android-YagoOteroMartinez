package com.example.eva;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void mostrarMensaje(Context context, String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
