package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;

public class Registration extends AppCompatActivity {
    EditText FullName, Email, Password,  Medication, MedicalCondition, Age, Weight;
    CheckBox Hospitalised, Smoker;

    String str_fullname, str_email, str_password, str_medication, str_medicalcondition, str_age, str_weight;
    Integer int_hospitalised, int_smoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FullName            = (EditText)findViewById(R.id.et_fullname);
        Email               = (EditText)findViewById(R.id.et_email);
        Password            = (EditText)findViewById(R.id.et_password);
        Hospitalised        = (CheckBox)findViewById(R.id.cb_hospitalised);
        Smoker              = (CheckBox)findViewById(R.id.cb_smoker);
        Medication          = (EditText)findViewById(R.id.et_medication);
        MedicalCondition    = (EditText)findViewById(R.id.et_medicalcondition);
        Age                 = (EditText)findViewById(R.id.et_age);
        Weight              = (EditText)findViewById(R.id.et_weight);
    }

   public void OnReg(View view) {
       String  str_fullname              = FullName.getText().toString();
       String  str_email                 = Email.getText().toString();
       String  str_password              = Password.getText().toString();
     /*Integer int_hospitalised          = Hospitalised
       Integer int_smoker                = Smoker */
       String  str_medication           = Medication.getText().toString();
       String  str_medicalcondition      = MedicalCondition.getText().toString();
       String  str_age                   = Age.getText().toString();
       String  str_weight                = Weight.getText().toString();
       String type = "register";
       BackgroundWorker backgroundWorker = new BackgroundWorker(this);
       backgroundWorker.execute(type, str_fullname, str_email, str_password, /*int_hospitalised  int_smoker*/  str_medication,  str_medicalcondition,  str_age, str_weight);
       }

}
