package com.example.covid19app;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class MainActivity extends AppCompatActivity {
    EditText EmailEt, PasswordEt;
    String ipAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailEt = (EditText) findViewById(R.id.etEmail);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        ipAddress = ((MyIP) this.getApplication()).getIP();

    }

    //old login method:

//    public void OnLogin(View view) {
//        String email = EmailEt.getText().toString();
//        String password = PasswordEt.getText().toString();
//        String type = "login";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, email, password);
//
//        startActivity(new Intent(this, UserPage.class));
//
//        }

public void OnGoToDoctorLogin(View view) {
    startActivity(new Intent(this, DoctorLogin.class));
}


    public void OnLogin(View view) {
        String email = EmailEt.getText().toString();
        String password = PasswordEt.getText().toString();
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

        if (!email.equals("") && !password.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;
                    //change the ip and php file location to your own:
                    PutData putData = new PutData("http://"+ ipAddress +"/c19php/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                String userDetails = preferences.getString(field[0], data[0]);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("userpage",userDetails);
                                editor.apply();

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
        } else {
            Toast.makeText(getApplicationContext(), "All Fields required", Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenReg(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    public void OnForgotPassword(View view) {
        startActivity(new Intent(this, forgotPassword.class));

    }
}

