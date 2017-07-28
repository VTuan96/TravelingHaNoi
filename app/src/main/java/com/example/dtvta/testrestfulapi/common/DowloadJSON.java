package com.example.dtvta.testrestfulapi.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vutuan on 14/07/2017.
 */

public class DowloadJSON extends AsyncTask<String,Void,String> {
    private ProgressDialog progressDialog;
    private Context context;
    private int total=0;

    public DowloadJSON(Context context) {
        this.context = context;
    }

    private StringBuilder builder=new StringBuilder();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Loading ... !");
        progressDialog.setMessage("Please waiting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream=connection.getInputStream();
            InputStreamReader reader=new InputStreamReader(inputStream);
            BufferedReader buffer=new BufferedReader(reader);
            String line="";
            while ((line=buffer.readLine())!=null){
                builder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
