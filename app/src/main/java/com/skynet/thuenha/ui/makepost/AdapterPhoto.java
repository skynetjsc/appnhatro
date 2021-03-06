package com.skynet.thuenha.ui.makepost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Image;
import com.skynet.thuenha.models.Utility;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPhoto extends RecyclerView.Adapter<AdapterPhoto.ViewHolder> {

    List<Image> list;
    Context context;
    ICallback iCallback;

    public AdapterPhoto(List<Image> list, Context context, ICallback iCallback) {
        this.iCallback = iCallback;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.make_a_post_photo_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (list.get(i).getImg() != null && !list.get(i).getImg().isEmpty()) {
            Picasso.with(context).load(list.get(i).getImg()).fit().centerCrop().into(viewHolder.img);

        } else if (list.get(i) != null && list.get(i).getFile() != null && list.get(i).getFile().exists()) {
            Picasso.with(context).load(list.get(i).getFile()).fit().centerCrop().into(viewHolder.img);
        }
        viewHolder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.clear)
        ImageView clear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
