package com.example.dtvta.testrestfulapi.fragment;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.DirectionApi;
import com.example.dtvta.testrestfulapi.common.GPSTracker;
import com.example.dtvta.testrestfulapi.common.StringFormatter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by vutuan on 14/07/2017.
 */

public class SearchFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    private GoogleMap mGoogleMap;
    private MapView mapViewSearch;
    private EditText edtSearch;
    private Button btnSearch;

    private GPSTracker gpsTracker;
    private Location myLocation;
    private Location myDestination;
    String polyline="";
    String destination;
    private DirectionApi directionApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        edtSearch= (EditText) view.findViewById(R.id.edtSearch);
        btnSearch= (Button) view.findViewById(R.id.btnSearch);
        directionApi=new DirectionApi(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mGoogleMap==null){
            mapViewSearch= (MapView) view.findViewById(R.id.mapViewSearch);
            mapViewSearch.onCreate(null);
            mapViewSearch.onResume();
        }
        mapViewSearch.getMapAsync(this);
        gpsTracker=new GPSTracker(getContext());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;

        getPresentPosition();

    }

    @Override
    public void onResume() {
        super.onResume();
        myLocation=gpsTracker.getLocation();
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnSearch:
                mGoogleMap.clear();
                getPresentPosition();
                destination=edtSearch.getText().toString();
                try {
                    destination= URLEncoder.encode(destination,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("des",destination);
                if (destination!=null && !destination.equals("")){
                    Log.d("des","click");
                    searchDestination(destination);
                }
                break;
        }
    }

    private void searchDestination(String destination) {
        String origin=myLocation.getLatitude()+","+myLocation.getLongitude();
        polyline=directionApi.getPolyLine(origin,destination);
        String polyLine=directionApi.getPolyLine(origin,destination);

        List<LatLng> listLine= PolyUtil.decode(polyLine);
        mGoogleMap.addPolyline(new PolylineOptions().addAll(listLine).color(Color.GREEN));

        LatLng myDestination= directionApi.getEndLocation(getContext(),origin,destination);
        mGoogleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("My destination")
                .snippet(destination)
                .position(myDestination)
        );
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(myDestination));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myDestination,15));

    }

    private void getPresentPosition(){
        LatLng myLoc=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions()
                .icon(new BitmapDescriptor(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED).zzIy()))
                .title("My location")
                .position(myLoc)
        );

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc,15));
    }

}
