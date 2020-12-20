package com.example.covid19app;

public class SymptomRecordConfig {



    public static final String DATA_URL = "http://192.168.1.15/c19php/symptomrecords.php?email=";
    public static final String KEY_cough = "cough";
    public static final String KEY_enterdate = "enterdate";
    public static final String KEY_breathlessness =  "breathlessness";
    public static final String KEY_pemail =  "pemail";
    public static final String KEY_lossofTaste =  "lossofTaste";
    public static final String KEY_lossofSmell =  "lossofSmell";
    public static final String KEY_highTemprature =  "highTemprature";
    public static final String KEY_chills =  "chills";
    public static final String KEY_headache =  "headache";
    public static final String KEY_muscleAche =  "muscleAche";
    public static final String KEY_soreThroat =  "soreThroat";
    public static final String KEY_congestedNose =  "congestedNose";
    public static final String KEY_nausea =  "nausea";
    public static final String KEY_diarrhea =  "diarrhea";
    public static final String KEY_other =  "other";
    public static final String JSON_ARRAY = "result";

    public SymptomRecordConfig() throws NoSuchFieldException {
    }
}
