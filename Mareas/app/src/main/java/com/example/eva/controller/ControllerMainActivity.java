package com.example.eva.controller;

public class ControllerMainActivity {
    public static final String strURL = "https://servizos.meteogalicia.gal";
    public String contents;

    private String error;
    private boolean isLoading;

    public ControllerMainActivity() {
        error = "";
        isLoading = false;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getURL() {
        return strURL;
    }

    public String getContents() {
        return contents;
    }

}
