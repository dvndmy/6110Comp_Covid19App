package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AddSymptoms extends AppCompatActivity {
    private Button submitBtn;
    CheckBox breathless, diarrhoea, cough, congested, others, sorethroat, muscleache, hightemp,
            losstaste, losssmell, chills, headache, nausea;

    //ID field to test database connection
    EditText ID;
    Integer int_breathless, int_diarrhoea, int_cough, int_congested, int_others, int_sorethroat,
            int_muscleache, int_hightemp, int_losstaste, int_losssmell, int_chills, int_headache, int_nausea;
    String ipAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

        ID = (EditText)findViewById(R.id.etID);

        breathless = (CheckBox)findViewById(R.id.cb_Breathless);
        diarrhoea = (CheckBox)findViewById(R.id.cb_Diarrhoea);
        cough = (CheckBox)findViewById(R.id.cb_Cough);
        congested = (CheckBox)findViewById(R.id.cb_Congested);
        others = (CheckBox)findViewById(R.id.cb_Others);
        sorethroat = (CheckBox)findViewById(R.id.cb_Sorethroat);
        muscleache = (CheckBox)findViewById(R.id.cb_Muscleache);
        hightemp = (CheckBox)findViewById(R.id.cb_Hightemp);
        losstaste = (CheckBox)findViewById(R.id.cb_Losstaste);
        losssmell = (CheckBox)findViewById(R.id.cb_Losssmell);
        chills = (CheckBox)findViewById(R.id.cb_Chills);
        headache = (CheckBox)findViewById(R.id.cb_Headache);
        nausea = (CheckBox)findViewById(R.id.cb_Nausea);

        ipAddress = ((MyIP) this.getApplication()).getIP();

        submitBtn = (Button) findViewById(R.id.submitBtn);






  /*      submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Selected Symptoms";
                if (breathless.isChecked()) {
                    result += "\nBreathless";
                }
                if (diarrhoea.isChecked()) {
                    result += "\nDiarrhoea";
                }
                if (cough.isChecked()) {
                    result += "\nCough";
                }
                if (congested.isChecked()) {
                    result += "\nCongested";
                }
                if (others.isChecked()) {
                    result += "\nOthers";
                }
                if (sorethroat.isChecked()) {
                    result += "\nSore Throat";
                }
                if (muscleache.isChecked()) {
                    result += "\nMuscle Ache";
                }
                if (hightemp.isChecked()) {
                    result += "\nHigh Temperature";
                }
                if (losstaste.isChecked()) {
                    result += "\nLoss of Taste";
                }
                if (losssmell.isChecked()) {
                    result += "\nLoss of Smell";
                }
                if (chills.isChecked()) {
                    result += "\nChills";
                }
                if (headache.isChecked()) {
                    result += "\nHeadache";
                }
                if (nausea.isChecked()) {
                    result += "\nNausea";
                }

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            }

        });
        */

        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backuserPage();
            }
        });
    }

    public void onSubmit(View view) throws InterruptedException {
        //Using Code from Register.php to test entering checkbox data into database
        ;
        Integer int_cough;
        if (cough.isChecked()) {
            int_cough = 1;
        } else {
            int_cough = 0;
        }

        Integer int_breathless;
        if (breathless.isChecked()) {
            int_breathless = 1;
        } else {
            int_breathless = 0;
        }

        Integer int_diarrhoea;
        if (diarrhoea.isChecked()) {
            int_diarrhoea = 1;
        } else {
            int_diarrhoea = 0;
        }

        Integer int_congested;
        if (congested.isChecked()) {
            int_congested = 1;
        } else {
            int_congested = 0;
        }

        Integer int_others;
        if (others.isChecked()) {
            int_others = 1;
        } else {
            int_others = 0;
        }

        Integer int_sorethroat;
        if (sorethroat.isChecked()) {
            int_sorethroat = 1;
        } else {
            int_sorethroat = 0;
        }

        Integer int_muscleache;
        if (muscleache.isChecked()) {
            int_muscleache = 1;
        } else {
            int_muscleache = 0;
        }

        Integer int_hightemp;
        if (hightemp.isChecked()) {
            int_hightemp = 1;
        } else {
            int_hightemp = 0;
        }

        Integer int_losstaste;
        if (losstaste.isChecked()) {
            int_losstaste = 1;
        } else {
            int_losstaste = 0;
        }

        Integer int_losssmell;
        if (losssmell.isChecked()) {
            int_losssmell = 1;
        } else {
            int_losssmell = 0;
        }

        Integer int_chills;
        if (chills.isChecked()) {
            int_chills = 1;
        } else {
            int_chills = 0;
        }

        Integer int_headache;
        if (headache.isChecked()) {
            int_headache = 1;
        } else {
            int_headache = 0;
        }

        Integer int_nausea;
        if (nausea.isChecked()) {
            int_nausea = 1;
        } else {
            int_nausea = 0;
        }

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[13];
                    field[0] = "cough";
                    field[1] = "breathlessness";
                    field[2] = "lossofTaste";
                    field[3] = "lossofSmell";
                    field[4] = "highTemprature";
                    field[5] = "chills";
                    field[6] = "headache";
                    field[7] = "muscleAche";
                    field[8] = "soreThroat";
                    field[9] = "congestedNose";
                    field[10] = "nausea";
                    field[11] = "diarrhea";
                    field[12] = "other";
                    //Creating array for data
                    String[] data = new String[13];
                    data[0] = int_cough.toString();
                    data[1] = int_breathless.toString();
                    data[2] = int_losstaste.toString();
                    data[3] = int_losssmell.toString();
                    data[4] = int_hightemp.toString();
                    data[5] = int_chills.toString();
                    data[6] = int_headache.toString();
                    data[7] = int_muscleache.toString();
                    data[8] = int_sorethroat.toString();
                    data[9] = int_congested.toString();
                    data[10] = int_nausea.toString();
                    data[11] = int_diarrhoea.toString();
                    data[12] = int_others.toString();
                    //change ip and path as necessary
                    PutData putData = new PutData("http://"+ ipAddress +"/c19php/AddSymptoms.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Symptoms Updated")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }

                            Log.i("PutData", result);
                        }
                    }

                }
            });



    }










  /*  public void onCheckboxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str ="";
        //Checks if a checkbox was clicked
        switch(view.getId()) {
            case R.id.cb_Breathless:
                str = checked?"Breathless Selected" : "Breathless Deselected";
                break;
            case R.id.cb_Diarrhoea:
                str = checked?"Diarrhoea Selected" : "Diarrhoea Deselected";
                break;
            case R.id.cb_Cough:
                str = checked?"Cough Selected" : "Cough Deselected";
                break;
            case R.id.cb_Congested:
                str = checked?"Congested Selected" : "Congested Deselected";
                break;
            case R.id.cb_Others:
                str = checked?"Others Selected" : "Others Deselected";
                break;
            case R.id.cb_Sorethroat:
                str = checked?"Sore Throat Selected" : "Sore Throat Deselected";
                break;
            case R.id.cb_Muscleache:
                str = checked?"Muscle Ache Selected" : "Muscle Ache Deselected";
                break;
            case R.id.cb_Hightemp:
                str = checked?"High Temperature Selected" : "High Temperature Deselected";
                break;
            case R.id.cb_Losstaste:
                str = checked?"Loss of Taste Selected" : "Loss of Taste Deselected";
                break;
            case R.id.cb_Losssmell:
                str = checked?"Loss of Smell Selected" : "Loss of Smell Deselected";
                break;
            case R.id.cb_Chills:
                str = checked?"Chills Selected" : "Chills Deselected";
                break;
            case R.id.cb_Headache:
                str = checked?"Headache Selected" : "Headache Deselected";
                break;
            case R.id.cb_Nausea:
                str = checked?"Nausea Selected" : "Nausea Deselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

    } */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:

                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return true;
    }


    public void backuserPage() {
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }



}