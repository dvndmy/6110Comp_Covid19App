package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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

public class Dashboard extends AppCompatActivity {
    TextView displayName= (TextView) findViewById(R.id.textViewDisplayName);
    String ipAddress;
    public profile pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String userpage = preferences.getString("userpage","");
        Toast.makeText(getApplicationContext(), userpage, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "http://"+ ipAddress +"/c19php/GetProfileDetails.php?user="+userpage, response -> {
            try {
                //converting the string to json array object
                JSONArray array = new JSONArray(response);

                //traversing through all the object
                for (int i = 0; i < array.length(); i++) {

                    //getting product object from json array
                    JSONObject profilejson = array.getJSONObject(i);

                    //adding the product to product list
                    pName=new profile(profilejson.getString("fullname"),profilejson.getString("email"),profilejson.getInt("hospitalised"),profilejson.getInt("smoker"),profilejson.getString("medication"),profilejson.getString("medicalcondition"),profilejson.getInt("age"),profilejson.getInt("weight"),profilejson.getInt("securitycode"));


                }
                displayName.setText(pName.getFullname());

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
}