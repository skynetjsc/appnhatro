package com.skynet.thuenha.ui.updateProfile;

import android.net.Uri;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.utils.AppConstant;

import java.io.File;

public class ProfilePresenter implements ProfileContract.Presenter {
    ProfileContract.View view;
    ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        interactor = new ProfileRemoteImpl(this);
    }

    @Override
    public void signUp(String name, String address, String password) {
        view.showProgress();
        interactor.signUp(name, address, password);
    }

    @Override
    public void getInfor() {
        view.showProgress();
        String profileStr = AppController.getInstance().getmSetting().getString(AppConstant.KEY_PROFILE, "");
        if (profileStr.isEmpty()) {
            view.onError("not found");
        } else {
            view.showProgress();
            interactor.doGetInfor(profileStr);
        }
    }

    @Override
    public void onSuccessSignUp(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessSignUp();
    }

    @Override
    public void uploadAvatar(File file) {
        view.showProgress();
        interactor.doUpdateAvatar(file, ApiUtil.prepareFilePart("img", Uri.fromFile(file)));
    }

    @Override
    public void update(String name,String email, String phone, String password) {

        view.showProgress();
        if (password.equals("******")) password = "";
        interactor.update(name, email, phone, password);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSuccessUpdate() {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessUpdate();
    }

    @Override
    public void getInforSuccess(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessGetInfor();
    }

    @Override
    public void notFoundInfor() {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorAuthorization();
    }

    @Override
    public void onSuccessUpdatedAvatar() {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessUpdatedAvatar();
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
