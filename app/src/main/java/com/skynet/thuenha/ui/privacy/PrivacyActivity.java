package com.skynet.thuenha.ui.privacy;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PrivacyActivity extends BaseActivity {
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;

    @Override
    protected int initLayout() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        tvTitleToolbar.setText(R.string.privacy_title);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.imgBtn_back_toolbar)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
