package com.skynet.thuenha.ui.vnpay;


import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class RechargePresenter implements RechargeContract.Presenter {
    RechargeContract.View view;
    RechargeContract.Interactor interactor;

    public RechargePresenter(RechargeContract.View view) {
        this.view = view;
        this.interactor = new RechargeRemoteImpl(this);
    }


    @Override
    public void onDestroyView() {
        view = null;
    }


    @Override
    public void onErrorApi(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorApi(message);
    }

    @Override
    public void onError(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError(message);

    }

    @Override
    public void onErrorAuthorization() {
        if (view == null) return;
        view.hiddenProgress();
    }

    @Override
    public void doPayment(String amount,String note) {
        if (view == null) return;
        if(amount.isEmpty()){
            onError("Please input your amount");
            return;
        }

        interactor.doPayment(amount,note);
    }

    @Override
    public void updateAmount(String url) {
        if(view == null) return;
        view.showProgress();
        interactor.updateAmount(url);
    }

    @Override
    public void onSuccessGetPaymenConfirm(String url) {
        if (view == null) return;
        view.hiddenProgress();
        LogUtils.e("URL PAYPAL  "+ url);
        view.onSuccessGetPaymenConfirm(url);
    }

    @Override
    public void onSucessPaid() {
        if(view == null) return;
        view.hiddenProgress();
        view.onSucessPaid();
    }
}
