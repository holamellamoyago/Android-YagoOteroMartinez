package com.example.eva.presentation.mainActivity;

import android.content.Context;

import com.example.eva.FlechasFragment;

public class ControllerMainActivity {
    private Context context;
    private FlechasFragment frgFlechas;

    public ControllerMainActivity(Context context, FlechasFragment frgFlechas) {
        this.context = context;
        this.frgFlechas = frgFlechas;
    }
}
