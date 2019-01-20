package com.skynet.mumgo.ui.detailshop;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentIntro extends BaseFragment {
    @BindView(R.id.tvContent)
    TextView tvContent;

    public static FragmentIntro newInstance(String content) {

        Bundle args = new Bundle();
        args.putString(AppConstant.MSG, content);
        FragmentIntro fragment = new FragmentIntro();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_intro_shop;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initVariables() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(getArguments().getString(AppConstant.MSG),Html.FROM_HTML_MODE_COMPACT));
        }else{
            tvContent.setText(Html.fromHtml(getArguments().getString(AppConstant.MSG)));
        }
    }
}
