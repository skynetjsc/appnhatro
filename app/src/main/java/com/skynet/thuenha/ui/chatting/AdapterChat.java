package com.skynet.thuenha.ui.chatting;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Message;
import com.skynet.thuenha.models.Post;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Huy on 8/31/2017.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolerMessage> {
    List<Message> mMessages;
    Context context;
    ICallback onResponse;
    String ava;
    int layoutId;

    private Post post;


    public AdapterChat(List<Message> list, Context applicationContext, String urll, Post post, ICallback onResponse) {
        this.mMessages = list;
        this.ava = urll;
        this.post = post;
        this.onResponse = onResponse;
        this.context = applicationContext;
    }

    @Override
    public ViewHolerMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutId = viewType == AppController.getInstance().getmProfileUser().getType() ? R.layout.row_my_chat : R.layout.row_partner_chat;
        return new ViewHolerMessage(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolerMessage holder, final int position) {
        Message message = mMessages.get(position);
        if (holder != null) {
            holder.mContent.setText(message.getContent());
            holder.mTime.setText(mMessages.get(position).getTime().split(" ")[1].substring(0, mMessages.get(position).getTime().split(" ")[1].lastIndexOf(':')));
//            if(position == getItemCount()-1){
//                holder.mTime.setVisibility(View.VISIBLE);
//            }else{
//                holder.mTime.setVisibility(View.INVISIBLE);
//
//            }
            if (message.getAttach() != 0 && post != null) {
                holder.layoutPost.setVisibility(View.VISIBLE);
                holder.tvAddressPost.setText(post.getAddress());
                holder.tvNamePost.setText(post.getTitle());
                holder.tvPricePost.setText(String.format("%,.0fđ/tháng", post.getPrice()));
                if (post.getAvatar() != null
                        && !post.getAvatar().isEmpty()) {
                    Transformation transformation = new RoundedTransformationBuilder()
                            .cornerRadiusDp(5)
                            .oval(false)
                            .build();
                    Picasso.with(context).load(post.getAvatar()).fit().centerCrop().transform(transformation).into(holder.roundedImageView);
                }
                holder.layoutPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onResponse.onCallBack(position);
                    }
                });
            } else {
                holder.layoutPost.setVisibility(View.GONE);
            }
            if (getItemViewType(position) == AppController.getInstance().getmProfileUser().getType()) {
                if (AppController.getInstance().getmProfileUser().getAvatar() != null
                        && !AppController.getInstance().getmProfileUser().getAvatar().isEmpty()) {
                    Picasso.with(context).load(AppController.getInstance().getmProfileUser().getAvatar()).fit().centerCrop().into(holder.mAvatar);
                }
            } else {
                if (ava != null
                        && !ava.isEmpty()) {
                    Picasso.with(context).load(ava).fit().centerCrop().into(holder.mAvatar);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    public class ViewHolerMessage extends RecyclerView.ViewHolder {
        TextView mContent;
        TextView mTime;
        ImageView mAvatar;
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tvNamePost)
        TextView tvNamePost;
        @BindView(R.id.tvAddressPost)
        TextView tvAddressPost;
        @BindView(R.id.tvPricePost)
        TextView tvPricePost;
        @BindView(R.id.layoutPost)
        ConstraintLayout layoutPost;

        public ViewHolerMessage(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContent = (TextView) itemView.findViewById(R.id.content_txt);
            mTime = (TextView) itemView.findViewById(R.id.time_tt);
            mAvatar = (CircleImageView) itemView.findViewById(R.id.avt);
        }
    }
}
