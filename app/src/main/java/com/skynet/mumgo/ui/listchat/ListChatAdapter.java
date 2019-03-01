package com.skynet.mumgo.ui.listchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.ChatItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListChatAdapter extends RecyclerView.Adapter<ListChatAdapter.ViewHolder> {
    List<ChatItem> list;
    Context context;
    ChatCallBack iCallback;



    public ListChatAdapter(List<ChatItem> list, Context context, ChatCallBack iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        for (ChatItem c : list) {
            if (AppController.getInstance().getmProfileUser().getType() == 1) {
                c.setName(c.getShop().getName());
                c.setAvt(c.getShop().getAvatar());
            } else {
                c.setName(c.getUse().getName());
                c.setAvt(c.getUse().getAvatar());
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_chat, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvContent.setText(list.get(i).getLastMessage());
        viewHolder.tvTime.setText(list.get(i).getTime());
        if(list.get(i).getAvt()!=null && !list.get(i).getAvt().isEmpty())
        Picasso.with(context).load(list.get(i).getAvt()).fit().centerCrop().into(viewHolder.circleImageView2);
        viewHolder.layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onClickItemChat(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circleImageView2)
        CircleImageView circleImageView2;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.layoutContent)
        LinearLayout layoutContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ChatCallBack {
        void onClickItemChat(ChatItem chatItem);

        void onClickConfirm(ChatItem chatItem);

        void onDeleteChat(ChatItem chatItem);
    }
}
