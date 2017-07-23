package com.example.dtvta.testrestfulapi.common;

import android.util.Log;

import com.example.dtvta.testrestfulapi.model.Description;
import com.example.dtvta.testrestfulapi.model.Detail;
import com.example.dtvta.testrestfulapi.model.Travel;
import com.example.dtvta.testrestfulapi.model.TypeTravel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 17/07/2017.
 */

public class Webservice {
    public static List<TypeTravel> getListTypeTravel(){
        List<TypeTravel> listTypeTravel=new ArrayList<>();
        try {
            String data=new DowloadJSON().execute(Config.URL_TYPE_TRAVEL).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray type=JsonUtil.getJSONArray(root,"TYPE");
            for (int i=0;i<type.length();i++){
                JSONObject itemType=JsonUtil.getJSONObject(type,i);
                TypeTravel typeTravel=new TypeTravel();
                typeTravel.setID_TYPE(itemType.getInt("ID_TYPE"));
                typeTravel.setNAME_TYPE(itemType.getString("NAME_TYPE"));
                listTypeTravel.add(typeTravel);
            }
            Log.d("tag",type.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listTypeTravel;
    }

    public static List<Travel> getListTravel(){
        List<Travel> listTravel=new ArrayList<>();
        try {
            String data=new DowloadJSON().execute(Config.URL_TRAVEL+"getTravel").get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray travelArray=JsonUtil.getJSONArray(root,"TRAVEL");
            int length=travelArray.length();
            for (int i=0;i<length;i++){
                JSONObject itemTravel=JsonUtil.getJSONObject(travelArray,i);
                int id_travel=itemTravel.getInt("ID_TYPE");
                int id_desription=itemTravel.getInt("ID_DESCRIPTION");
                String name_travel=itemTravel.getString("NAME_TRAVEL");
                float latitude= (float) itemTravel.getDouble("LATITUDE");
                float longtitude= (float) itemTravel.getDouble("LONGTITUDE");
                int id_type=itemTravel.getInt("ID_TYPE");
                Travel travel=new Travel(id_travel,id_type,id_desription,latitude,longtitude,name_travel);
                listTravel.add(travel);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listTravel;
    }

    public static List<Travel> getListTravel(int id_type){
        List<Travel> listTravel=new ArrayList<>();
        try {
            String data=new DowloadJSON().execute(Config.URL_TRAVEL+"getTypeTravel&idtype="+id_type).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray travelArray=JsonUtil.getJSONArray(root,"TRAVEL");
            int length=travelArray.length();
            for (int i=0;i<length;i++){
                JSONObject itemTravel=JsonUtil.getJSONObject(travelArray,i);
                int id_travel=itemTravel.getInt("ID_TYPE");
                int id_desription=itemTravel.getInt("ID_DESCRIPTION");
                String name_travel=itemTravel.getString("NAME_TRAVEL");
                float latitude= (float) itemTravel.getDouble("LATITUDE");
                float longtitude= (float) itemTravel.getDouble("LONGTITUDE");
                Travel travel=new Travel(id_travel,id_type,id_desription,latitude,longtitude,name_travel);
                listTravel.add(travel);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listTravel;
    }

    public static Description getDescription(int id_description){
        Description description=new Description();
        try {
            String data=new DowloadJSON().execute(Config.URL_DESCRIPTION+"?id="+id_description).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray arrayDescription=JsonUtil.getJSONArray(root,"DESCRIPTION");
            int length=arrayDescription.length();
            for (int i=0;i<length;i++){
                JSONObject itemDescription=JsonUtil.getJSONObject(arrayDescription,i);
                String introduction=itemDescription.getString("INTRODUCTION");
                String image=itemDescription.getString("IMAGE_DESCRIPTION");
                description=new Description(id_description,image,introduction);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }

    public static Travel getLastestTravel(int id_type){
        List<Travel> listTravel=new ArrayList<>();
        try {
            String data=new DowloadJSON().execute(Config.URL_TRAVEL+"getTypeTravel&idtype="+id_type).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray travelArray=JsonUtil.getJSONArray(root,"TRAVEL");
            int length=travelArray.length();
            for (int i=0;i<length;i++){
                JSONObject itemTravel=JsonUtil.getJSONObject(travelArray,i);
                int id_travel=itemTravel.getInt("ID_TYPE");
                int id_desription=itemTravel.getInt("ID_DESCRIPTION");
                String name_travel=itemTravel.getString("NAME_TRAVEL");
                float latitude= (float) itemTravel.getDouble("LATITUDE");
                float longtitude= (float) itemTravel.getDouble("LONGTITUDE");
                Travel travel=new Travel(id_travel,id_type,id_desription,latitude,longtitude,name_travel);
                listTravel.add(travel);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int length=listTravel.size();
        if (length!=0)
            return listTravel.get(length-1);
        else return null;
    }

    public static List<Detail> getListDetail(int id_description){
        List<Detail> listDetail=new ArrayList<>();
        try {
            String data=new DowloadJSON().execute(Config.URL_DETAIL+id_description).get();
            JSONObject root=JsonUtil.createJSONObject(data);
            JSONArray travelArray=JsonUtil.getJSONArray(root,"DETAIL");
            int length=travelArray.length();
            for (int i=0;i<length;i++){
                JSONObject itemDetail=JsonUtil.getJSONObject(travelArray,i);
                int id_detail=itemDetail.getInt("ID_DETAIL");
                String title=itemDetail.getString("TITLE");
                String image_detail=itemDetail.getString("IMAGE_DETAIL");
                Detail detail=new Detail(id_detail,id_description,image_detail,title);
                listDetail.add(detail);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listDetail;
    }
}
