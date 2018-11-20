package com.skynet.thuenha.ui.feedback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Comment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterComment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Comment> listComment;
    Context context;


    public AdapterComment(List<Comment> listComment, Context context) {
        this.listComment = listComment;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == 1) {
            return new AdminViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_item_admin, viewGroup, false));

        } else {

            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_item_comment, viewGroup, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount())
            return listComment.get(position).getType();
        return listComment.get(listComment.size()-1).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof AdminViewHolder) {
            AdminViewHolder adminViewHolder = (AdminViewHolder) viewHolder;
            adminViewHolder.tvTime.setText(listComment.get(i).getDate());
            adminViewHolder.tvContent.setText(listComment.get(i).getComment());

        } else {
            ViewHolder viewHolderSimple = (ViewHolder) viewHolder;
            viewHolderSimple.tvTime.setText(listComment.get(i).getDate());
            viewHolderSimple.tvContent.setText(listComment.get(i).getComment());
            viewHolderSimple.tvName.setText(listComment.get(i).getName());
            if (listComment.get(i).getAvatar() != null && !listComment.get(i).getAvatar().isEmpty()) {
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(5)
                        .oval(false)
                        .build();
                Picasso.with(context).load(listComment.get(i).getAvatar()).fit().centerCrop().transform(transformation).into(viewHolderSimple.imgAvt);
            }
        }


    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvt)
        RoundedImageView imgAvt;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AdminViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
