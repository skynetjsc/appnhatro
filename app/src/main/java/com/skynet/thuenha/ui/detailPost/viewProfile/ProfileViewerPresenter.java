package com.skynet.thuenha.ui.detailPost.viewProfile;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.Presenter;
import com.skynet.thuenha.ui.profile.ProfileContract;
import com.skynet.thuenha.ui.profile.ProfileImplRemote;

public class ProfileViewerPresenter extends Presenter<ProfileViewerContract.View> implements ProfileViewerContract.Presenter {
    ProfileViewerContract.Interactor interactor;

    public ProfileViewerPresenter(ProfileViewerContract.View view) {
        super(view);
        interactor = new ProfileViewerImplRemote(this);
    }

    @Override
    public void getProfile(String idHost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getProfile(idHost);
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
