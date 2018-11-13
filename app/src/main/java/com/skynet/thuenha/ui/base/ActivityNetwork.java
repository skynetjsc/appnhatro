package com.skynet.thuenha.ui.base;


import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityNetwork extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.dialog_lost_connect;
    }

    @Override
    protected void initVariables() {
        AppController.getInstance().setToogleInternet(true);
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    @OnClick(R.id.imgBack)
    public void onclickBack() {
        onBackPressed();
    }

    @Override
    public void onNetworkTurnOn() {
        super.onNetworkTurnOn();
        onBackPressed();
    }

    @Override
    public String getTag(){
        super.getTag();
        return "ActivityNetwork";
    }
    @Override
    protected int initViewSBAnchor() {
        return 0;
    }
}
