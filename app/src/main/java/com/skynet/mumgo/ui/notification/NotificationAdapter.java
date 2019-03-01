package com.skynet.mumgo.ui.Notification;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Notification;
import com.skynet.mumgo.models.Promotion;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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
            sparseBooleanArray.put(i, list.get(i).getIsRead() == 1);
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
        holder.time.setText(list.get(position).getTime());
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

        @BindView(R.id.layoutRootNotia)
        androidx.constraintlayout.widget.ConstraintLayout layoutRootNotia;


        public CategoryViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
