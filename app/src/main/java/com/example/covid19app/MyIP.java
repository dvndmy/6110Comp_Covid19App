package com.example.covid19app;

import android.app.Application;

//Class called universally for database connection
public class MyIP extends Application {

    private String IP = "192.168.1.15";

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
