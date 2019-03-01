package com.skynet.mumgo.ui.search;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.Viewholder> implements Filterable {
    List<Category> list;
    List<Category> listFilter;

    Context context;
    ICallback iCallback;

    SparseBooleanArray sparseBooleanArray;

    public AdapterCategory(List<Category> list, Context context, ICallback iCallback) {
        this.list = list;
        this.listFilter = list;
        this.context = context;

        this.iCallback = iCallback;
        this.sparseBooleanArray = new SparseBooleanArray();
        for (int i = 0; i < this.listFilter.size(); i++) {
            sparseBooleanArray.put(i,listFilter.get(i).getIs_hot()==1);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if (listFilter.get(position).getImg() != null && !listFilter.get(position).getImg().isEmpty()) {
            Picasso.with(context).load(listFilter.get(position).getImg()).fit().centerCrop().into(holder.imgShop);
        }
        holder.tvNameShop.setText(listFilter.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
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
                    List<Category> filteredList = new ArrayList<>();
                    for (Category row : list) {

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
                listFilter = (ArrayList<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgShop)
        ImageView imgShop;
        @BindView(R.id.tvNameShop)
        TextView tvNameShop;
        @BindView(R.id.card)
        CardView card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
