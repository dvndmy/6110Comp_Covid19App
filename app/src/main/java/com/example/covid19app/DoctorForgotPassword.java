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

public class DoctorForgotPassword extends AppCompatActivity {
EditText etUserName,etPassword, etPassword2 ;
    String ipAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_forgot_password);
        etUserName = (EditText)findViewById(R.id.etEmailfp);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword2 = (EditText)findViewById(R.id.etPassword2);
        ipAddress = ((MyIP) this.getApplication()).getIP();
    }

    public void OnForgotSC(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(DoctorForgotPassword.this).create();
        alertDialog.setTitle("Security Code Reset");
        alertDialog.setMessage("In order to recover or change your security code, please contact your system Administrator.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    public void OnCancel(View view){
        startActivity(new Intent(this, DoctorLogin.class));

    }

    public void OnSave(View view) throws InterruptedException {
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String securitycode = etPassword2.getText().toString();

        //Verifies that the user has correctly entered; an email address, password and security code
        if (!email.equals("") && !password.equals("") && !securitycode.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[3];
                    field[0] = "email";
                    field[1] = "password";
                    field[2] = "securitycode";
                    //Creating array for data
                    String[] data = new String[3];
                    data[0] = email;
                    data[1] = password;
                    data[2] = securitycode;
                    //change the ip and php file location to your own:
                    PutData putData = new PutData("http://"+ ipAddress +"/c19php/DoctorForgotPassword.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Password Reset successfully")) {
                                AlertDialog alertDialog = new AlertDialog.Builder(DoctorForgotPassword.this).create();
                                alertDialog.setTitle("Password Changed Successfully");
                                alertDialog.setMessage("Your Password was successfully changed. please Log in on the following page using your new details.");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        (dialog, which) -> startActivity(new Intent(getApplicationContext(), DoctorLogin.class)));
                                alertDialog.show();
                                //finish();
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
}