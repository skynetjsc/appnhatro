package com.skynet.mumgo.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCartItem extends RecyclerSwipeAdapter<AdapterCartItem.ViewHolder> {
    List<Product> list;
    Context context;
    CallBackCart callBackCart;

    public AdapterCartItem(List<Product> list, Context context, CallBackCart callBackCart) {
        this.list = list;
        this.context = context;
        this.callBackCart = callBackCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false));
    }

    public void remove(int position) {
        list.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mItemManger.bindView(holder.itemView, position);
        if(list.get(position).getImg()!=null && !list.get(position).getImg().isEmpty()){
            Picasso.with(context).load(list.get(position).getImg()).fit().centerCrop().into(holder.circleImageView);
        }
        Product product = list.get(position);
        holder.tvName.setText(product.getName());
        holder.textView33.setText(String.format("%,.0fđ",product.getPrice()));
        holder.textView31.setText("Ghi chú: "+product.getNote());
        holder.textView34.setText("("+product.getShopName()+")");
        holder.textView35.setText("Số lượng: "+product.getQuatity());
        holder.layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackCart.onClickDetail(position, list.get(position));
                mItemManger.closeItem(position);
            }
        });
        holder.tvOptionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackCart.onClickEdit(position, list.get(position));
                mItemManger.closeItem(position);
            }
        });
        holder.imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackCart.onClickDelete(position, list.get(position));
                mItemManger.closeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvOptionLeft)
        FrameLayout tvOptionLeft;
        @BindView(R.id.circleImageView)
        CircleImageView circleImageView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.textView31)
        TextView textView31;
        @BindView(R.id.textView34)
        TextView textView34;
        @BindView(R.id.textView35)
        TextView textView35;
        @BindView(R.id.textView33)
        TextView textView33;
        @BindView(R.id.imageView14)
        ImageView imageView14;
        @BindView(R.id.layoutContent)
        LinearLayout layoutContent;
        @BindView(R.id.swipe)
        SwipeLayout swipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CallBackCart {
        void onClickDelete(int pos, Product product);

        void onClickEdit(int pos, Product product);

        void onClickDetail(int pos, Product product);
    }
}
