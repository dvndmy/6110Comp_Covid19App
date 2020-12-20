package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Dashboard extends AppCompatActivity {

    //Code for button operations

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

