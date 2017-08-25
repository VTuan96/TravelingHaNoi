package com.example.dtvta.testrestfulapi.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.dtvta.testrestfulapi.R;

/**
 * Created by vutuan on 14/07/2017.
 */

public class SettingFragment extends Fragment {
    private CheckBox ckbTitleColor;
    private SharedPreferences preferences;
    private String TITILE_COLOR;
    private String COLOR;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        ckbTitleColor= (CheckBox) view.findViewById(R.id.ckbTitleColor);

        preferences=getActivity().getSharedPreferences(TITILE_COLOR, Context.MODE_PRIVATE);
        boolean check=preferences.getBoolean(COLOR,true);
        ckbTitleColor.setChecked(check);
        Log.d("check",check+"");
        if (check){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        }

        ckbTitleColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Log.d("alert","not checked!");
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(COLOR,false);
                    editor.commit();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
                } else
                {
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(COLOR,true);
                    editor.commit();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

                }
            }
        });



        return view;
    }

}
