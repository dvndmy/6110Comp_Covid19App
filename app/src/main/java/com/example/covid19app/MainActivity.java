package com.example.covid19app;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;




public class MainActivity extends AppCompatActivity {
    EditText EmailEt, PasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailEt = (EditText)findViewById(R.id.etEmail);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String email = EmailEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, password);

        startActivity(new Intent(this, UserPage.class));

    }
    public void OpenReg(View view){
        startActivity(new Intent(this, Registration.class));

  }

    public void OnForgotPassword(View view){
        startActivity(new Intent(this, forgotPassword.class));

    }
}

