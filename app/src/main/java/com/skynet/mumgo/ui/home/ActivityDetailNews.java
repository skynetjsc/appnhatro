package com.skynet.mumgo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.jaeger.library.StatusBarUtil;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.News;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.detailProduct.DetailProductContract;
import com.skynet.mumgo.ui.detailProduct.DetailProductPresenter;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityDetailNews extends BaseActivity implements DetailProductContract.View {


    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout3)
    AppBarLayout appBarLayout3;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvContent)
    TextView tvContent;
    private int quatity = 0, unit = 0;
    private double price = 0;
    private Product product;
    private DetailProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoaing;
    News news;

    @Override
    protected int initLayout() {
        StatusBarUtil.setTransparent(this);
        return R.layout.activity_detail_news;
    }

    @Override
    protected void initVariables() {
        presenter = new DetailProductPresenter(this);
        dialogLoaing = new ProgressDialogCustom(this);
        news = getIntent().getBundleExtra(AppConstant.BUNDLE).getParcelable(AppConstant.MSG);
        if (news != null) {
            if (news.getImg() != null && !news.getImg().isEmpty()) {
                Picasso.with(this).load(news.getImg()).fit().centerCrop().into(imgCover);
            }
            tvName.setText(news.getTitle());
            tvAddress.setText(news.getDate_end());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               tvContent.setText(Html.fromHtml(news.getContent(),Html.FROM_HTML_MODE_COMPACT));
            }else{
                tvContent.setText(Html.fromHtml(news.getContent()));
            }
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();

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

    private void exceute(int i) {

    }

    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onSucessGetProduct(Product shopDetail) {

    }

    @Override
    public void onSucessGetCart(List<Product> list, boolean move) {
        setResult(RESULT_OK);

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogLoaing.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoaing.hideDialog();
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


}