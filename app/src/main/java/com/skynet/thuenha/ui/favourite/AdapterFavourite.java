package com.skynet.thuenha.ui.favourite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.ViewHolder> {
    List<Post> list;
    Context context;
    ICallbackTwoM iCallback;


    public AdapterFavourite(List<Post> list, Context context, ICallbackTwoM iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourite_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(String.format("%,.0fđ/tháng",list.get(i).getPrice()));
        viewHolder.tvAddress.setText(list.get(i).getAddress());
        viewHolder.tvArea.setText(list.get(i).getArea() + context.getString(R.string.area_unit));
        Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.checkBox2)
        CheckBox checkBox2;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvBed)
        TextView tvBed;
        @BindView(R.id.tvWashingRoom)
        TextView tvWashingRoom;
        @BindView(R.id.tvArea)
        TextView tvArea;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
