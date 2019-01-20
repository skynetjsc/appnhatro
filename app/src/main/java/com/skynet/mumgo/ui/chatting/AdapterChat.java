package com.skynet.mumgo.ui.chatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Message;
import com.skynet.mumgo.models.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
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
            if (!mMessages.get(position).getTime().isEmpty())
                holder.mTime.setText(mMessages.get(position).getTime().split(" ")[1].substring(0, mMessages.get(position).getTime().split(" ")[1].lastIndexOf(':')));
//            if(position == getItemCount()-1){
//                holder.mTime.setVisibility(View.VISIBLE);
//            }else{
//                holder.mTime.setVisibility(View.INVISIBLE);
//
//            }
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


        public ViewHolerMessage(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContent = (TextView) itemView.findViewById(R.id.content_txt);
            mTime = (TextView) itemView.findViewById(R.id.time_tt);
            mAvatar = (CircleImageView) itemView.findViewById(R.id.avt);
        }
    }
}
