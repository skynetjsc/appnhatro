package com.skynet.thuenha.ui.feedback;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Feedback;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity implements FeedbackContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    FeedbackContract.Presenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initVariables() {
        presenter = new FeedbackPresenter(this);
        presenter.getListFeedback();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        swipe.setOnRefreshListener(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onSucessGetListFeedback(List<Feedback> list) {

    }

    @Override
    public void onCommentSucess() {

    }

    @Override
    public void onSucessMakeNewFeedback(String comment) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hiddenProgress() {
        swipe.setRefreshing(false);

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
        presenter.getListFeedback();
    }
}
