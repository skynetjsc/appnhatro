package com.skynet.thuenha.ui.home;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.network.api.ExceptionHandler;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class HomeRemoteImpl extends Interactor implements HomeContract.Interactor {
    HomeContract.Listener listener;

    public HomeRemoteImpl(HomeContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getHome() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getHome(profile.getId(), profile.getType()).enqueue(new CallBackBase<ApiResponse<HomeResponse>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<HomeResponse>> call, Response<ApiResponse<HomeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        listener.onSucessGetHome(response.body().getData());
                    } else {
                        new ExceptionHandler<HomeResponse>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<HomeResponse>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }
}
