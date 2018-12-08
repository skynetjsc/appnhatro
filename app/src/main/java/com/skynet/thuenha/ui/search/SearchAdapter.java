package com.skynet.thuenha.ui.search;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<Post> list;
    Context context;
    ICallback iCallback;
    SparseBooleanArray sparseBooleanArray;

    public SearchAdapter(List<Post> list, Context context, ICallback iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        sparseBooleanArray = new SparseBooleanArray();
        for (int i = 0; i < this.list.size(); i++) {
            sparseBooleanArray.put(i, list.get(i).getPrice() == 0);
            list.get(i).setColor(list.get(i).getPrice() == 0 ? ContextCompat.getColor(context, R.color.green) : Color.BLACK);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item_post, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.address.setText(list.get(i).getAddress());
        viewHolder.name.setText(list.get(i).getTitle());
        viewHolder.time.setText(list.get(i).getDate());
        viewHolder.textView8.setText(list.get(i).getPrice() == 0 ? "Giá liên hệ" : String.format("%,.0fVNĐ/Tháng", list.get(i).getPrice()));
//        if (list.get(i).getPrice() == 0) {
            viewHolder.textView8.setTextColor(sparseBooleanArray.get(i) ? ContextCompat.getColor(context, R.color.green) : Color.BLACK);
//        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onCallBack(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
