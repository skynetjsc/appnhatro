package com.skynet.thuenha.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.home.HomeFragment;
import com.skynet.thuenha.ui.views.ViewpagerNotSwipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentHomeCallBack, ChooseAddressFragment.CallBackChooseAddress {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    private AdapterViewpager adapter;
    private boolean doubleBackToExitPressedOnce;

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
        adapter = new AdapterViewpager(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        bnve.setupWithViewPager(viewpager);
        viewpager.setPagingEnabled(false);
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
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn BACK 2 lần để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onChooseAddress() {
//        HomeFragment page = (HomeFragment) getSupportFragmentManager().findFragmentById(getSupportFragmentManager().getFragments().get(0).getId());
//        // based on the current position you can then cast the page to the correct
//        // class and call the method:
//
//        if (viewpager.getCurrentItem() == 0 && page != null) {
//            ((HomeFragment) page).setupAddress();
//        }
        getSupportFragmentManager().popBackStack();
//        HomeFragment page = (HomeFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewpager.getCurrentItem());
//        // based on the current position you can then cast the page to the correct
//        // class and call the method:
//        if (viewpager.getCurrentItem() == 0 && page != null) {
//            ((HomeFragment)page).setupAddress();
//        }
        adapter.getRegisteredFragment(0).onResume();
//        adapter.getItem(viewpager.getCurrentItem()).onResume();
    }
}
