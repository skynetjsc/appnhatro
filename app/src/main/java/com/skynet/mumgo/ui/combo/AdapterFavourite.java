package com.skynet.mumgo.ui.combo;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallbackTwoM;
import com.skynet.mumgo.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.ViewHolder> {
    List<Product> list;
    Context context;
    ICallbackTwoM iCallback;
    SparseBooleanArray cachedBoolen;

    public AdapterFavourite(List<Product> list, Context context, ICallbackTwoM iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        cachedBoolen = new SparseBooleanArray();
        for (int i = 0; i < this.list.size(); i++) {
            list.get(i).setIs_favourite(1);
            cachedBoolen.put(i, true);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_shop, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        Picasso.with(context).load(list.get(i).getImg()).fit().centerCrop().into(viewHolder.imgPhoto);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onCallBack(i);
            }
        });
        viewHolder.tvUnit.setText("Giá\n1" + list.get(i).getName_unit());
        viewHolder.tvPrice.setText(String.format("%,.0fđ", list.get(i).getPrice()));
        viewHolder.cb.setChecked(cachedBoolen.get(i));
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
