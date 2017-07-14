package com.example.dtvta.testrestfulapi.common;

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
    private StringBuilder builder=new StringBuilder();
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
}
