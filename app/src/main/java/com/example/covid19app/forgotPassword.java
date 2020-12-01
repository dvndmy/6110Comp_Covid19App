package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class forgotPassword extends AppCompatActivity {
EditText etUserName,etPassword ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);
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
        String type = "login";
    //TODO
        //
        AlertDialog alertDialog = new AlertDialog.Builder(forgotPassword.this).create();
        alertDialog.setTitle("Password Changed Successfully");
        alertDialog.setMessage("Your Password was successfully changed. please Log in on the following page using your new details.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> startActivity(new Intent(this, MainActivity.class)));
        alertDialog.show();
        //
        ;
    }
}