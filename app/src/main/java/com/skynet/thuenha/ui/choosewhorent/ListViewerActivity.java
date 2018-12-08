package com.skynet.thuenha.ui.choosewhorent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewerActivity extends BaseActivity implements ListViewerContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    private List<Profile> list;
    private ListViewerContract.Presenter presenter;
    ProgressDialogCustom dialogCustom;


    @Override
    protected int initLayout() {
        return R.layout.fragment_list_viewer;
    }


    @Override
    protected void initVariables() {
        presenter = new ListViewerPresenter(this);
        dialogCustom = new ProgressDialogCustom(this);
        onRefresh();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        rcv.setLayoutManager(new LinearLayoutManager(getMyContext()));
        rcv.setHasFixedSize(true);
        tvToolbar.setText("Ai đã thuê phòng của bạn ?");
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    public void onSucessGetList(List<Profile> list) {
        this.list = list;
        Profile profile = new Profile();
        profile.setId("0");
        profile.setName("Khách vãng lai");
        profile.setPhone("");
        this.list.add(0, profile);
        rcv.setAdapter(new AdapterViewer(this.list, getMyContext(), this));
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
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }


    @Override
    public void onRefresh() {
        presenter.getList(getIntent().getExtras().getInt(AppConstant.MSG));
    }

    @Override
    public void onCallBack(int pos) {
//        Intent i = new Intent(getActivity(), DetailPostActivity.class);
//        i.putExtra(AppConstant.MSG, list.get(pos).getId());
//        startActivity(i);
        Intent i = new Intent();
        i.putExtra(AppConstant.MSG, list.get(pos).getId());
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void onCallBackToggle(int pos, boolean isCheck) {
    }
}
