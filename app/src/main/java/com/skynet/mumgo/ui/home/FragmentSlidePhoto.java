package com.skynet.mumgo.ui.home;

import android.os.Bundle;
import android.view.View;

import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseFragment;

public class FragmentSlidePhoto extends BaseFragment {
    public static FragmentSlidePhoto newInstance(String url) {

        Bundle args = new Bundle();
        FragmentSlidePhoto fragment = new FragmentSlidePhoto();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_slide_photo;

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initVariables() {

    }
}
