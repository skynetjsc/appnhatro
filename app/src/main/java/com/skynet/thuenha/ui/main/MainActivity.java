package com.skynet.thuenha.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.network.socket.SocketResponse;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.detailPost.viewProfile.ProfileViewerFragment;
import com.skynet.thuenha.ui.home.HomeFragment;
import com.skynet.thuenha.ui.notification.NotificationActivity;
import com.skynet.thuenha.ui.updateProfile.ProfileUpdateFragment;
import com.skynet.thuenha.ui.views.ViewpagerNotSwipe;
import com.skynet.thuenha.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentHomeCallBack, ChooseAddressFragment.CallBackChooseAddress, ICallback {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    private AdapterViewpager adapter;
    private boolean doubleBackToExitPressedOnce;
    private Badge badge;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
//        showDialogExpired();
        if (getIntent() != null && getIntent().getStringExtra(AppConstant.NOTIFICATION_SOCKET) != null) {
            String json = getIntent().getStringExtra(AppConstant.NOTIFICATION_SOCKET);
            if (json != null) {
                SocketResponse response = new Gson().fromJson(json, SocketResponse.class);
                if (response != null) {
                    if (response.getType().equals("-1")) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        NotificationActivity fragmentSearch = NotificationActivity.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.layoutRootViewpager, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                                .addToBackStack(null)
                                .commit();
                    } else if (response.getType().equals("3")) {
                        viewpager.setCurrentItem(1);

                    } else {
                        viewpager.setCurrentItem(2);

                    }
                }
            }

        }
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
    protected void onResume() {
        super.onResume();
        int count = AppController.getInstance().getmProfileUser().getMessage() + AppController.getInstance().getmProfileUser().getNoty();
        if (count > 0)
            addBadgeAt(2, count);
        else if (badge != null)
            badge.hide(true);

        if (AppController.getInstance().getmProfileUser().getName().isEmpty() || AppController.getInstance().getmProfileUser().getEmail().isEmpty()) {
            startActivity(new Intent(MainActivity.this, ProfileUpdateFragment.class));
        }
    }

    private void addBadgeAt(int position, int number) {
        // add badge
        if (badge == null)
            badge = new QBadgeView(this)
                    .setBadgeNumber(number)
                    .setGravityOffset(12, 2, true)
                    .bindTarget(bnve.getBottomNavigationItemView(position))
                    .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                        @Override
                        public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
//                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                        }
                    });
        else
            badge.setBadgeNumber(number);
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
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Nhấn BACK 2 lần để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
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

    @Override
    public void onCallBack(int pos) {
        int count = AppController.getInstance().getmProfileUser().getMessage() + AppController.getInstance().getmProfileUser().getNoty();
        if (count > 0)
            addBadgeAt(2, count);
        else if (badge != null)
            badge.hide(true);
    }
}
