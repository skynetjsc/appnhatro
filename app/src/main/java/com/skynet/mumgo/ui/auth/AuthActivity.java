package com.skynet.mumgo.ui.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.auth.login.LoginActivity;
import com.skynet.mumgo.ui.auth.signup.ActivitySignUp;
import com.skynet.mumgo.ui.base.BaseActivity;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    AdapterAuthViewpager adapterAuthViewpager;

    @Override
    protected int initLayout() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initVariables() {

        adapterAuthViewpager = new AdapterAuthViewpager(getSupportFragmentManager());
        viewpager.setAdapter(adapterAuthViewpager);
    }

    @Override
    public void onSocketConnected() {
        super.onSocketConnected();
//        if(getmSocket()!=null){
//            getmSocket().onStopAudio();
//            AppController.getInstance().getmSetting().put("music", false);
//        }
    }

    //
    @OnClick({R.id.tvLogin, R.id.btnSignUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogin: {
                startActivityForResult(new Intent(AuthActivity.this, LoginActivity.class), 1000);
                break;
            }
            case R.id.btnSignUp: {
                startActivityForResult(new Intent(AuthActivity.this, ActivitySignUp.class), 1000);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            finish();
        }
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
