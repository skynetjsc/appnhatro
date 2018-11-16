package com.skynet.thuenha.ui.detailPost.viewProfile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPostProfileViewer extends RecyclerView.Adapter<AdapterPostProfileViewer.ViewHolder> {
    List<Post> list;
    Context context;
    ICallbackTwoM iCallback;


    public AdapterPostProfileViewer(List<Post> list, Context context, ICallbackTwoM iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_viewer_post_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvPrice.setText(String.format("%,.0fđ/tháng", list.get(i).getPrice()));
        viewHolder.tvName1.setText(list.get(i).getTitle());
        viewHolder.tvAddress.setText(list.get(i).getAddress());
        viewHolder.tvArea.setText(String.format("%,.0f", list.get(i).getArea()) + context.getString(R.string.area_unit));
        Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().into(viewHolder.img);
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
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvBed)
        TextView tvBed;
        @BindView(R.id.tvWashingRoom)
        TextView tvWashingRoom;
        @BindView(R.id.tvArea)
        TextView tvArea;
        @BindView(R.id.tvName1)
        TextView tvName1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
