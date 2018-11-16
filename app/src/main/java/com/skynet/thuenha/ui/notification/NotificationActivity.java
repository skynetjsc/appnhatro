package com.skynet.thuenha.ui.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Notification;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.DetailNotificationActivity.DetailNotificationActivity;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NotificationActivity extends BaseFragment implements ICallback, NotificationContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;
    private NotificationContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    @BindView(R.id.rcvNotification)
    RecyclerView rcvNotification;
    Profile profileModel;
    List<Notification> listGroupServices = new ArrayList<>();

    public static NotificationActivity newInstance() {

        Bundle args = new Bundle();

        NotificationActivity fragment = new NotificationActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcvNotification.setLayoutManager(new LinearLayoutManager(getMyContext()));
        rcvNotification.setHasFixedSize(true);
        swipe.setOnRefreshListener(this);
        tvToolbar.setText("Thông báo");
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(getMyContext());
        presenter = new NotificationPresenter(this);
        profileModel = AppController.getInstance().getmProfileUser();
        if (profileModel == null) {
            showDialogExpiredToken();
            return;
        }
        presenter.getAllService(profileModel.getId());
    }


    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroyView();

        super.onDestroyView();


    }

    @Override
    public void showProgress() {
//        dialogLoading.showDialog();
        swipe.setRefreshing(true);
    }

    @Override
    public void hiddenProgress() {
//        dialogLoading.hideDialog();
        swipe.setRefreshing(false);
    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        Toast.makeText(getMyContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        Toast.makeText(getMyContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorAuthorization() {

    }


    @Override
    public void onSuccessGetServices(List<Notification> listGroupServices) {
        if (listGroupServices != null) {
            this.listGroupServices.clear();
            this.listGroupServices.addAll(listGroupServices);
        }
        rcvNotification.setAdapter(new NotificationAdapter(this.listGroupServices, getMyContext(), this));
    }


    @Override
    public void onRefresh() {
        presenter.getAllService(profileModel.getId());
    }

    // Click to item to fire this
    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), DetailNotificationActivity.class);
//        if (listGroupServices.get(pos).isRead() == 0) {
//            AppController.getInstance().getmProfileUser().setNoty(AppController.getInstance().getmProfileUser().getNoty() - 1);
//            ((MainActivity) getActivity()).setBadget(AppController.getInstance().getmProfileUser().getNoty());
//        }
        i.putExtra(AppConstant.MSG, this.listGroupServices.get(pos).getId());
        startActivityForResult(i, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == getActivity().RESULT_OK) {
            //     onRefresh();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }
}
