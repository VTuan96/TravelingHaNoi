package com.example.dtvta.testrestfulapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.DownloadImage;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.Description;
import com.example.dtvta.testrestfulapi.model.Travel;
import com.example.dtvta.testrestfulapi.model.TypeTravel;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 14/07/2017.
 */

public class AdapterTypeTravel extends BaseAdapter {
    private Context context;
    private List<TypeTravel> listTypeTravel;

    public AdapterTypeTravel(Context context, List<TypeTravel> listTypeTravel){
        this.context=context;
        this.listTypeTravel=listTypeTravel;
    }
    @Override
    public int getCount() {
        return listTypeTravel.size();
    }

    @Override
    public Object getItem(int position) {
        return listTypeTravel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listTypeTravel.get(position).getID_TYPE();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_item_home,parent,false);
        }
        ViewHolderTypeTravel holder=new ViewHolderTypeTravel(convertView);
        TypeTravel typeTravel=listTypeTravel.get(position);
        Log.d("id_type",typeTravel.getID_TYPE()+"");
        holder.txtNameType.setText(typeTravel.getNAME_TYPE());

        Travel lastestTravel= Webservice.getLastestTravel(typeTravel.getID_TYPE());
        if (lastestTravel!=null){
            Description latestDescription=Webservice.getDescription(lastestTravel.getID_DESCRIPTION());
            String imageDescription=latestDescription.getIMAGE_DESCRIPTION();
            Log.d("image description",imageDescription);
            try {
                if (imageDescription!=null || !imageDescription.equals("")){
                    Bitmap bitmap=new DownloadImage().execute(imageDescription).get();
                    holder.imgTypeTravel.setImageBitmap(bitmap);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }

    public class ViewHolderTypeTravel{
        private TextView txtNameType;
        private ImageView imgTypeTravel;
        public ViewHolderTypeTravel(View view){
            txtNameType= (TextView) view.findViewById(R.id.txtNameType);
            imgTypeTravel= (ImageView) view.findViewById(R.id.imgTypeTravel);
        }
    }
}
