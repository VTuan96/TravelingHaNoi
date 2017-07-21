package com.example.dtvta.testrestfulapi.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vutuan on 21/07/2017.
 */

public class DownloadImage extends AsyncTask<String,Void,Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap=null;
        try {
            URL url=new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
