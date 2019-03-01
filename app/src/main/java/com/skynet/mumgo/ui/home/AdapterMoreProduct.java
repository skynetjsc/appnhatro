package com.skynet.mumgo.ui.home;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMoreProduct extends RecyclerView.Adapter<AdapterMoreProduct.Viewholder> {
    List<Product> list;
    Context context;
    ICallBackListProduct iCallback;

    SparseBooleanArray sparseBooleanArray;

    public AdapterMoreProduct(List<Product> list, Context context, ICallBackListProduct iCallback) {
        this.list = list;
        this.context = context;

        this.iCallback = iCallback;
        this.sparseBooleanArray = new SparseBooleanArray();

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if (list.get(position).getImg() != null && !list.get(position).getImg().isEmpty()) {
            Picasso.with(context).load(list.get(position).getImg()).into(holder.imgShop);
        }
        holder.tvNameShop.setText(list.get(position).getName());
        holder.tvNumberProduct.setText(String.format("%,.0fđ", list.get(position).getPrice()));
        holder.card.setElevation(0);
        holder.tvNumberProduct.setTextColor(ContextCompat.getColor(context, R.color.green));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onClickProduct(position, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgShop)
        ImageView imgShop;
        @BindView(R.id.tvNumberProduct)
        TextView tvNumberProduct;
        @BindView(R.id.tvNameShop)
        TextView tvNameShop;
        @BindView(R.id.card)
        CardView card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ICallBackListProduct {
        void onClickProduct(int pos, Product shop);
    }
}
