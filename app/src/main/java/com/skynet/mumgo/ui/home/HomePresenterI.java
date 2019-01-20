package com.skynet.mumgo.ui.home;


import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.HomeResponse;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.utils.AppConstant;

public class HomePresenterI implements HomeContract.PresenterI {
    HomeContract.View view;
    HomeContract.Interactor interactor;

    public HomePresenterI(HomeContract.View view) {
        this.view = view;
        interactor = new HomeRemoteImpl(this);
    }

    @Override
    public void getInfor() {

        String profileStr = AppController.getInstance().getmSetting().getString(AppConstant.KEY_PROFILE, "");
        if (profileStr.isEmpty()) {
            view.onError("not found");
        } else {
            view.showProgress();
            interactor.doGetInfor(profileStr);
        }

    }

    @Override
    public void getHome() {
        if (view == null) return;
        view.showProgress();
        interactor.getHome();
    }

    @Override
    public void onSuccessGetInfor(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessGetInfor();
    }

    @Override
    public void onSucessGetHome(HomeResponse response) {
        if (view == null) return;
        view.hiddenProgress();
        if (response.getBanners() != null && !response.getBanners().isEmpty())
            view.onSucessGetBanner(response.getBanners());
        if (response.getCategory() != null && !response.getCategory().isEmpty())
            view.onSucessGetCategory(response.getCategory());
        if (response.getNews() != null && !response.getNews().isEmpty())
            view.onSucessGetNews(response.getNews());
        if (response.getSuggest() != null && !response.getSuggest().isEmpty())
            view.onSucessGetRecommend(response.getSuggest());
    }


    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }

    @Override
    public void onError(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }

    @Override
    public void onErrorAuthorization() {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }


}
