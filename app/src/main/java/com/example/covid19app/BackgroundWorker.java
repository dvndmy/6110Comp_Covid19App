package com.example.covid19app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type         = params[0];
        String login_url    = "https://10.0.2.2/login.php";
        String register_url = "https://10.0.2.2/register.php";
        if(type.equals("login")) {
            try {
                String email = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
           } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try {
                String fullName          = params[1];
                String email             = params[2];
                String password          = params[3];
                /*Integer hospitalised      = params[4];
                 Integer smoker            = params[5];*/
                String medication        = params[6];
                String medicalCondition  = params[7];
                String age               = params[8];
                String weight            = params[9];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("FullName","UTF-8")+"="+URLEncoder.encode(fullName ,"UTF-8")+"&"
                + URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                + URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                /*+ URLEncoder.encode("Hospitalised","UTF-8")+"="+URLEncoder.encode(hospitalised,"UTF-8")+"&"
                        + URLEncoder.encode("Smoker","UTF-8")+"="+URLEncoder.encode(Smoker,"UTF-8")+"&"*/
                        + URLEncoder.encode("Medication","UTF-8")+"="+URLEncoder.encode(medication,"UTF-8"
                        + URLEncoder.encode("MedicalCondition","UTF-8")+"="+URLEncoder.encode(medicalCondition,"UTF-8")+"&"
                        + URLEncoder.encode("Age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                        + URLEncoder.encode("Weight","UTF-8")+"="+URLEncoder.encode(weight,"UTF-8")+"&");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status" );
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}