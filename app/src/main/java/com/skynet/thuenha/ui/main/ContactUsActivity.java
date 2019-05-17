package com.skynet.thuenha.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.utils.AppConstant;
import com.skynet.thuenha.utils.CommomUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

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


}