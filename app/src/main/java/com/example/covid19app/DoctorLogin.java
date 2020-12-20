package com.example.covid19app;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class DoctorLogin extends AppCompatActivity {
    EditText EmailEt, PasswordEt;
    String ipAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        EmailEt = (EditText) findViewById(R.id.etEmail);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        ipAddress = ((MyIP) this.getApplication()).getIP();

    }

    public void OnLogin(View view) {

        //Retrieves the user's entered email and password
        String email = EmailEt.getText().toString();
        String password = PasswordEt.getText().toString();

        //Verifies that the user has correctly entered; an email address and a password
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
                    PutData putData = new PutData("http://"+ ipAddress +"/c19php/DoctorLogin.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                               //todo: change page link on line below
                                Intent intent = new Intent(getApplicationContext(), DoctorDashboard.class);
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
        AlertDialog alertDialog = new AlertDialog.Builder(DoctorLogin.this).create();
        alertDialog.setTitle("Doctor Registration");
        alertDialog.setMessage("In order to register as a doctor, please contact your system Administrator.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();

    }

    public void OnGoToPatientLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void OnForgotPassword(View view) {
        startActivity(new Intent(this, DoctorForgotPassword.class));

    }
}

