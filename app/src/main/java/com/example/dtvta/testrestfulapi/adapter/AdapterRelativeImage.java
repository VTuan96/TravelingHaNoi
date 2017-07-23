package com.example.dtvta.testrestfulapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dtvta.testrestfulapi.R;
import com.example.dtvta.testrestfulapi.common.DownloadImage;
import com.example.dtvta.testrestfulapi.model.Detail;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vutuan on 23/07/2017.
 */

//public class AdapterRelativeImage extends BaseAdapter {
//    private Context context;
//    private List<Detail> listDetail;
//
//    public AdapterRelativeImage(Context context, List<Detail> listDetail) {
//        this.context = context;
//        this.listDetail = listDetail;
//    }
//
//    @Override
//    public int getCount() {
//        return listDetail.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listDetail.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return listDetail.get(position).getID_DETAIL();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView==null){
//            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_item_relative_image,parent,false);
//        }
//        ViewHolderRelativeImage holder=new ViewHolderRelativeImage(convertView);
//        Detail detail=listDetail.get(position);
//        holder.txtNameType.setText(detail.getTITLE());
//        try {
//            Bitmap bitmap=new DownloadImage().execute(detail.getIMAGE_DETAIL()).get();
//            holder.imgTypeTravel.setImageBitmap(bitmap);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//        return convertView;
//    }
//
//    public class ViewHolderRelativeImage{
//        private TextView txtNameType; //title
//        private ImageView imgTypeTravel; //image detail
//        public ViewHolderRelativeImage(View view){
//            txtNameType= (TextView) view.findViewById(R.id.txtNameType);
//            imgTypeTravel= (ImageView) view.findViewById(R.id.imgTypeTravel);
//        }
//    }
//}

public class AdapterRelativeImage extends RecyclerView.Adapter<AdapterRelativeImage.ViewHolderRelativeImage>{

    private Context context;
    private List<Detail> listDetail;
    public static ClickListener listener;

    public AdapterRelativeImage(Context context, List<Detail> listDetail) {
        this.context = context;
        this.listDetail = listDetail;
    }

    public interface ClickListener{
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    };

    @Override
    public ViewHolderRelativeImage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout_item_relative_image,parent,false);
        ViewHolderRelativeImage holderRelativeImage=new ViewHolderRelativeImage(view);

        return holderRelativeImage;
    }

    @Override
    public void onBindViewHolder(ViewHolderRelativeImage holder, int position) {
        Detail detail=listDetail.get(position);
        holder.txtNameType.setText(detail.getTITLE());
        try {
            Bitmap bitmap=new DownloadImage().execute(detail.getIMAGE_DETAIL()).get();
            holder.imgTypeTravel.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        View view=holder.imgTypeTravel.getRootView();
        view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listDetail.size();
    }

    public void setOnItemClickListener(ClickListener listener){
        AdapterRelativeImage.listener=listener;
    }


    public static class ViewHolderRelativeImage extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView txtNameType; //title
        private ImageView imgTypeTravel; //image detail

            public ViewHolderRelativeImage(View itemView) {
                super(itemView);
                txtNameType= (TextView) itemView.findViewById(R.id.txtNameType);
                imgTypeTravel= (ImageView) itemView.findViewById(R.id.imgTypeTravel);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }


        @Override
        public void onClick(View v) {
            int position= (int) v.getTag();
            listener.onItemClick(position,v);
        }

        @Override
        public boolean onLongClick(View v) {
            int position= (int) v.getTag();
            listener.onItemClick(position,v);
            return true;
        }
    }

}
