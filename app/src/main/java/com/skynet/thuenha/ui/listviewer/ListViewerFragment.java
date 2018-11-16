package com.skynet.thuenha.ui.listviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;

import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewerFragment extends BaseFragment implements ListViewerContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    private List<Profile> list;
    private ListViewerContract.Presenter presenter;
    ProgressDialogCustom dialogCustom;

    public static ListViewerFragment newInstance(int post) {
        Bundle args = new Bundle();
        args.putInt(AppConstant.MSG, post);
        ListViewerFragment fragment = new ListViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_list_viewer;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcv.setLayoutManager(new LinearLayoutManager(getMyContext()));
        rcv.setHasFixedSize(true);
        tvToolbar.setText("Ai đã xem tin của tôi");
    }

    @Override
    protected void initVariables() {
        presenter = new ListViewerPresenter(this);
        dialogCustom = new ProgressDialogCustom(getContext());
        onRefresh();
    }

    @Override
    public void onSucessGetList(List<Profile> list) {
        this.list = list;
        rcv.setAdapter(new AdapterViewer(this.list, getMyContext(), this));
    }

    @Override
    public Context getMyContext() {
        return getContext();
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
        showDialogExpiredToken();
    }


    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();

    }

    @Override
    public void onRefresh() {
        presenter.getList(getArguments().getInt(AppConstant.MSG));
    }

    @Override
    public void onCallBack(int pos) {
//        Intent i = new Intent(getActivity(), DetailPostActivity.class);
//        i.putExtra(AppConstant.MSG, list.get(pos).getId());
//        startActivity(i);
    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onCallBackToggle(int pos, boolean isCheck) {
    }
}
