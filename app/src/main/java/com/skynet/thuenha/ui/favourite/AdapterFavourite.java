package com.skynet.thuenha.ui.favourite;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    SparseBooleanArray cachedBoolen;
    SparseBooleanArray cachedBoolenPrice;

    public AdapterFavourite(List<Post> list, Context context, ICallbackTwoM iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        cachedBoolen = new SparseBooleanArray();
        cachedBoolenPrice = new SparseBooleanArray();
        for (int i = 0; i < this.list.size(); i++) {
            list.get(i).setChecked(true);
            list.get(i).setColor(list.get(i).getPrice() == 0 ?ContextCompat.getColor(context,R.color.green) : Color.BLACK );
            cachedBoolen.put(i, true);
            cachedBoolenPrice.put(i, list.get(i).getPrice() == 0);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourite_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getPrice() == 0 ? "Giá liên hệ":String.format("%,.0fVNĐ/Tháng", list.get(i).getPrice()));
        viewHolder.tvName.setTextColor(cachedBoolenPrice.get(i) ? ContextCompat.getColor(context, R.color.green) : Color.BLACK);
        viewHolder.tvAddress.setText(list.get(i).getAddress());
        viewHolder.tvArea.setText(String.format("%,.0f", list.get(i).getArea()) + context.getString(R.string.area_unit));
        viewHolder.tvBed.setText(list.get(i).getNumber_bed()+"");
        viewHolder.tvWashingRoom.setText(list.get(i).getNumber_wc()+"");
        Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onCallBack(i);
            }
        });
        viewHolder.checkBox2.setChecked(cachedBoolen.get(i));
        viewHolder.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iCallback.onCallBackToggle(i, b);
                cachedBoolen.put(i, b);
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
