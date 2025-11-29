package com.example.eva.data.openF1;

public class HiloConector extends Thread{
    @Override
    public void run() {
        GestorOpenF1 gestorOpenF1 = new GestorOpenF1();
//        System.out.println("json " + gestorOpenF1.getUrlContents());
        gestorOpenF1.getInformacionPiloto(33);
    }
}
