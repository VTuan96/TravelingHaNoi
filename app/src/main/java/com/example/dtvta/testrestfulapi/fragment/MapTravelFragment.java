package com.example.dtvta.testrestfulapi.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.Config;
import com.example.dtvta.testrestfulapi.common.DirectionApi;
import com.example.dtvta.testrestfulapi.common.GPSTracker;
import com.example.dtvta.testrestfulapi.model.Distance;
import com.example.dtvta.testrestfulapi.model.Travel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

/**
 * Created by vutuan on 17/07/2017.
 */

public class MapTravelFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private MapView mapView;

    private String origin = "", destination = "";
    private Location myLocation;
    private GPSTracker gpsTracker;

    private LatLng destinationLocation;
    private Marker markerDestination;
    private Marker markerOrigin;
    private Travel travel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_travel, container, false);
        gpsTracker=new GPSTracker(getContext());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getArguments();
        if (bundle!=null){
            travel= (Travel) bundle.getSerializable(Config.TRAVEL);
            destinationLocation=new LatLng(travel.getLATTITUDE(),travel.getLONGTITUDE());
            Log.d("lat create",travel.getLATTITUDE()+"");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gpsTracker.canGetLocation()){
            myLocation=gpsTracker.getLocation();
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
        markerOrigin=mGoogleMap.addMarker(new MarkerOptions()
                .position(here)
                .title("My location").draggable(true));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(here,13));


        origin = myLocation.getLatitude()+ "," + myLocation.getLongitude();
        destination = destinationLocation.latitude + "," + destinationLocation.longitude;
        Distance distance=new DirectionApi(getContext()).getDistance(origin,destination);

        Log.d("marker",distance.getText());

        markerDestination=mGoogleMap.addMarker(new MarkerOptions()
                .position(destinationLocation)
                .title(travel.getNAME_TRAVEL())
                .snippet(distance.getText())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).draggable(true));

        String polyLine=new DirectionApi(getContext()).getPolyLine(origin,destination);

        List<LatLng> listLine= PolyUtil.decode(polyLine);
        mGoogleMap.addPolyline(new PolylineOptions().addAll(listLine).color(Color.GREEN));
        Log.d("lat",myLocation.getLatitude()+"");

    }


}
