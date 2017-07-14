package com.example.dtvta.testrestfulapi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dtvta.testrestfulapi.R;

/**
 * Created by vutuan on 14/07/2017.
 */

public class LocateFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_locate, container, false);


        return view;
    }

}
