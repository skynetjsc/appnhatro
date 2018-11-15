package com.skynet.thuenha.ui.favourite;

import android.os.Bundle;
import android.view.View;

import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.R;

public class FavouriteFragment extends BaseFragment {
    public static FavouriteFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.fragment_favourite;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initVariables() {

    }
}
