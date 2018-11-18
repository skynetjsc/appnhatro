package com.skynet.thuenha.ui.notification;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Notification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.skynet.thuenha.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.CategoryViewHoder> {

    List<Notification> list;
    Context context;
    SparseBooleanArray sparseBooleanArray;
    ICallback iCallback;

    public NotificationAdapter(List<Notification> listCategories, Context context, ICallback iCallback) {
        this.list = listCategories;
        this.context = context;
        sparseBooleanArray = new SparseBooleanArray();
        for (int i = 0; i < this.list.size(); i++) {
            sparseBooleanArray.append(i, list.get(i).isRead() == 1);
        }
        this.iCallback = iCallback;
    }

    @NonNull
    @Override
    public CategoryViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHoder holder, final int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.address.setText(list.get(position).getName());
        holder.textView8.setText(list.get(position).getTime());
        if (sparseBooleanArray.get(position)) {
            holder.layoutRootNotia.setBackgroundResource(R.drawable.noti_bg_read);
        } else {
            holder.layoutRootNotia.setBackgroundResource(R.drawable.search_item_bg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
                sparseBooleanArray.put(position, true);
                list.get(position).setIsRead(1);
                notifyItemChanged(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.textView8)
        TextView textView8;
        @BindView(R.id.imageView5)
        ImageView imageView5;
        @BindView(R.id.layoutRootNotia)
        ConstraintLayout layoutRootNotia;


        public CategoryViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
