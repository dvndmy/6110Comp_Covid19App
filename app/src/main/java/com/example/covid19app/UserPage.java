package com.example.covid19app;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class UserPage extends AppCompatActivity {
    private Button submitfeelBtn;
    CheckBox verygood, good, neutral, bad, verybad;

    EditText ID, stress, energy;
    Integer int_verygood, int_good, int_neutral, int_bad, int_verybad;
    String ipAddress, str_mood, str_stress, str_energy;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);


        verygood = (CheckBox)findViewById(R.id.cb_verygood);
        good = (CheckBox)findViewById(R.id.cb_good);
        neutral = (CheckBox)findViewById(R.id.cb_neutral);
        bad = (CheckBox)findViewById(R.id.cb_bad);
        verybad = (CheckBox)findViewById(R.id.cb_verybad);
        stress = (EditText) findViewById(R.id.et_stress);
        energy = (EditText) findViewById(R.id.et_energy);
        ipAddress = ((MyIP) this.getApplication()).getIP();

        submitfeelBtn = (Button) findViewById(R.id.btnSubmitFeel);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String userpage = preferences.getString("userpage","");

    }

    //Code for button operations

    public void OpenSymp(View view){
        startActivity(new Intent(this, AddSymptoms.class));

    }
    public void OpenRecord(View view){
        startActivity(new Intent(this, YourRecords.class));

    }

    public void OpenDash(View view){
        startActivity(new Intent(this, Dashboard.class));

    }


    //Ensures that only one checkbox can be checked at a time
    public void onvgClick(View view){

        verygood.setChecked(true);
            good.setChecked(false);
            neutral.setChecked(false);
            bad.setChecked(false);
            verybad.setChecked(false);

    }

    public void ongClick(View view){

        good.setChecked(true);
            verygood.setChecked(false);
            neutral.setChecked(false);
            bad.setChecked(false);
            verybad.setChecked(false);

    }
    public void onnClick(View view){
        neutral.setChecked(true);
            good.setChecked(false);
            verygood.setChecked(false);
            bad.setChecked(false);
            verybad.setChecked(false);
    }

    public void onbClick(View view){
        bad.setChecked(true);
            good.setChecked(false);
            neutral.setChecked(false);
            verygood.setChecked(false);
            verybad.setChecked(false);
    }

    public void onvbClick(View view){
        verybad.setChecked(true);
            good.setChecked(false);
            neutral.setChecked(false);
            bad.setChecked(false);
            verygood.setChecked(false);
    }
    public void onSubmit(View view) throws InterruptedException {
            String str_mood = "";

            //Below code checks for the checkbox input to determine the string value of mood
            if (verygood.isChecked()) {
             str_mood = "Very good";
            } else if (good.isChecked()) {
                 str_mood = "Good";
            } else if (neutral.isChecked()) {
                 str_mood = "Neutral";
            } else if (bad.isChecked()) {
                 str_mood = "Bad";
            } else if (verybad.isChecked()) {
                 str_mood = "Very bad";
            }


        String str_stress = stress.getText().toString();
        String str_energy = energy.getText().toString();
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String emailad = preferences.getString("userpage","");
        Handler handler = new Handler(Looper.getMainLooper());
        String finalStr_mood = str_mood;
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "mood";
                field[1] = "stresslevel";
                field[2] = "energy";
                field[3] = "emailad";

                //Creating array for data
                String[] data = new String[4];
                data[0] = finalStr_mood.toString();
                data[1] = str_stress.toString();
                data[2] = str_energy.toString();
                data[3] = emailad;

                //change ip and path as necessary
                PutData putData = new PutData("http://"+ ipAddress +"/c19php/usermental.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Mental State Updated")) {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
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

    //Adding menu as well as logout functionality

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
}

