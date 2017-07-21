package com.example.dtvta.testrestfulapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.model.Description;
import com.example.dtvta.testrestfulapi.model.TypeTravel;

import java.util.List;

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
        holder.txtNameType.setText(listTypeTravel.get(position).getNAME_TYPE());

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
