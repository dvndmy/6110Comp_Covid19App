package com.example.covid19app;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserPage extends AppCompatActivity {
    public void OpenSymp(View view){
        startActivity(new Intent(this, AddSymptoms.class));

    }
}

