package com.skynet.mumgo.ui.category.listProduct;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNearbyShop extends RecyclerView.Adapter<AdapterNearbyShop.Viewholder> {
    List<Shop> list;
    Context context;
    ICallBackListShop iCallback;

    SparseBooleanArray sparseBooleanArray;

    public AdapterNearbyShop(List<Shop> list, Context context, ICallBackListShop iCallback) {
        this.list = list;
        this.context = context;

        this.iCallback = iCallback;
        this.sparseBooleanArray = new SparseBooleanArray();
//        for (int i = 0; i < this.list.size(); i++) {
//            sparseBooleanArray.put(i,list.get(i).getIs_hot()==1);
//
//
//        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if (list.get(position).getAvatar() != null && !list.get(position).getAvatar().isEmpty()) {
            Picasso.with(context).load(list.get(position).getAvatar()).into(holder.imgShop);
        }
        holder.tvNameShop.setText(list.get(position).getName());
        holder.tvNumberProduct.setText(list.get(position).getNumber_product() + " sản phẩm");
//        if (sparseBooleanArray.get(position)) {
            holder.card.setElevation(4);
//        }else{
//            holder.card.setElevation(0);
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onClickShop(position,list.get(position));
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
    public interface ICallBackListShop{
        void onClickShop(int pos, Shop shop);
    }
}
