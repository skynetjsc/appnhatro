package com.skynet.thuenha.ui.verifyaccount;


import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;

public class VerifyPresenter implements VerifyAccountContract.Presenter {
    VerifyAccountContract.Interactor interactor;
    VerifyAccountContract.View view;

    public VerifyPresenter(VerifyAccountContract.View view) {
        this.view = view;
        interactor = new VerifyRemoteImpl(this);
    }

    @Override
    public void signUp( String phone, String password,  int type) {
        view.showProgress();
        interactor.signUp(phone, password,type);
    }

    @Override
    public void sendCode(String phone,int type) {
        view.showProgress();
        interactor.sendCodeTo(phone,type);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSuccessSignUp(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessSignUp();
    }

    @Override
    public void onSuccessSendCode(String code) {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessSendCode(code);
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
        view.onErrorApi(message);
    }

    @Override
    public void onErrorAuthorization() {
        if (view == null) return;
        view.hiddenProgress();
    }
}
