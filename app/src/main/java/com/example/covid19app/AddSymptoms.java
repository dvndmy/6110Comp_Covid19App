package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddSymptoms extends AppCompatActivity {
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backuserPage();
            }
        });
    }

    public void backuserPage() {
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }
}