package com.skynet.mumgo.ui.detailshop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.ShopDetail;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.search.ActivitySearch;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.ui.views.ViewpagerNotSwipe;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailShopActivity extends BaseActivity implements DetailShopContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.plus)
    FloatingActionButton plus;
    @BindView(R.id.btnsearch)
    TextView btnsearch;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    @BindView(R.id.imgNew)
    ImageView imgNew;
    private ProgressDialogCustom dialogCustom;
    private ShopDetail shopDetail;
    private DetailShopContract.Presenter presenter;

    @Override
    protected int initLayout() {
//        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setColor(this, Color.BLACK);
        return R.layout.activity_detail_shop;
    }

    @Override
    protected void initVariables() {
        presenter = new DetailShopPresenter(this);
        onRefresh();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        dialogCustom = new ProgressDialogCustom(this);
        btnsearch.setVisibility(View.VISIBLE);
        plus.setVisibility(View.GONE);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppController.getInstance().getCart() != null && AppController.getInstance().getCart().getListProduct() != null
                && !AppController.getInstance().getCart().getListProduct().isEmpty()) {
            imgNew.setVisibility(View.VISIBLE);
        } else {
            imgNew.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        // TODO: add setContentView(...) invocation
    }

    @OnClick({R.id.imgBack, R.id.imgCart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgCart:
                startActivityForResult(new Intent(DetailShopActivity.this, CartActivity.class), 1000);
                break;
        }
    }

    @Override
    public void onSucessGetShop(ShopDetail shopDetail) {
        this.shopDetail = shopDetail;
        viewpager.setAdapter(new AdapterShopViewpager(getSupportFragmentManager(), shopDetail));
//        tabLayout.setupWithViewPager(viewpager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    btnsearch.setVisibility(View.VISIBLE);
                    plus.setVisibility(View.GONE);
                } else if (tab.getPosition() == 2) {
                    btnsearch.setVisibility(View.GONE);
                    plus.setVisibility(View.VISIBLE);
                } else {
                    btnsearch.setVisibility(View.GONE);
                    plus.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (shopDetail.getShop() != null) {
            tvName.setText(shopDetail.getShop().getName());
            if (shopDetail.getShop().getAvatar() != null && !shopDetail.getShop().getAvatar().isEmpty()) {
                Picasso.with(this).load(shopDetail.getShop().getAvatar()).fit().centerCrop().into(imgCover);
            }
            checkBox.setChecked(shopDetail.getShop().getIs_favourite() == 1);
            ratingBar.setRating((float) shopDetail.getShop().getStar());
            textView18.setText(String.format("%.1f", shopDetail.getShop().getStar()));
            tvAddress.setText(shopDetail.getShop().getAddress());
        }
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogCustom.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogCustom.hideDialog();

    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }

    @Override
    public void onRefresh() {
        presenter.getShop(getIntent().getIntExtra(AppConstant.MSG, 0));
    }

    @OnClick(R.id.imageView11)
    public void onViewClicked() {

    }

    @OnClick({R.id.btnsearch, R.id.plus})
    public void onViewButtonClicked(View view) {
        if (shopDetail == null) return;
        switch (view.getId()) {
            case R.id.btnsearch:
                Intent i = new Intent(DetailShopActivity.this, ActivitySearch.class);
                Bundle b = new Bundle();
                b.putInt("type", ActivitySearch.TYPE_PRODUCT);
                b.putParcelableArrayList(AppConstant.MSG, (ArrayList<? extends Parcelable>) shopDetail.getListProduct());
                i.putExtra(AppConstant.BUNDLE, b);
                startActivity(i);
                break;
            case R.id.plus:
                break;
        }
    }

    @OnClick(R.id.imgShare)
    public void onViewShareClicked() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://mumgo.vn/shop_detail.php?id="+shopDetail.getShop().getId());
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }
}
