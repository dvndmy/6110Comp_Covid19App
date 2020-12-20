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

public class UserSymptomRecords extends AppCompatActivity {
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

        String value = txtvalue.getText().toString().trim();

//        if (value.equals("")) {
//            Toast.makeText(this, "Please Enter Data Value", Toast.LENGTH_LONG).show();
//            return;
//        }

        String url = SymptomRecordConfig.DATA_URL + txtvalue.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserSymptomRecords.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(SymptomRecordConfig.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String cough = jo.getString(SymptomRecordConfig.KEY_cough);
                String enterdate = jo.getString(SymptomRecordConfig.KEY_enterdate);
                String breathlessness = jo.getString(SymptomRecordConfig.KEY_breathlessness);
                String pemail = jo.getString(SymptomRecordConfig.KEY_pemail);
                String lossofTaste = jo.getString(SymptomRecordConfig.KEY_lossofTaste);
                String lossofSmell = jo.getString(SymptomRecordConfig.KEY_lossofSmell);
                String highTemprature = jo.getString(SymptomRecordConfig.KEY_highTemprature);
                String chills = jo.getString(SymptomRecordConfig.KEY_chills);
                String headache = jo.getString(SymptomRecordConfig.KEY_headache);
                String muscleAche = jo.getString(SymptomRecordConfig.KEY_muscleAche);
                String soreThroat = jo.getString(SymptomRecordConfig.KEY_soreThroat);
                String congestedNose = jo.getString(SymptomRecordConfig.KEY_congestedNose);
                String nausea = jo.getString(SymptomRecordConfig.KEY_nausea);
                String diarrhea = jo.getString(SymptomRecordConfig.KEY_diarrhea);
                String other = jo.getString(SymptomRecordConfig.KEY_other);


                final HashMap<String, String> employees = new HashMap<>();
                employees.put(SymptomRecordConfig.KEY_cough, "Cough = " + cough);
                employees.put(SymptomRecordConfig.KEY_enterdate,"Date: "+ enterdate);
                employees.put(SymptomRecordConfig.KEY_breathlessness,"breathlessness = " + breathlessness);
                employees.put(SymptomRecordConfig.KEY_pemail,"E-mail: " + pemail);
                employees.put(SymptomRecordConfig.KEY_lossofTaste,"lossofTaste = " + lossofTaste);
                employees.put(SymptomRecordConfig.KEY_lossofSmell,"lossofSmell = " + lossofSmell);
                employees.put(SymptomRecordConfig.KEY_highTemprature,"highTemprature = " + highTemprature);
                employees.put(SymptomRecordConfig.KEY_chills, "chills = " + chills);
                employees.put(SymptomRecordConfig.KEY_headache,"headache = " + headache);
                employees.put(SymptomRecordConfig.KEY_muscleAche,"muscleAche = " + muscleAche);
                employees.put(SymptomRecordConfig.KEY_soreThroat,"soreThroat = " + soreThroat);
                employees.put(SymptomRecordConfig.KEY_congestedNose,"congestedNose = " + congestedNose);
                employees.put(SymptomRecordConfig.KEY_nausea,"nausea = " + nausea);
                employees.put(SymptomRecordConfig.KEY_diarrhea,"diarrhea = " + diarrhea);
                employees.put(SymptomRecordConfig.KEY_other,"other = " + other);

                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                UserSymptomRecords.this, list, R.layout.activity_user_symptom_result,
                new String[]{SymptomRecordConfig.KEY_enterdate, SymptomRecordConfig.KEY_pemail, SymptomRecordConfig.KEY_cough,  SymptomRecordConfig.KEY_breathlessness,  SymptomRecordConfig.KEY_lossofTaste, SymptomRecordConfig.KEY_lossofSmell, SymptomRecordConfig.KEY_highTemprature, SymptomRecordConfig.KEY_chills, SymptomRecordConfig.KEY_headache, SymptomRecordConfig.KEY_muscleAche, SymptomRecordConfig.KEY_soreThroat, SymptomRecordConfig.KEY_congestedNose, SymptomRecordConfig.KEY_nausea, SymptomRecordConfig.KEY_diarrhea, SymptomRecordConfig.KEY_other},
                new int[]{R.id.enterdate, R.id.pemail, R.id.cough, R.id.breathlessness, R.id.lossofTaste, R.id.lossofSmell, R.id.highTemprature, R.id.chills, R.id.headache, R.id.muscleAche, R.id.soreThroat, R.id.congestedNose, R.id.nausea, R.id.diarrhea, R.id.other});

        listview.setAdapter(adapter);

    }
}
