package com.skynet.thuenha.ui.mypost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.favourite.AdapterFavourite;
import com.skynet.thuenha.ui.favourite.FavouriteContract;
import com.skynet.thuenha.ui.favourite.FavouriteFragment;
import com.skynet.thuenha.ui.favourite.FavouritePresenter;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPostFragment extends BaseFragment implements MyPostContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<Post> list;
    private MyPostContract.Presenter presenter;

    public static MyPostFragment newInstance() {
        Bundle args = new Bundle();
        MyPostFragment fragment = new MyPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_favourite;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcv.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        rcv.setHasFixedSize(true);
        tvToolbar.setText("Tin đăng của tôi");
    }

    @Override
    protected void initVariables() {
        presenter = new MyPostPresenter(this);
        swipe.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onSucessGetList(List<Post> list) {
        this.list = list;
        rcv.setAdapter(new AdapterPost(this.list, getMyContext(), this));
    }

    @Override
    public Context getMyContext() {
        return getContext();
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
        showDialogExpiredToken();
    }


    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();

    }

    @Override
    public void onRefresh() {
        presenter.getList();
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), DetailPostActivity.class);
        i.putExtra(AppConstant.MSG, list.get(pos).getId());
        startActivity(i);
    }

    @Override
    public void onCallBackToggle(int pos, boolean isCheck) {
        presenter.toggleFav(list.get(pos).getId(), isCheck);
    }
}
