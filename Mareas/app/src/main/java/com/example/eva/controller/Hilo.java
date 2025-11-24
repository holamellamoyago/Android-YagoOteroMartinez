package com.example.eva.controller;

import com.example.eva.MainActivity;
import com.example.eva.model.HTTP;

import java.net.InetSocketAddress;
import java.net.Proxy;


public class Hilo extends Thread {

    @Override
    public void run() {
        ControllerMainActivity controller = MainActivity.controller;;

        String contents = HTTP.getUrlContents(controller.getURL());
        controller.setContents(contents);
        System.out.println("Yago: " + contents);


    }
}
