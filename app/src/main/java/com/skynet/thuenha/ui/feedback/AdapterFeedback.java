package com.skynet.thuenha.ui.feedback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Feedback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFeedback extends RecyclerView.Adapter<AdapterFeedback.ViewHolder> {
    List<Feedback> list;
    Context context;
    feedbackCallback iCallback;
    SparseBooleanArray cache;

    public AdapterFeedback(List<Feedback> list, Context context, feedbackCallback iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        cache = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, list.get(i).getIs_like() == 1);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (list.get(i).getAvatar() != null && !list.get(i).getAvatar().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(5)
                    .oval(false)
                    .build();
            Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().transform(transformation).into(viewHolder.imgAvt);
        }
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvTime.setText(list.get(i).getDate());
        viewHolder.tvContent.setText(list.get(i).getContent());
        viewHolder.tvLike.setText(list.get(i).getLike_feedback() + "");
        viewHolder.cbLike.setChecked(cache.get(i));
        viewHolder.tvComment.setText(list.get(i).getListComment().size() + " Bình luận");

        viewHolder.tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.cbLike.toggle();
            }
        });
        viewHolder.cbLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.put(i, isChecked);
                iCallback.clickLike(list.get(i), isChecked);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
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
        @BindView(R.id.cbLike)
        CheckBox cbLike;
        @BindView(R.id.tvComment)
        TextView tvComment;
        @BindView(R.id.rcvComment)
        RecyclerView rcvComment;
        @BindView(R.id.expandLayout)
        ExpandableLayout expandLayout;
        @BindView(R.id.tvRep)
        TextView tvRep;
        @BindView(R.id.tvLike)
        TextView tvLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface feedbackCallback {
        void clickLike(Feedback fb, boolean isChecked);

        void clickComment(Feedback fb);

        void clickRep(Feedback fb);

    }
}
