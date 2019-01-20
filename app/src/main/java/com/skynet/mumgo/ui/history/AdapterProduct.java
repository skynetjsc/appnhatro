package com.skynet.mumgo.ui.history;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.History;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    List<History> list;
    Context context;
    ICallback iCallback;
    SparseBooleanArray cacheAlreadyBook;
    SparseBooleanArray cacheWaiting;
    SparseBooleanArray cacheDone;
    SparseBooleanArray cacheCancle;
    public AdapterProduct(List<History> list, Context context, ICallback iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        this.cacheAlreadyBook = new SparseBooleanArray();
        this.cacheWaiting = new SparseBooleanArray();
        this.cacheDone = new SparseBooleanArray();
        this.cacheCancle = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getActive()){
                case 1:{
                    list.get(i).setActiveString("Đã đặt");
                    cacheAlreadyBook.put(i,true);
                    break;
                } case 2:{
                    list.get(i).setActiveString("Đang chờ giao");
                    cacheWaiting.put(i,true);

                    break;
                }case 3:{
                    list.get(i).setActiveString("Đã giao");
                    cacheDone.put(i,true);
                    break;
                }case 4:{
                    list.get(i).setActiveString("Đã huỷ");
                    cacheCancle.put(i,true);
                    break;
                }case 5:{
                    list.get(i).setActiveString("Đã nhận");
                    cacheDone.put(i,true);
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getAvatar() != null && !list.get(position).getAvatar().isEmpty()) {
            Picasso.with(context).load(list.get(position).getAvatar()).into(holder.img);
        }
        holder.tvName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });
        holder.tvDate.setText(list.get(position).getDate_booking());
        holder.tvName.setText(list.get(position).getName());
        holder.tvContent.setText(list.get(position).getContent());
        holder.tvTotalPrice.setText(String.format("Tổng bill: %,.0fđ",list.get(position).getPrice()));
        holder.tvStatus.setText(list.get(position).getActiveString());
        if(cacheAlreadyBook.get(position)){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.blue));
        }else if(cacheWaiting.get(position)){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.orage));
        }else if(cacheDone.get(position)){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.green));
        }else if(cacheCancle.get(position)){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.red));
        }else{
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.blue));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearCache() {
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getActive()){
                case 1:{
                    list.get(i).setActiveString("Đã đặt");
                    cacheAlreadyBook.put(i,true);
                    break;
                } case 2:{
                    list.get(i).setActiveString("Đang chờ giao");
                    cacheWaiting.put(i,true);

                    break;
                }case 3:{
                    list.get(i).setActiveString("Đã giao");
                    cacheDone.put(i,true);
                    break;
                }case 4:{
                    list.get(i).setActiveString("Đã huỷ");
                    cacheCancle.put(i,true);
                    break;
                }case 5:{
                    list.get(i).setActiveString("Đã nhận");
                    cacheDone.put(i,true);
                    break;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvTotalPrice)
        TextView tvTotalPrice;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvDate)
        TextView tvDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
