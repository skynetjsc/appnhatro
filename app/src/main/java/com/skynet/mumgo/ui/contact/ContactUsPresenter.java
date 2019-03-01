package com.skynet.mumgo.ui.contact;

import com.skynet.mumgo.ui.base.Presenter;

public class ContactUsPresenter extends Presenter<ContactUsContract.View> implements ContactUsContract.Presenter {
    ContactUsContract.Interactor interactor;

    public ContactUsPresenter(ContactUsContract.View view) {
        super(view);
        interactor = new ContactUsRemote(this);
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



    @Override
    public void feedback(String name, String email, String phone, String content) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.feedback(name, email, phone, content);
        }
    }

    @Override
    public void onSucessgetFeedback() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessgetFeedback();
        }
    }
}
