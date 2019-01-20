package com.skynet.mumgo.ui.enterpin;

import com.skynet.mumgo.ui.base.Presenter;

public class PurchasePresenter extends Presenter<PurchaseContract.View> implements PurchaseContract.Presenter {
    PurchaseContract.Interactor interactor;

    public PurchasePresenter(PurchaseContract.View view) {
        super(view);
        interactor = new PurchaseImplRemote(this);
    }

    @Override
    public void book() {
        if(isAvaliableView()){
            view.showProgress();
            interactor.book();
        }
    }

    @Override
    public void sendPin() {
        if(isAvaliableView()){
            view.showProgress();
            interactor.sendPin();
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessBooking() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onSucessBooking();
        }
    }

    @Override
    public void onSucessSendPin(String code) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onSucessSendPin(code);
        }
    }

    @Override
    public void onErrorApi(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }
}
