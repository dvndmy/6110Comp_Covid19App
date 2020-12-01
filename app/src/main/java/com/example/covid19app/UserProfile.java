package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    public void gotoViewRecords(View view) {
        Button ViewRecords = (Button) findViewById(R.id.btnViewRecords);

        ViewRecords.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, YourRecords.class));
            }
        });

    }

    public void gotoUpdateRecords(View view) {
    }

    public void gotoContactDoctor(View view) {
    }

    public void gotoHelp(View view) {
    }
}