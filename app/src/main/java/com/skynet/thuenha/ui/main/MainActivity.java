package com.skynet.thuenha.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//
//import com.byappsoft.sap.launcher.Sap_act_main_launcher;
//import com.byappsoft.sap.utils.Sap_Func;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
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

import java.util.ArrayList;
import java.util.List;

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
    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }
        return true;
    }

    private void requestSapPermissions() {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }catch (Exception e){
        }
    }
    private InterstitialAd mInterstitialAd;

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        MobileAds.initialize(this,
                getString(R.string.admob_app_id));
        AdRequest adRequestInterstitial = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("FFABFA1692AA499FFBC2A4EB34847CAE")
                .build();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8862945044447812/1012456009");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                LogUtils.e("Đã tải xong quảng cáo");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                LogUtils.e("onAdFailedToLoad : " + errorCode);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                LogUtils.e("Đã đóng quảng cáo");

                // Code to be executed when the interstitial ad is closed.
            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.setTextVisibility(true);
        bnve.enableItemShiftingMode(false);
        adapter = new AdapterViewpager(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        bnve.setupWithViewPager(viewpager);
        viewpager.setPagingEnabled(false);

//        if(!checkPermission()){
//            requestSapPermissions();
//        }else{
//            Sap_Func.notiUpdate(getApplicationContext());
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int count = AppController.getInstance().getmProfileUser().getMessage();
        if (count > 0)
            addBadgeAt(2, count);
        else if (badge != null)
            badge.hide(true);
//        if(checkPermission()){
//            Sap_Func.setNotiBarLockScreen(this, false);
//            Sap_act_main_launcher.initsapStart(this, "anhhoa125555", true, true);
//        }
//        if (AppController.getInstance().getmProfileUser().getName().isEmpty() || AppController.getInstance().getmProfileUser().getEmail().isEmpty()) {
//            startActivity(new Intent(MainActivity.this, ProfileUpdateFragment.class));
//        }
    }
    @Override
    public void onStop() {
        super.onStop();
        AppController.getInstance().getmSetting().remove(AppConstant.district);
        AppController.getInstance().getmSetting().remove(AppConstant.city);
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
    public void onShowAds() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
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
