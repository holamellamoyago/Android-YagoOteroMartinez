package com.example.eva;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void mostrarToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
