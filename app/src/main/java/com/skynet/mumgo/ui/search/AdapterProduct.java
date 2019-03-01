package com.skynet.mumgo.ui.search;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> implements Filterable {
    List<Product> list;
    List<Product> listFilter;
    Context context;
    CallBackProduct iCallback;
    SparseBooleanArray cache;


    public AdapterProduct(List<Product> list, Context context, CallBackProduct iCallback) {
        this.list = list;
        this.listFilter = list;
        this.context = context;
        this.iCallback = iCallback;
        this.cache = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            cache.put(i, listFilter.get(i).getIs_favourite() == 1);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listFilter.get(position).getImg() != null && !listFilter.get(position).getImg().isEmpty()) {
            Picasso.with(context).load(listFilter.get(position).getImg()).fit().centerCrop().into(holder.imgPhoto);
        }
        holder.tvName.setText(listFilter.get(position).getName());
        holder.tvUnit.setText("Giá\n1" + listFilter.get(position).getName_unit());
        holder.tvPrice.setText(String.format("%,.0fđ", listFilter.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });

        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(cache.get(position));
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listFilter.get(position).setIs_favourite(isChecked ? 1 : 2);
                iCallback.toggleFav(position, listFilter.get(position), isChecked);
                cache.put(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFilter = list;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    listFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFilter = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPhoto)
        ImageView imgPhoto;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvUnit)
        TextView tvUnit;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.cb)
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CallBackProduct extends ICallback {
        void toggleFav(int pos, Product product, boolean toggle);
    }
}
