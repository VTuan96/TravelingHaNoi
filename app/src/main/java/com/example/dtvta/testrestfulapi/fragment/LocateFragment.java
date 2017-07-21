package com.example.dtvta.testrestfulapi.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.DirectionApi;
import com.example.dtvta.testrestfulapi.common.DowloadJSON;
import com.example.dtvta.testrestfulapi.common.GPSTracker;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.Distance;
import com.example.dtvta.testrestfulapi.model.Travel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 14/07/2017.
 */

public class LocateFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GPSTracker gpsTracker;
    private Location myLocation;
    private List<Travel> listTravel;

    private List<Travel> listTargetTravel;

    private GoogleMap mGoogleMap;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locate, container, false);
        setHasOptionsMenu(true);
        gpsTracker = new GPSTracker(getContext());

        setupTravel();

        return view;
    }

    private void setupTravel() {
        listTravel=new ArrayList<>();
        listTravel = Webservice.getListTravel();
        Log.d("size", listTravel.size() + "");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map_your_location);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_locate, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnu_locate:
                //get all target that distance's value < 5 Km
                getAllTarget();
                showAllTarget();
                mGoogleMap.setOnMarkerClickListener(this);
                break;
        }
        return true;
    }

    private void showAllTarget() {
        int length = listTargetTravel.size();
        if (length != 0) {
            LatLng here = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
            Marker markerOrigin=mGoogleMap.addMarker(new MarkerOptions()
                    .position(here)
                    .title("My location"));

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(here));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(here,12));
            Toast.makeText(getContext(),getResources().getString(R.string.number_target)+": "+length,Toast.LENGTH_LONG).show();
            for (int i = 0; i < length; i++) {
                Travel targetTravel=listTargetTravel.get(i);
                LatLng latLngYourLocation = new LatLng(targetTravel.getLATTITUDE(), targetTravel.getLONGTITUDE());
                Marker markerYourLocation = mGoogleMap.addMarker(new MarkerOptions()
                        .title("Your destination")
                        .position(latLngYourLocation)
                        .snippet(targetTravel.getNAME_TRAVEL())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                mGoogleMap.setOnMarkerClickListener(this);
            }

        }

    }

    private void getAllTarget() {
        setupTravel();
        listTargetTravel=new ArrayList<>();
        String orgin = myLocation.getLatitude() + "," + myLocation.getLongitude();
        int length = listTravel.size();
        for (int i = 0; i < length; i++) {
            Travel temp = listTravel.get(i);
            String destination = temp.getLATTITUDE() + "," + temp.getLONGTITUDE();
            Distance distance = new DirectionApi().getDistance(orgin, destination);
            Log.d("distance", distance.toString());
            if (distance.isTarget()) {
                listTargetTravel.add(temp);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        LatLng here = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        Marker markerOrigin=mGoogleMap.addMarker(new MarkerOptions()
                .position(here)
                .title("My location"));
        markerOrigin.showInfoWindow();

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(here,15));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gpsTracker.canGetLocation()){
            myLocation=gpsTracker.getLocation();
            Log.d("location",myLocation.toString());
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }
}
