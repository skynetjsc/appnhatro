package com.skynet.mumgo.ui.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentIntro extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;

    public static FragmentIntro newInstance(String title, String content) {
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("content",content);

        FragmentIntro fragment = new FragmentIntro();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_intro;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initVariables() {
        tvTitle.setText(getArguments().getString("title"));
        tvContent.setText(getArguments().getString("content"));
    }
}
