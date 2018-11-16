package com.skynet.thuenha.ui.profile;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.Presenter;

public class ProfilePresenter extends Presenter<ProfileContract.View> implements ProfileContract.Presenter {
    ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view) {
        super(view);
        interactor = new ProfileImplRemote(this);
    }

    @Override
    public void getProfile() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getProfile();
        }
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
    public void onSucessGetProfile(Profile profile) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (profile != null) {
                AppController.getInstance().setmProfileUser(profile);
                view.onSucessGetProfile(profile);
            }
        }
    }
}
