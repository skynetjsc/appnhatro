package com.skynet.thuenha.ui.splash;

import android.content.Intent;
import android.view.View;


import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.login.LoginActivity;
import com.skynet.thuenha.ui.signup.FragmentSignUp;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initVariables() {

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
    @OnClick({R.id.btnLogin, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin: {
                startActivityForResult(new Intent(AuthActivity.this, LoginActivity.class),1000);
                break;
            }
            case R.id.button2: {
                startActivityForResult(new Intent(AuthActivity.this, FragmentSignUp.class),1000);
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
}
