package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class forgotPassword extends AppCompatActivity {
EditText etUserName,etPassword, etPassword2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etUserName = (EditText)findViewById(R.id.etEmailfp);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword2 = (EditText)findViewById(R.id.etPassword2);
    }

    public void OnForgotSC(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(forgotPassword.this).create();
        alertDialog.setTitle("Security Code Reset");
        alertDialog.setMessage("In order to recover or change your security code, please contact your system Administrator.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    public void OnCancel(View view){
        startActivity(new Intent(this, MainActivity.class));

    }

    public void OnSave(View view) throws InterruptedException {
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String securitycode = etPassword2.getText().toString();
    //TODO
        //

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
                    PutData putData = new PutData("http://192.168.0.11/c19db/ForgotPassword.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Password Reset successfully")) {
                                AlertDialog alertDialog = new AlertDialog.Builder(forgotPassword.this).create();
                                alertDialog.setTitle("Password Changed Successfully");
                                alertDialog.setMessage("Your Password was successfully changed. please Log in on the following page using your new details.");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        (dialog, which) -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
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