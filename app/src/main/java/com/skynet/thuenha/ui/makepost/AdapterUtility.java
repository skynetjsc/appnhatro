package com.skynet.thuenha.ui.makepost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterUtility extends RecyclerView.Adapter<AdapterUtility.ViewHolder> {

    List<Utility> list;
    Context context;
    SparseBooleanArray cache;
    int oldPosition = -1;
    ViewHolder oldHolder;

    public AdapterUtility(List<Utility> list, Context context) {
        this.list = list;
        this.context = context;
        cache = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, list.get(i).isChecked());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.make_post_item_utility, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (cache.get(i) ) {
            viewHolder.layoutRoot.setBackgroundResource(R.drawable.detail_post_bg_stock_orange_btn);
        } else {
            viewHolder.layoutRoot.setBackgroundResource(R.drawable.make_post_bg_stock_gray_btn);
        }
        viewHolder.tvName.setText(list.get(i).getName());
        if (list.get(i).getImg() != null && !list.get(i).getImg().isEmpty()) {
            Picasso.with(context).load(list.get(i).getImg()).fit().centerInside().into(viewHolder.img);
        }
        viewHolder.tvNumber.setText("+" + list.get(i).getNumber());
        if (list.get(i).getNumber() >= 1) {
            viewHolder.tvNumber.setVisibility(View.VISIBLE);

        } else {
            viewHolder.tvNumber.setVisibility(View.INVISIBLE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setNumber(list.get(i).getNumber() + 1);
                list.get(i).setChecked(true);
                cache.put(i, true);
                if (list.get(i).getNumber() > 4) {
                    list.get(i).setNumber(0);
                    list.get(i).setChecked(false);
                    cache.put(i, false);

                }
                notifyItemChanged(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb)
        TextView tvName;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.layoutRoot)
        ConstraintLayout layoutRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
