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

        ID = (EditText)findViewById(R.id.etID);

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

        TextView displayInfo = (TextView) findViewById(R.id.viewTextName);
        displayInfo.setText(userpage);
    }

    public void OpenSymp(View view){
        startActivity(new Intent(this, AddSymptoms.class));

    }
    public void OpenRecord(View view){
        startActivity(new Intent(this, YourRecords.class));

    }

    public void OpenDash(View view){
        startActivity(new Intent(this, Dashboard.class));

    }

    public void OnEdit(View view){
        startActivity(new Intent(this, EditUser.class));

    }

    public void onSubmit(View view) throws InterruptedException {
        ;
        //Checks for only one checkbox selected
        int cb_count=0;
        if (verygood.isChecked()) {
            cb_count=cb_count+1;
        }if (good.isChecked()) {
            cb_count=cb_count+1;
        }if (neutral.isChecked()) {
            cb_count=cb_count+1;
        }if (bad.isChecked()) {
            cb_count=cb_count+1;
        }if (verybad.isChecked()) {
            cb_count = cb_count + 1;
        }
            //Checks if only one checkbox has been selected
        if (cb_count == 1) {

            //Below code checks for the integer value of checkbox input to determine the string value of mood

            Integer int_verygood;
            if (verygood.isChecked()) {
                int_verygood = 1;
            } else {
                int_verygood = 0;
            }

            Integer int_good;
            if (good.isChecked()) {
                int_good = 1;
            } else {
                int_good = 0;
            }

            Integer int_neutral;
            if (neutral.isChecked()) {
                int_neutral = 1;
            } else {
                int_neutral = 0;
            }

            Integer int_bad;
            if (bad.isChecked()) {
                int_bad = 1;
            } else {
                int_bad = 0;
            }

            Integer int_verybad;
            if (verybad.isChecked()) {
                int_verybad = 1;
            } else {
                int_verybad = 0;
            }

            if (int_verygood == 1) {
            String str_mood = "Very good";
            } else if (int_good == 1) {
                String str_mood = "Good";
            } else if (int_neutral == 1) {
                String str_mood = "Neutral";
            } else if (int_bad == 1) {
                String str_mood = "Bad";
            } else if (int_verybad == 1) {
                String str_mood = "Very bad";
            }

        } else {
            Toast.makeText(UserPage.this, "Please select only one checkbox!", Toast.LENGTH_SHORT).show();
        }
        String str_stress = stress.getText().toString();
        String str_energy = energy.getText().toString();
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String emailad = preferences.getString("userpage","");
        Handler handler = new Handler(Looper.getMainLooper());
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
                data[0] = str_mood.toString();
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

