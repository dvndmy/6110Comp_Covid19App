package com.example.covid19app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

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
}

