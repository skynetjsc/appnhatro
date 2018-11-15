package com.skynet.thuenha.ui.profile;

import android.os.Bundle;
import android.view.View;
import com.skynet.thuenha.R;

import com.skynet.thuenha.ui.base.BaseFragment;

public class ProfileFragment extends BaseFragment{
    public static ProfileFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initVariables() {

    }
}
