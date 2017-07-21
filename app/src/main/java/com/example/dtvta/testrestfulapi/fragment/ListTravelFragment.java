package com.example.dtvta.testrestfulapi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.adapter.AdapterListTravel;
import com.example.dtvta.testrestfulapi.common.Config;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.Travel;

import java.util.List;

/**
 * Created by vutuan on 17/07/2017.
 */

public class ListTravelFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lvTravel;
    private List<Travel> listTravel;
    private AdapterListTravel adapterListTravel;
    private int id_type=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_travel,container,false);
        lvTravel= (ListView) view.findViewById(R.id.lvTravel);

        getArgument();
        setupAdapter();

        lvTravel.setOnItemClickListener(this);

        return view;
    }

    private void getArgument(){
        Bundle bundle=getArguments();
        if (bundle!=null){
            id_type=bundle.getInt(Config.ID_TYPE);
        }
    }

    private void setupAdapter(){
        listTravel= Webservice.getListTravel(id_type);
        adapterListTravel=new AdapterListTravel(getContext(),listTravel);
        lvTravel.setAdapter(adapterListTravel);
        adapterListTravel.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        MapTravelFragment mapTravelFragment=new MapTravelFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Config.TRAVEL,listTravel.get(position));
        mapTravelFragment.setArguments(bundle);
        transaction.replace(R.id.content,mapTravelFragment).addToBackStack(Config.LIST_TRAVEL_FRAGMENT);
        transaction.commit();
    }
}
