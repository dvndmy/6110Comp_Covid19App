package com.example.covid19app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public void OpenDash(View view){
        startActivity(new Intent(this, Dashboard.class));

    }

    public void OnEdit(View view){
        startActivity(new Intent(this, EditUser.class));

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

