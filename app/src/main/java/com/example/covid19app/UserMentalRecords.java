package com.example.covid19app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserMentalRecords extends AppCompatActivity {
    EditText txtvalue;
    Button btnfetch;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_symptom_search);
        txtvalue = (EditText) findViewById(R.id.editText);
        btnfetch = (Button) findViewById(R.id.buttonfetch);
        listview = (ListView) findViewById(R.id.listView);
        getData();
        btnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    public void OpenDash(View view){
        startActivity(new Intent(this, DoctorDashboard.class));

    }
    private void getData() {

        //Todo

        String value = txtvalue.getText().toString().trim();

//        if (value.equals("")) {
//            Toast.makeText(this, "Please Enter Data Value", Toast.LENGTH_LONG).show();
//            return;
//        }

        String url = MentalRecordConfig.DATA_URL + txtvalue.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserMentalRecords.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {

        //Todo
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(MentalRecordConfig.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String mood = jo.getString(MentalRecordConfig.KEY_mood);
                String enterdate = jo.getString(MentalRecordConfig.KEY_enterdate);
                String stresslevel = jo.getString(MentalRecordConfig.KEY_stresslevel);
                String pemail = jo.getString(MentalRecordConfig.KEY_pemail);
                String energy = jo.getString(MentalRecordConfig.KEY_energy);



                final HashMap<String, String> employees = new HashMap<>();
                employees.put(MentalRecordConfig.KEY_mood, "mood = " + mood);
                employees.put(MentalRecordConfig.KEY_enterdate,"Date: "+ enterdate);
                employees.put(MentalRecordConfig.KEY_stresslevel,"stresslevel = " + stresslevel);
                employees.put(MentalRecordConfig.KEY_pemail,"E-mail: " + pemail);
                employees.put(MentalRecordConfig.KEY_energy,"energy = " + energy);

                list.add(employees);

            }

        //Todo
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                UserMentalRecords.this, list, R.layout.activity_user_mental_result,
                new String[]{MentalRecordConfig.KEY_enterdate, MentalRecordConfig.KEY_pemail, MentalRecordConfig.KEY_mood,  MentalRecordConfig.KEY_stresslevel,  MentalRecordConfig.KEY_energy},
                new int[]{R.id.enterdate, R.id.pemail, R.id.mood, R.id.stresslevel, R.id.energy});

        listview.setAdapter(adapter);

    }
}
