package com.skynet.thuenha.ui.splash;

import com.google.gson.Gson;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class SlideRemoteImpl extends Interactor implements SlideContract.Interactor {
    SlideContract.PresenterI presenter;

    public SlideRemoteImpl(SlideContract.PresenterI presenter) {
        this.presenter = presenter;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void doGetInfor(String idUser) {
        Profile profile = new Gson().fromJson(idUser, Profile.class);
        if (profile == null) {
            presenter.notFoundInfor();
            return;
        }
        getmService().getHome(profile.getId(), profile.getType()).enqueue(new CallBackBase<ApiResponse<HomeResponse>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<HomeResponse>> call, final Response<ApiResponse<HomeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                presenter.getInforSuccess(response.body().getData().getProfile());
                                AppController.getInstance().setListBanner(response.body().getData().getBanners());
                            }
                        }, 2000);
                    } else {
                        presenter.notFoundInfor();
                    }
                } else {
                    presenter.notFoundInfor();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<HomeResponse>> call, Throwable t) {
                presenter.notFoundInfor();

            }
        });
    }
}
