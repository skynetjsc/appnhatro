package com.skynet.thuenha.ui.search;

import android.os.Bundle;
import android.view.View;

import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseFragment;

public class FragmentSearch extends BaseFragment {
    public static FragmentSearch newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("idService", id);
        FragmentSearch fragment = new FragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initVariables() {

    }
}
