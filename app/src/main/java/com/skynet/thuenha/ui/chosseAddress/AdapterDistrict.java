package com.skynet.thuenha.ui.chosseAddress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.interfaces.ICallbackObj;
import com.skynet.thuenha.models.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDistrict extends RecyclerView.Adapter<AdapterDistrict.ViewHolder>  implements Filterable {
    private List<Address> listDistrictFiltered;
    List<Address> listDistrict;
    Context context;
    ICallbackObj iCallback;

    public AdapterDistrict(List<Address> listDistrict, Context context, ICallbackObj iCallback) {
        this.listDistrict = listDistrict;
        this.listDistrictFiltered = listDistrict;
        this.context = context;
        this.iCallback = iCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_district, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(listDistrictFiltered.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(listDistrictFiltered.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDistrictFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listDistrictFiltered = listDistrict;
                } else {
                    List<Address> filteredList = new ArrayList<>();
                    for (Address row : listDistrict) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    listDistrictFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listDistrictFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listDistrictFiltered = (ArrayList<Address>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView4)
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
