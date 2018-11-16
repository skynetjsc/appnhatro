package com.skynet.thuenha.ui.chatting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Message;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;


/**
 * Created by Huy on 8/31/2017.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolerMessage> {
    List<Message> mMessages;
    Context context;
    ICallback onResponse;
    String ava_user;
    String ava_driver;
    int layoutId;

    public AdapterChat(List<Message> list, Context context, ICallback onResponse) {
        this.mMessages = list;
        this.context = context;
        this.onResponse = onResponse;
    }

    public AdapterChat(List<Message> list, Context applicationContext) {
        this.mMessages = list;
        this.context = applicationContext;
    }

    @Override
    public ViewHolerMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutId = viewType == AppConstant.TYPE_USER ? R.layout.row_my_chat : R.layout.row_partner_chat;
        return new AdapterChat.ViewHolerMessage(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolerMessage holder, int position) {
        Message message = mMessages.get(position);
        if (holder != null) {
            holder.mContent.setText(message.getContent());
            holder.mTime.setText(mMessages.get(position).getTime().split(" ")[1].substring(0,mMessages.get(position).getTime().split(" ")[1].lastIndexOf(':')));
//            if(position == getItemCount()-1){
//                holder.mTime.setVisibility(View.VISIBLE);
//            }else{
//                holder.mTime.setVisibility(View.INVISIBLE);
//
//            }
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType() ;
    }

    public class ViewHolerMessage extends RecyclerView.ViewHolder {
        TextView mContent;
        TextView mTime;
        ImageView mAvatar;

        public ViewHolerMessage(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.content_txt);
            mTime = (TextView) itemView.findViewById(R.id.time_tt);
        }
    }
}
