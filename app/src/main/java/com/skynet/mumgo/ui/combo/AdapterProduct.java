package com.skynet.mumgo.ui.combo;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    List<Product> list;
    Context context;
    CallBackProduct iCallback;
    SparseBooleanArray cache;


    public AdapterProduct(List<Product> list, Context context, CallBackProduct iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        this.cache = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, list.get(i).getIs_favourite() == 1);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getImg() != null && !list.get(position).getImg().isEmpty()) {
            Picasso.with(context).load(list.get(position).getImg()).into(holder.imgPhoto);
        }
        holder.tvName.setText(list.get(position).getName());
        holder.tvUnit.setText("Giá\n1" + list.get(position).getName_unit());
        holder.tvPrice.setText(String.format("%,.0fđ", list.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });

        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(cache.get(position));
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setIs_favourite(isChecked ? 1 : 2);
                iCallback.toggleFav(position, list.get(position), isChecked);
                cache.put(position, isChecked);
            }
        });
        holder.tvRating.setText(list.get(position).getStar() + "");
        holder.ratingBar2.setRating(list.get(position).getNumber_rating());
        holder.tvAddress.setText(list.get(position).getShopName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPhoto)
        ImageView imgPhoto;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvUnit)
        TextView tvUnit;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.ratingBar2)
        RatingBar ratingBar2;
        @BindView(R.id.tvRating)
        TextView tvRating;
        @BindView(R.id.tvAddress)
        TextView tvAddress;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CallBackProduct extends ICallback {
        void toggleFav(int pos, Product product, boolean toggle);
    }
}
