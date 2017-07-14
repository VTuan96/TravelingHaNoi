package com.example.dtvta.testrestfulapi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.adapter.AdapterTypeTravel;
import com.example.dtvta.testrestfulapi.model.TypeTravel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView lvTypeTravel;
    private List<TypeTravel> listTypeTravel;
    private AdapterTypeTravel adapterTypeTravel;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        lvTypeTravel= (ListView) view.findViewById(R.id.lvTypeTravel);

        setupAdapter();

        return view;
    }

    private void setupAdapter() {
        listTypeTravel=new ArrayList<>();
        adapterTypeTravel=new AdapterTypeTravel(getContext(),listTypeTravel);
        lvTypeTravel.setAdapter(adapterTypeTravel);
        adapterTypeTravel.notifyDataSetChanged();
    }

}
