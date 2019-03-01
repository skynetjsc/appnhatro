package com.skynet.mumgo.ui.DetailNews;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Promotion;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNotificationActivity extends BaseActivity implements DetailNotificationContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.code)
    TextView code;

    private DetailNotificationContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;


    @Override
    protected int initLayout() {
        return R.layout.activity_detail_notification;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new DetailNotificationPresenter(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            presenter.getDetail(getIntent().getExtras().getInt(AppConstant.MSG)+"");
        }
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        tvTitleToolbar.setText("Tin tức");

        swipe.setOnRefreshListener(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();
        swipe.setRefreshing(false);
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorAuthorization() {

    }


    @Override
    public void onRefresh() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            presenter.getDetail(getIntent().getExtras().getString(AppConstant.MSG));
        }
    }


    @Override
    public void onSuccessGetDetail(Promotion notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(notification.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvContent.setText(Html.fromHtml(notification.getContent()));
        }
        tvTitle.setText(notification.getTitle());
        tvTime.setText(notification.getDate_end());
        code.setText(notification.getCode());
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("promotion", notification.getCode());
                clipboard.setPrimaryClip(clip);
                showToast("Đã sao chép mã khuyến mãi!", AppConstant.POSITIVE);
            }
        });
        setResult(RESULT_OK);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgBtn_back_toolbar)
    public void onViewClicked() {
        onBackPressed();

    }
}
