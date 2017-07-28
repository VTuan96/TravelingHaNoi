package com.example.dtvta.testrestfulapi.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.adapter.AdapterRelativeImage;
import com.example.dtvta.testrestfulapi.common.Config;
import com.example.dtvta.testrestfulapi.common.DownloadImage;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.Description;
import com.example.dtvta.testrestfulapi.model.Detail;
import com.example.dtvta.testrestfulapi.model.Travel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 21/07/2017.
 */

public class DetailTravelFragment extends Fragment {

    private TextView txtDetailNameTravel;
    private ImageView imgDetailTravel;
    private TextView txtIntroduce;

    private RecyclerView rvRelativeImage;
    private List<Detail> listDetail;
    private AdapterRelativeImage adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Travel travel;
    private Description description;
    private Bundle bundle;
    private Webservice webservice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail_travel,container,false);

        txtDetailNameTravel= (TextView) view.findViewById(R.id.txtDetailNameTravel);
        imgDetailTravel= (ImageView) view.findViewById(R.id.imgDetailTravel);
        rvRelativeImage= (RecyclerView) view.findViewById(R.id.rvRelativeImage);
        txtIntroduce= (TextView) view.findViewById(R.id.txtIntroduce);

        webservice=new Webservice(getContext());
        getArgument();
        setupAdapter();

        txtDetailNameTravel.setText(travel.getNAME_TRAVEL());
        try {
            Bitmap bitmap=new DownloadImage(getContext()).execute(description.getIMAGE_DESCRIPTION()).get();
            imgDetailTravel.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        txtIntroduce.setText(description.getINTRODUCTION());
        txtIntroduce.setMovementMethod(new ScrollingMovementMethod());

        adapter.setOnItemClickListener(new AdapterRelativeImage.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Detail detail=listDetail.get(position);
                txtDetailNameTravel.setText(detail.getTITLE());
                try {
                    Bitmap bitmap=new DownloadImage(getContext()).execute(detail.getIMAGE_DETAIL()).get();
                    imgDetailTravel.setImageBitmap(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        return view;
    }

    private void setupAdapter(){
        listDetail=new ArrayList<>();
        listDetail=webservice.getListDetail(description.getID_DESCRIPTION());
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        adapter=new AdapterRelativeImage(getContext(),listDetail);
        rvRelativeImage.setAdapter(adapter);
        rvRelativeImage.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    private void getArgument(){
        bundle=getArguments();
        if (bundle!=null){
            travel= (Travel) bundle.getSerializable(Config.TRAVEL);
            description= webservice.getDescription(travel.getID_DESCRIPTION());
        }
    }
}
