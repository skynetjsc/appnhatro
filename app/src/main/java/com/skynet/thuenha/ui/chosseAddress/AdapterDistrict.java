package com.skynet.thuenha.ui.chosseAddress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDistrict extends RecyclerView.Adapter<AdapterDistrict.ViewHolder> {

    List<Address> listDistrict;
    Context context;
    ICallback iCallback;

    public AdapterDistrict(List<Address> listDistrict, Context context, ICallback iCallback) {
        this.listDistrict = listDistrict;
        this.context = context;
        this.iCallback = iCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_district, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(listDistrict.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDistrict.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView4)
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
