package com.skynet.thuenha.ui.chosseAddress;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Address;

import java.util.List;

public class AdapterCity extends ArrayAdapter<Address> {
    List<Address> list;
    Context context;

    /*************  CustomAdapter Constructor *****************/
    public AdapterCity(
            Context activitySpinner,
            int textViewResourceId,
            List<Address> objects
    ) {
        super(activitySpinner, textViewResourceId, objects);
        this.list = objects;
        this.context = activitySpinner;


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        TextView textView = row.findViewById(R.id.textView4);
        textView.setText(list.get(position).getName());
        return row;
    }
}
