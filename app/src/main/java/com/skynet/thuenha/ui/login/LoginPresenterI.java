package com.skynet.thuenha.ui.login;


import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.OnFinishListener;
import com.skynet.thuenha.utils.AppConstant;

public class LoginPresenterI implements LoginContract.PresenterI, OnFinishListener {
    LoginContract.View view;
    LoginContract.Interactor interactor;

    public LoginPresenterI(LoginContract.View view) {
        this.view = view;
        this.interactor = new LoginRemoteImpl(this);
    }

    @Override
    public void login(String username, String password, int type) {
        if (username.isEmpty()) {
            view.onError("Vui lòng nhập email hoặc số điện thoại");
            return;
        }
        if (password.isEmpty()) {
            view.onError("Vui lòng nhập Password");
            return;
        }

        view.showProgress();
        interactor.doLogin(username, password, type);
    }

    @Override
    public void onSuccessLogin(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessLogin(profile);
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
        view.onErrorAuthorization();
    }
}
