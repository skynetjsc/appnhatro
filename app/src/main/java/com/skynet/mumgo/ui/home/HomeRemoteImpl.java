package com.skynet.mumgo.ui.home;

import com.google.gson.Gson;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.HomeResponse;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.network.api.ApiResponse;
import com.skynet.mumgo.network.api.ApiService;
import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.network.api.CallBackBase;
import com.skynet.mumgo.ui.base.Interactor;
import com.skynet.mumgo.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeRemoteImpl extends Interactor implements HomeContract.Interactor {
    HomeContract.Listener presenter;

    public HomeRemoteImpl(HomeContract.Listener presenter) {
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
            presenter.onErrorAuthorization();
            return;
        }
        getmService().getProfile(profile.getId(),AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, final Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                presenter.onSuccessGetInfor(response.body().getData());
//                                AppController.getInstance().setListBanner(response.body().getData().getBanners());
                            }
                        }, 2000);
                    } else {
                        presenter.onErrorAuthorization();
                    }
                } else {
                    presenter.onErrorAuthorization();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                presenter.onErrorAuthorization();

            }
        });
    }

    @Override
    public void getListProduct(int id) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            presenter.onErrorAuthorization();
            return;
        }
        getmService().getListCombo(profile.getId()).enqueue(new CallBackBase<ApiResponse<List<Combo>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Combo>>> call, Response<ApiResponse<List<Combo>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        presenter.onSucessGetListProduct(response.body().getData());
                    } else {
                        presenter.onError(response.body().getMessage());
                    }
                } else {
                    presenter.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Combo>>> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void getHome() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            presenter.onErrorAuthorization();
            return;
        }
        getmService().getHome(profile.getId()).enqueue(new CallBackBase<ApiResponse<HomeResponse>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<HomeResponse>> call, final Response<ApiResponse<HomeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        presenter.onSucessGetHome(response.body().getData());
                    } else {
                        presenter.onErrorAuthorization();
                    }
                } else {
                    presenter.onErrorAuthorization();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<HomeResponse>> call, Throwable t) {
                presenter.onErrorAuthorization();

            }
        });
    }
}
