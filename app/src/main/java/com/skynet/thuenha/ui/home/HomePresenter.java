package com.skynet.thuenha.ui.home;

import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.ui.base.Presenter;

public class HomePresenter extends Presenter<HomeContract.View> implements HomeContract.Presenter {
    HomeContract.Interactor interactor;

    public HomePresenter(HomeContract.View view) {
        super(view);
        interactor = new HomeRemoteImpl(this);
    }

    @Override
    public void getHome() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getHome();
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetHome(HomeResponse homeResponse) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetHome(homeResponse.getProfile(), homeResponse.getCity(), homeResponse.getServices(), homeResponse.getBanners());
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
