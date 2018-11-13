package com.skynet.thuenha.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentHomeCallBack,ChooseAddressFragment.CallBackChooseAddress {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
//        showDialogExpired();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.setTextVisibility(false);
        bnve.enableItemShiftingMode(false);
        viewpager.setAdapter(new AdapterViewpager(getSupportFragmentManager()));
        bnve.setupWithViewPager(viewpager);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onChooseAddress() {
        HomeFragment page = (HomeFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewpager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (viewpager.getCurrentItem() == 0 && page != null) {
            ((HomeFragment)page).setupAddress();
        }
    }
}
