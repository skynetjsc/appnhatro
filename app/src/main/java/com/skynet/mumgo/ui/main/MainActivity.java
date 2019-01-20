package com.skynet.mumgo.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.FragmentCallBackTitle;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.ScannerQr;
import com.skynet.mumgo.ui.auth.updateProfile.ActivityProfileUpdate;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.history.ListHistoryActivity;
import com.skynet.mumgo.ui.listchat.ListChatActivity;
import com.skynet.mumgo.ui.views.ViewpagerNotSwipe;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import q.rorbin.badgeview.Badge;

public class MainActivity extends BaseActivity implements OptionBottomSheet.MoreOptionCallback, ContactContract.View, FragmentCallBackTitle {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    @BindView(R.id.bnve)
    RadioGroup radGroup;
    @BindView(R.id.layoutRootViewpager)
    FrameLayout layoutRootViewpager;
    @BindView(R.id.imgAvatarProfile)
    CircleImageView imgAvatarProfile;
    @BindView(R.id.tvNameProfile)
    TextView tvNameProfile;
    @BindView(R.id.nav_home)
    LinearLayout navHome;
    @BindView(R.id.nav_fav)
    LinearLayout navFav;
    @BindView(R.id.nav_cart)
    LinearLayout navCart;
    @BindView(R.id.nav_history)
    LinearLayout navHistory;
    @BindView(R.id.nav_message)
    LinearLayout navMessage;
    @BindView(R.id.nav_news)
    LinearLayout navNews;
    @BindView(R.id.nav_help)
    LinearLayout navHelp;
    @BindView(R.id.nav_customer)
    LinearLayout navCustomer;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    private AdapterMainViewpager adapter;
    private boolean doubleBackToExitPressedOnce;
    private Badge badge;
    private ContactContract.Presenter presenter;
    private OptionBottomSheet optionBottomSheet;
    private OptionBottomSheet bottomAddFriendRequest;
    private String userIdRequestFriend;
    private OptionBottomSheet.MoreOptionCallback addFriendCallback = new OptionBottomSheet.MoreOptionCallback() {
        @Override
        public void onMoreOptionCallback() {
            if (userIdRequestFriend != null) {
            }
        }
    };

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
//        showDialogExpired();
        bottomAddFriendRequest = new OptionBottomSheet(this, addFriendCallback);

        if (getIntent() != null && getIntent().getExtras() != null) {
            String data = getIntent().getExtras().getString(AppConstant.NOTIFICATION_SOCKET);
            if (data != null) {
//
            }
        }
        presenter = new ContactPresenter(this);
        optionBottomSheet = new OptionBottomSheet(this, this);
        presenter.updateToken();
        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imgRight.setImageResource(R.drawable.ic_qrcode);
                switch (checkedId) {
                    case R.id.btmNewest: {
                        viewpager.setCurrentItem(0);
                        setTilte("Sản phẩm mới");
                        break;
                    }
                    case R.id.btmShop: {
                        viewpager.setCurrentItem(1);
                        setTilte("Chuỗi cửa hàng");
                        break;
                    }
                    case R.id.btmCategory: {
                        viewpager.setCurrentItem(2);
                        setTilte("Ngành hàng");
                        imgRight.setImageResource(R.drawable.ic_search_toolbar);
                        break;
                    }
                    case R.id.btmFav: {
                        viewpager.setCurrentItem(3);
                        setTilte("Yêu thích");

                        break;
                    }
                }
            }
        });
        bindUserData();
        radGroup.check(R.id.btmNewest);
    }

    @Override
    public void onSocketConnected() {
        super.onSocketConnected();

    }


    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, null, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ;
        viewpager.setAdapter(new AdapterMainViewpager(getSupportFragmentManager()));
        viewpager.setPagingEnabled(false);
//        viewpager.setCurrentItem(1);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        int count = AppController.getInstance().getmProfileUser().getMessage() + AppController.getInstance().getmProfileUser().getNoty();
//        if (count > 0)
//            addBadgeAt(2, count);
//        else if (badge != null)
//            badge.hide(true);

        if ((AppController.getInstance().getmProfileUser().getName().isEmpty() || AppController.getInstance().getmProfileUser().getEmail().isEmpty()) && !AppController.getInstance().getmSetting().getBoolean("show")) {
            startActivityForResult(new Intent(MainActivity.this, ActivityProfileUpdate.class), 1001);
        }
    }

    private void addBadgeAt(int position, int number) {
        // add badge
//        if (badge == null)
//            badge = new QBadgeView(this)
//                    .setBadgeNumber(number)
//                    .setGravityOffset(12, 2, true)
//                    .bindTarget(bnve.getBottomNavigationItemView(position))
//                    .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                        @Override
//                        public void onDragStateChanged(int dragState, Badge badge, View targetView) {
////                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
////                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        else
//            badge.setBadgeNumber(number);
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


    private void bindUserData() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile != null) {
            tvNameProfile.setText(profile.getName());
            if (profile.getAvatar() != null && !profile.getAvatar().isEmpty()) {
                Picasso.with(this).load(profile.getAvatar()).fit().centerCrop().into(imgAvatarProfile);
            }
        } else {
            return;
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
    public void onMoreOptionCallback() {
//        if (bnve.getCurrentItem() == 1)

    }


    @Override
    public Context getMyContext() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

    }

    @Override
    public void onErrorApi(String message) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onErrorAuthorization() {

    }

    @Override
    public void setTilte(String title) {
        tvTitleToolbar.setText(title);
    }

    @OnClick({R.id.nav_home, R.id.nav_fav, R.id.nav_cart, R.id.nav_history, R.id.nav_message, R.id.nav_news, R.id.nav_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nav_home:
                radGroup.check(R.id.btmNewest);
                break;
            case R.id.nav_fav:
                radGroup.check(R.id.btmFav);
                break;
            case R.id.nav_cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                break;
            case R.id.nav_history:
                startActivity(new Intent(MainActivity.this, ListHistoryActivity.class));

                break;
            case R.id.nav_message:
                startActivity(new Intent(MainActivity.this, ListChatActivity.class));
                break;
            case R.id.nav_news:
                break;
            case R.id.nav_help:
                break;
        }
        drawerLayout.closeDrawer(Gravity.LEFT);

    }

    @OnClick({R.id.imgHome, R.id.imgRight, R.id.imageView9})
    public void onViewToolbarClicked(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.imgRight:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                           startActivity(new Intent(MainActivity.this,ScannerQr.class));
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

                break;
            case R.id.imageView9:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }
}
