package com.example.dtvta.testrestfulapi.common;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.dtvta.testrestfulapi.model.Distance;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 18/07/2017.
 */

public class DirectionApi {
    public static final String JSON_REQUEST_URL="https://maps.googleapis.com/maps/api/directions/json?";
    public static final String QUERY_ORIGIN="origin=";
    public static final String QUERY_DESTINATION="&destination=";
    public static final String QUERY_KEY="&key=";
    public static final String API_KEY_DIRECTION="AIzaSyBQTnHfSUcb4NH7J3tBKl2lKZoANqomFL0";

    public Context context;

    public DirectionApi(Context context) {
        this.context = context;
    }

    public String getPolyLine(String origin, String destination){
        StringBuilder builder=new StringBuilder();
        String url=new StringBuilder(JSON_REQUEST_URL).append(QUERY_ORIGIN).append(origin).append(QUERY_DESTINATION)
                .append(destination).append(QUERY_KEY).append(API_KEY_DIRECTION).toString();
        Log.d("url",url);
        try {
            String data=new DowloadJSON(context).execute(url).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            Log.d("root",root.toString());
            JSONArray arrayRoutes=JsonUtil.getJSONArray(root,"routes");
            JSONObject objectData=JsonUtil.getJSONObject(arrayRoutes,0);
            JSONObject overview_polyline=JsonUtil.getJSONObject(objectData,"overview_polyline");
            String polyLine=overview_polyline.getString("points");
            Log.d("polyline",polyLine);
            builder.append(polyLine);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return builder.toString();

    }

    public Distance getDistance(String origin, String destination){
        Distance result=new Distance();
        String url=new StringBuilder(JSON_REQUEST_URL).append(QUERY_ORIGIN).append(origin).append(QUERY_DESTINATION)
                .append(destination).append(QUERY_KEY).append(API_KEY_DIRECTION).toString();
        Log.d("url",url);
        try {
            String data=new DowloadJSON(context).execute(url).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            Log.d("root",root.toString());
            JSONArray arrayRoutes=JsonUtil.getJSONArray(root,"routes");
            JSONObject objectData=JsonUtil.getJSONObject(arrayRoutes,0);
            JSONArray legs=JsonUtil.getJSONArray(objectData,"legs");
            JSONObject itemLegs=JsonUtil.getJSONObject(legs,0);
            JSONObject distance=JsonUtil.getJSONObject(itemLegs,"distance");
            String text=distance.getString("text");
            int value=distance.getInt("value");
            result=new Distance(value,text);
            Log.d("polyline",result.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }

    public LatLng getEndLocation(Context context, String origin, String destination){
        GPSTracker gpsTracker=new GPSTracker(context);
        LatLng result=new LatLng(0,0);
        String url=new StringBuilder(JSON_REQUEST_URL).append(QUERY_ORIGIN).append(origin).append(QUERY_DESTINATION)
                .append(destination).append(QUERY_KEY).append(API_KEY_DIRECTION).toString();
        Log.d("url",url);
        try {
            String data=new DowloadJSON(context).execute(url).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            Log.d("root",root.toString());
            JSONArray arrayRoutes=JsonUtil.getJSONArray(root,"routes");
            JSONObject objectData=JsonUtil.getJSONObject(arrayRoutes,0);
            JSONArray legs=JsonUtil.getJSONArray(objectData,"legs");
            JSONObject itemLegs=JsonUtil.getJSONObject(legs,0);
            JSONObject distance=JsonUtil.getJSONObject(itemLegs,"end_location");
            double lat=distance.getDouble("lat");
            double lng=distance.getDouble("lng");
            result=new LatLng(lat,lng);
            Log.d("polyline",result.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
