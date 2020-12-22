package com.example.covid19app;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditUser extends AppCompatActivity {
    EditText FullName, Email, Password, RepeatPassword, Medication, MedicalCondition, Age, Weight, SecurityCode;
    CheckBox Hospitalised, Smoker, male, female;
    public profile userProfile;
    String str_fullname, str_email, str_password, str_repeatpassword, str_medication, str_medicalcondition, str_age, str_weight;
    Integer int_hospitalised, int_smoker;
    Boolean noErrors = true;
    String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        FullName = (EditText) findViewById(R.id.et_fullname);
        Email = (EditText) findViewById(R.id.et_email);
        Password = (EditText) findViewById(R.id.et_password);
        RepeatPassword = (EditText) findViewById(R.id.et_password2);
        Hospitalised = (CheckBox) findViewById(R.id.cb_hospitalised);
        Smoker = (CheckBox) findViewById(R.id.cb_smoker);
        Medication = (EditText) findViewById(R.id.et_medication);
        MedicalCondition = (EditText) findViewById(R.id.et_medicalcondition);
        Age = (EditText) findViewById(R.id.et_age);
        Weight = (EditText) findViewById(R.id.et_weight);
        SecurityCode= (EditText) findViewById(R.id.et_securitycode);
        ipAddress = ((MyIP) this.getApplication()).getIP();
        male = (CheckBox) findViewById(R.id.cb_Male2);
        female = (CheckBox) findViewById(R.id.cb_Female2);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String userpage = preferences.getString("userpage","");
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "http://"+ ipAddress +"/c19php/GetProfileDetails.php?user="+userpage, response -> {
            try {
                //converting the string to json array object
                JSONArray array = new JSONArray(response);

                //traversing through all the object
                for (int i = 0; i < array.length(); i++) {

                    //getting product object from json array
                    JSONObject profilejson = array.getJSONObject(i);

                    //adding the product to product list
                    userProfile=new profile(profilejson.getString("fullname"),profilejson.getString("email"),profilejson.getInt("hospitalised"),profilejson.getInt("smoker"),profilejson.getString("medication"),profilejson.getString("medicalcondition"),profilejson.getInt("age"),profilejson.getInt("weight"),profilejson.getInt("securitycode"),profilejson.getString("gender"));

                }
                FullName.setText(userProfile.getFullname());
                Email.setText(userProfile.getEmail());
                Password.setText("");
                RepeatPassword.setText("");


                if (userProfile.getHospitalised()==0) {
                    Hospitalised.setChecked(false);
                } else {
                    Hospitalised.setChecked(true);
                }

                if (userProfile.getSmoker()==0) {
                    Smoker.setChecked(false);
                } else {
                    Smoker.setChecked(true);
                }
                Medication.setText(userProfile.getMedication());
                MedicalCondition.setText(userProfile.getMedicalCondition());
                Age.setText(String.valueOf(userProfile.getAge()));
                Weight.setText(String.valueOf(userProfile.getWeight()));
                SecurityCode.setText(String.valueOf(userProfile.getSecurityCode()));
                if (userProfile.getGender().equals("Male")){
                   male.setChecked(true);
                }else if(userProfile.getGender().equals("Female")){
                    female.setChecked(true);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    System.out.println("error");
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
       // loadProfile();
    }

    //old registration code:

//   public void OnReg(View view) {
//        ;
//       String  str_fullname              = FullName.getText().toString();
//       String  str_email                 = Email.getText().toString();
//       String  str_password              = Password.getText().toString();
//       String  str_repeatpassword              = RepeatPassword.getText().toString();
//     /*Integer int_hospitalised          = Hospitalised
//       Integer int_smoker                = Smoker */
//       String  str_medication           = Medication.getText().toString();
//       String  str_medicalcondition      = MedicalCondition.getText().toString();
//       String  str_age                   = Age.getText().toString();
//       String  str_weight                = Weight.getText().toString();
//       String type = "register";
//       if(checkEnteredData()) {
//           BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//           backgroundWorker.execute(type, str_fullname, str_email, str_password, /*int_hospitalised  int_smoker*/  str_medication, str_medicalcondition, str_age, str_weight);
//           Toast t = Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT);
//           t.show();
//       }else{
//
//               Toast t = Toast.makeText(this, "Please make sure you have filled all fields correctly", Toast.LENGTH_SHORT);
//               t.show();
//
//       }
//    }

    public void onMaleClick(View view){

        male.setChecked(true);
        female.setChecked(false);
    }
    public void onFemaleClick(View view){

        male.setChecked(false);
        female.setChecked(true);
    }

//Register on click method
    public void OnReg(View view) {

        String str_fullname = FullName.getText().toString();
        String str_email = Email.getText().toString();
        String str_password = Password.getText().toString();
        String str_repeatpassword = RepeatPassword.getText().toString();

        //checkbox values
        Integer int_hospitalised;
        if (Hospitalised.isChecked()) {
            int_hospitalised = 1;
        } else {
            int_hospitalised = 0;
        }
        Integer int_smoker;
        if (Smoker.isChecked()) {
            int_smoker = 1;
        } else {
            int_smoker = 0;
        }
        String str_gender;
        if (male.isChecked()){
            str_gender="Male";
        }else{
            str_gender="Female";
        }
        String str_medication = Medication.getText().toString();
        String str_medicalcondition = MedicalCondition.getText().toString();
        String str_age = Age.getText().toString();
        String str_weight = Weight.getText().toString();
        String str_securitycode = SecurityCode.getText().toString();
        if (checkEnteredData()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[12];
                    field[0] = "fullname";
                    field[1] = "email";
                    field[2] = "password";
                    field[3] = "repeatpassword";
                    field[4] = "hospitalised";
                    field[5] = "smoker";
                    field[6] = "medication";
                    field[7] = "medicalcondition";
                    field[8] = "age";
                    field[9] = "weight";
                    field[10] = "securitycode";
                    field[11] = "gender";
                    //Creating array for data
                    String[] data = new String[12];
                    data[0] = str_fullname;
                    data[1] = str_email;
                    data[2] = str_password;
                    data[3] = str_repeatpassword;
                    data[4] = int_hospitalised.toString();
                    data[5] = int_smoker.toString();
                    data[6] = str_medication;
                    data[7] = str_medicalcondition;
                    data[8] = str_age;
                    data[9] = str_weight;
                    data[10] = str_securitycode;
                    data[11] = str_gender;
                    //change ip and path as necessary
                    PutData putData = new PutData("http://"+ ipAddress +"/c19php/EditDetails.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Details saved Successfully")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Please make sure you have filled all fields correctly", Toast.LENGTH_SHORT).show();
        }
        //Toast t = Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT);
        //t.show();


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
        if (!Password.getText().toString().equals(RepeatPassword.getText().toString())) {
            if (noErrors == true) {
                AlertDialog alertDialog = new AlertDialog.Builder(EditUser.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Your passwords do not match. Please try again.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            } else {
                noErrors = false;
            }
        }
        if (male.isChecked()==false && female.isChecked()==false) {
            noErrors = false;
            male.setError("Gender is required!");
            female.setError("Gender is required!");
        }
        if (isEmpty(Age)) {
            noErrors = false;
            Age.setError("Age is required!");
        }
        if (isEmpty(SecurityCode)) {
            noErrors = false;
            SecurityCode.setError("This is needed to verify that it is you when trying to reset your password.");
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
