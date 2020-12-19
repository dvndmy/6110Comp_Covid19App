package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void OpenSymp(View view){
        startActivity(new Intent(this, AddSymptoms.class));

    }
    public void OpenRecord(View view){
        startActivity(new Intent(this, YourRecords.class));

    }
    public void OpenUserPage(View view){
        startActivity(new Intent(this, UserPage.class));

    }

    public void OnEdit(View view){
        startActivity(new Intent(this, EditUser.class));

    }

}