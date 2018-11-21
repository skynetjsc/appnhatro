package com.skynet.thuenha.ui.makepost;

import android.content.Context;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Service;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterService extends RecyclerView.Adapter<AdapterService.ViewHolder> {

    List<Service> list;
    Context context;
    SparseBooleanArray cache;
    int oldPosition = -1;
    ViewHolder oldHolder;
    ICallback iCallback;

    public AdapterService(List<Service> list, Context context, ICallback iCallback) {
        this.list = list;
        this.context = context;
        cache = new SparseBooleanArray();
        this.iCallback = iCallback;
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, list.get(i).isChecked());
        }
    }


    public void updateAdapter() {
        cache.clear();
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, list.get(i).isChecked());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.make_post_item_service, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.cb.setOnCheckedChangeListener(null);
        viewHolder.cb.setChecked(cache.get(i));
        if (cache.get(i)) {
            oldHolder = viewHolder;
        }
        viewHolder.cb.setText(list.get(i).getName());
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setChecked(isChecked);
                cache.put(i, isChecked);
                if (isChecked) {
//                    if (oldPosition != -1) {
//                        list.get(oldPosition).setChecked(false);
//                    }
                    if (oldHolder != null) {
                        oldHolder.cb.setChecked(false);
                    }
                    oldHolder = viewHolder;
                    oldPosition = i;
                    if (iCallback!=null)
                        iCallback.onCallBack(i);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
