package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class AddSymptoms extends AppCompatActivity {
    private Button backBtn;
    private Button submitBtn;
    CheckBox breathless, diarrhoea, cough, congested, others, sorethroat, muscleache, hightemp,
            losstaste, losssmell, chills, headache, nausea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

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

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
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
        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backuserPage();
            }
        });
    }

    public void onCheckboxClick(View view) {
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


    public void backuserPage() {
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }



}