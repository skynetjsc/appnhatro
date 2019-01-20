package com.skynet.mumgo.ui.main;

import com.skynet.mumgo.ui.base.Presenter;

public class ContactPresenter extends Presenter<ContactContract.View> implements ContactContract.Presenter {
    ContactContract.Interactor interactor;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        interactor = new ContactImplRemote(this);
    }


    @Override
    public void updateToken() {
        interactor.updateToken();
    }


    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }


}
