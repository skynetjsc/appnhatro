package com.skynet.thuenha.ui.profile;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.network.api.ExceptionHandler;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileImplRemote extends Interactor implements ProfileContract.Interactor {
    ProfileContract.Listener listener;

    public ProfileImplRemote(ProfileContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getProfile() {
        final Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {

            listener.onErrorAuthorization();
            return;
        }
        getmService().getProfile(profile.getId(), profile.getType()).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetProfile(response.body().getData());
                    } else {
                        new ExceptionHandler<Profile>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }
}
