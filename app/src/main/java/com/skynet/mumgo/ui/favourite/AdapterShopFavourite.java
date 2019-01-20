package com.skynet.mumgo.ui.favourite;

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
import com.skynet.mumgo.models.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterShopFavourite extends RecyclerView.Adapter<AdapterShopFavourite.ViewHolder> {
    List<Shop> list;
    Context context;
    ICallbackTwoM iCallback;
    SparseBooleanArray cachedBoolen;


    public AdapterShopFavourite(List<Shop> list, Context context, ICallbackTwoM iCallback) {
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourite_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvContent.setText(list.get(i).getIntro());
        viewHolder.tvStar.setText(list.get(i).getStar() + "");
        Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onCallBack(i);
            }
        });
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
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.imageView19)
        ImageView imageView19;
        @BindView(R.id.tvStar)
        TextView tvStar;
        @BindView(R.id.cb)
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
