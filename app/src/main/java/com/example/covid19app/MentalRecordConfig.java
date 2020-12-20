package com.example.covid19app;

public class MentalRecordConfig {


    private static String ip = getIPad();
    public static final String DATA_URL = "http://" + ip + "/c19php/mentalrecords.php?email=";
    public static final String KEY_mood = "mood";
    public static final String KEY_enterdate = "enterdate";
    public static final String KEY_stresslevel =  "stresslevel";
    public static final String KEY_pemail =  "pemail";
    public static final String KEY_energy =  "energy";
    public static final String JSON_ARRAY = "result";

    public MentalRecordConfig() throws NoSuchFieldException {
    }
    public static String getIPad() {
        MyIP ip = new MyIP();
        return ip.getIP();
    }
}
