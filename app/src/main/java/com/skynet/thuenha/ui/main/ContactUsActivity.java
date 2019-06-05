package com.skynet.thuenha.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initVariables() {
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

    }


    @OnClick(R.id.imageView13)
    public void onViewClicked() {
        onBackPressed();
    }


    @OnClick(R.id.imageView14)
    public void onViewsClicked() {
        String url = "http://online.gov.vn/HomePage/AppDisplay.aspx?DocId=617";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}