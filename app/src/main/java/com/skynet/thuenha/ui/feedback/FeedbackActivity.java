package com.skynet.thuenha.ui.feedback;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class FeedbackActivity extends BaseActivity implements FeedbackContract.View, SwipeRefreshLayout.OnRefreshListener, AdapterFeedback.feedbackCallback {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    FeedbackContract.Presenter presenter;
    @BindView(R.id.message_txt)
    EditText messageTxt;
    @BindView(R.id.send_imv)
    ImageView sendImv;
    @BindView(R.id.bottom_chat_layout)
    LinearLayout bottomChatLayout;
    private List<Feedback> list;

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
        tvToolbar.setText("Phản hồi");
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
        this.list = list;
        rcv.setAdapter(new AdapterFeedback(this.list, this, this));
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

    @Override
    public void clickLike(Feedback fb, boolean isChecked) {
        presenter.toggleLikeFeedback(fb.getId(), isChecked);
    }

    @Override
    public void clickComment(Feedback fb) {

    }

    @Override
    public void clickRep(Feedback fb) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send_imv)
    public void onViewEdtClicked() {
        if (!messageTxt.getText().toString().isEmpty())
            presenter.makeNewFeedback(messageTxt.getText().toString());
    }
}
