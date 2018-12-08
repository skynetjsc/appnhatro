package com.skynet.thuenha.ui.choosewhorent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterViewer extends RecyclerView.Adapter<AdapterViewer.ViewHolder> {
    List<Profile> list;
    Context context;
    ICallbackTwoM iCallback;

    public AdapterViewer(List<Profile> list, Context context, ICallbackTwoM iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_viewer_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvAddress.setText(list.get(i).getAddress());
        viewHolder.tvEmail.setText(list.get(i).getPhone());
        if (list.get(i).getAvatar() != null && !list.get(i).getAvatar().isEmpty())
            Picasso.with(context).load(list.get(i).getAvatar()).fit().centerCrop().into(viewHolder.imgAvt);
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
        @BindView(R.id.imageView10)
        ImageView imageView10;
        @BindView(R.id.imgAvt)
        CircleImageView imgAvt;
        @BindView(R.id.imgAward)
        ImageView imgAward;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvEmail)
        TextView tvEmail;
        @BindView(R.id.layoutProfile)
        ConstraintLayout layoutProfile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
