package com.example.eva.fragmento;

public class ControllerFrgCni {
    private FrgCniSensorIA destinatario;
    private FrgCniSensorIA asunto;
    private FrgCniSensorIA mensaje;

    public ControllerFrgCni(FrgCniSensorIA destinatario, FrgCniSensorIA asunto, FrgCniSensorIA mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public ControllerFrgCni() {}

    public void setFragment(FrgCniSensorIA destinatario, FrgCniSensorIA asunto, FrgCniSensorIA mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
}
