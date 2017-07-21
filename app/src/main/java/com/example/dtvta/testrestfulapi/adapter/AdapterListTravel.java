package com.example.dtvta.testrestfulapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.DownloadImage;
import com.example.dtvta.testrestfulapi.common.Webservice;
import com.example.dtvta.testrestfulapi.model.Description;
import com.example.dtvta.testrestfulapi.model.Travel;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 17/07/2017.
 */

public class AdapterListTravel extends BaseAdapter {
    private Context context;
    private List<Travel> listTravel;

    public AdapterListTravel(Context context, List<Travel> listTravel) {
        this.context = context;
        this.listTravel = listTravel;
    }

    @Override
    public int getCount() {
        return listTravel.size();
    }

    @Override
    public Object getItem(int position) {
        return listTravel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listTravel.get(position).getID_TRAVEL();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_item_list_travel,parent,false);
        }
        ViewHolderListTravel holder=new ViewHolderListTravel(convertView);
        holder.txtNameTravel.setText(listTravel.get(position).getNAME_TRAVEL());
        int id_description=listTravel.get(position).getID_DESCRIPTION();
        Description description= Webservice.getDescription(id_description);
        try {
            Bitmap bitmap=new DownloadImage().execute(description.getIMAGE_DESCRIPTION()).get();
            holder.imgTravel.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return convertView;
    }

//    public class DownloadImage extends AsyncTask<String,Void,Bitmap>{
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            Bitmap bitmap=null;
//            try {
//                URL url=new URL(params[0]);
//                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream inputStream=connection.getInputStream();
//                bitmap= BitmapFactory.decodeStream(inputStream);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//    }

    public class ViewHolderListTravel{
        private TextView txtNameTravel;
        private ImageView imgTravel;
        private ImageButton imgDetail, imgLocate;
        public ViewHolderListTravel(View view){
            txtNameTravel= (TextView) view.findViewById(R.id.txtNameTravel);
            imgTravel= (ImageView) view.findViewById(R.id.imgTravel);
            imgDetail= (ImageButton) view.findViewById(R.id.imgDetail);
            imgLocate= (ImageButton) view.findViewById(R.id.imgLocate);
        }
    }
}
