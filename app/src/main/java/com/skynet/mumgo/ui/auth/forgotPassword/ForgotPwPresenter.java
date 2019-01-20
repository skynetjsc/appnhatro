package com.skynet.mumgo.ui.auth.forgotPassword;

import android.util.Patterns;

public class ForgotPwPresenter implements ForgotPwContract.Presenter {
    ForgotPwContract.View view;
    ForgotPwContract.Interactor interactor;

    public ForgotPwPresenter(ForgotPwContract.View view) {
        this.view = view;
        interactor = new ForgotPwImpl(this);
    }

    @Override
    public void signUp(String email, int type) {
        if (view == null) return;

        if (Patterns.PHONE.matcher(email).matches()) {
            if (email.length() < 10 || email.length() > 11) {
                view.onError("Số điện thoại sai định dạng");
                return;
            }
        }

        view.showProgress();
        interactor.doSignUp(email, type);

    }

    @Override
    public void signUpSuccess() {
        if (view == null) return;
        view.hiddenProgress();
        view.signUpSuccess();
    }

    @Override
    public void onDestroyView() {
        view = null;

    }

    @Override
    public void onErrorApi(String message) {
        if (view != null) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (view != null) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {

    }
}
