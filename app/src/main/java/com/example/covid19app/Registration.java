package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText FullName, Email, Password,RepeatPassword,  Medication, MedicalCondition, Age, Weight;
    CheckBox Hospitalised, Smoker;

    String str_fullname, str_email, str_password,str_repeatpassword, str_medication, str_medicalcondition, str_age, str_weight;
    Integer int_hospitalised, int_smoker;
    Boolean noErrors = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FullName            = (EditText)findViewById(R.id.et_fullname);
        Email               = (EditText)findViewById(R.id.et_email);
        Password            = (EditText)findViewById(R.id.et_password);
        RepeatPassword            = (EditText)findViewById(R.id.et_password2);
        Hospitalised        = (CheckBox)findViewById(R.id.cb_hospitalised);
        Smoker              = (CheckBox)findViewById(R.id.cb_smoker);
        Medication          = (EditText)findViewById(R.id.et_medication);
        MedicalCondition    = (EditText)findViewById(R.id.et_medicalcondition);
        Age                 = (EditText)findViewById(R.id.et_age);
        Weight              = (EditText)findViewById(R.id.et_weight);
    }

   public void OnReg(View view) {
        ;
       String  str_fullname              = FullName.getText().toString();
       String  str_email                 = Email.getText().toString();
       String  str_password              = Password.getText().toString();
       String  str_repeatpassword              = RepeatPassword.getText().toString();
     /*Integer int_hospitalised          = Hospitalised
       Integer int_smoker                = Smoker */
       String  str_medication           = Medication.getText().toString();
       String  str_medicalcondition      = MedicalCondition.getText().toString();
       String  str_age                   = Age.getText().toString();
       String  str_weight                = Weight.getText().toString();
       String type = "register";
       if(checkEnteredData()) {
           BackgroundWorker backgroundWorker = new BackgroundWorker(this);
           backgroundWorker.execute(type, str_fullname, str_email, str_password, /*int_hospitalised  int_smoker*/  str_medication, str_medicalcondition, str_age, str_weight);
           Toast t = Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT);
           t.show();
       }else{

               Toast t = Toast.makeText(this, "Please make sure you have filled all fields correctly", Toast.LENGTH_SHORT);
               t.show();

       }
    }

     Boolean checkEnteredData() {
         noErrors = true;
        if (isEmpty(FullName)) {
            noErrors = false;
            FullName.setError("Full Name is required!");
        }
        if (isEmail(Email) == false) {
            noErrors = false;
            Email.setError("Enter valid email!");
        }
        if (isEmpty(Password)) {
            noErrors = false;
            Password.setError("Password is required!");
        }
        if (isEmpty(RepeatPassword)) {
            noErrors = false;
            RepeatPassword.setError("Password is required!");
        }
        if (Password.getText().toString().equals(RepeatPassword.getText().toString())) {
            if(noErrors == true){
            AlertDialog alertDialog = new AlertDialog.Builder(Registration.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Your passwords do not match. Please try again.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();}
            else{
                noErrors=false;
            }
        }
        if (isEmpty(Age)) {
            noErrors = false;
            Age.setError("Age is required!");
        }
        return noErrors;
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
