package com.example.dtvta.testrestfulapi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.adapter.AdapterTypeTravel;
import com.example.dtvta.testrestfulapi.common.Config;
import com.example.dtvta.testrestfulapi.common.DowloadJSON;
import com.example.dtvta.testrestfulapi.common.JsonUtil;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.TypeTravel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lvTypeTravel;
    private List<TypeTravel> listTypeTravel;
    private AdapterTypeTravel adapterTypeTravel;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        lvTypeTravel= (ListView) view.findViewById(R.id.lvTypeTravel);
        setupAdapter();

        lvTypeTravel.setOnItemClickListener(this);
        return view;
    }

    private void setupAdapter() {
        listTypeTravel=new ArrayList<>();
        listTypeTravel= Webservice.getListTypeTravel();
        adapterTypeTravel=new AdapterTypeTravel(getContext(),listTypeTravel);
        lvTypeTravel.setAdapter(adapterTypeTravel);
        adapterTypeTravel.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        ListTravelFragment listTravelFragment=new ListTravelFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(Config.ID_TYPE,listTypeTravel.get(position).getID_TYPE());
        listTravelFragment.setArguments(bundle);
        transaction.replace(R.id.content,listTravelFragment).addToBackStack(Config.HOME_FRAGMENT);
        transaction.commit();
    }
}
