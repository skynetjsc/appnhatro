package com.skynet.mumgo.ui.detailshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Rate;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterFeedback extends RecyclerView.Adapter<AdapterFeedback.ViewHolder> {
    List<Rate> list;
    Context context;


    public AdapterFeedback(List<Rate> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getAvatar() != null && !list.get(position).getAvatar().isEmpty()) {
            Picasso.with(context).load(list.get(position).getAvatar()).fit().centerCrop().into(holder.circleImageView);
        }
        holder.tvName.setText(list.get(position).getName());
        holder.tvDate.setText(list.get(position).getDate());
        holder.tvContent.setText(list.get(position).getContent());
        holder.rating.setRating(list.get(position).getStar());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circleImageView)
        CircleImageView circleImageView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.rating)
        RatingBar rating;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
