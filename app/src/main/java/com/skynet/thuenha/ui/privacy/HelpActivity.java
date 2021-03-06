package com.skynet.thuenha.ui.privacy;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Term;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HelpActivity extends BaseActivity {
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int initLayout() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initVariables() {
        ApiUtil.createNotTokenApi().getIntruction().enqueue(new CallBackBase<ApiResponse<Term>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Term>> call, Response<ApiResponse<Term>> response) {
                if (response.isSuccessful() && response.body().getData() != null) {
                    tv.setText(Html.fromHtml(response.body().getData().getContent()));
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Term>> call, Throwable t) {

            }
        });
    }
    private InterstitialAd mInterstitialAd;

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        tvTitleToolbar.setText("Hướng dẫn");

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
