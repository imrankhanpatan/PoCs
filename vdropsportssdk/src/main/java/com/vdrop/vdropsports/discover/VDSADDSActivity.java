package com.vdrop.vdropsports.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.model.AddImageUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dennis on 11/7/17.
 */

public class VDSADDSActivity extends RecyclerView.Adapter<VDSADDSActivity.ViewHolder> {

    private Context context;
    private List<AddImageUrl> addImageUrls;

    public VDSADDSActivity(Context context,List<AddImageUrl> addImageUrls) {
        this.context = context;
        this.addImageUrls = addImageUrls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vds_adds_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(addImageUrls.get(position).getImage_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return addImageUrls.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.vds_iv_ads_image);
        }
    }
}
