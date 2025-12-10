package com.example.eva;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Utils {


    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }


    public static void showSnackbar(Activity activity, String message) {
        if (activity != null) {
            // Obtiene la vista raíz (content view) de la Activity para anclar el Snackbar
            View rootView = activity.findViewById(android.R.id.content);
            if (rootView != null) {
                Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public static void showSnackbarWithAction(Activity activity, String message,
                                              String actionText, View.OnClickListener listener) {
        if (activity != null) {
            View rootView = activity.findViewById(android.R.id.content);
            if (rootView != null) {
                Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
                snackbar.setAction(actionText, listener);
                snackbar.show();
            }
        }
    }
}
