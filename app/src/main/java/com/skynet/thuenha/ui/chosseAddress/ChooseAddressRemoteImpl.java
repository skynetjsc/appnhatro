package com.skynet.thuenha.ui.chosseAddress;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.network.api.ExceptionHandler;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChooseAddressRemoteImpl extends Interactor implements ChooseAddressContract.Interactor {
    ChooseAddressContract.Listener listener;

    public ChooseAddressRemoteImpl(ChooseAddressContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getCity() {
        getmService().getCities().enqueue(new CallBackBase<ApiResponse<List<Address>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Address>>> call, Response<ApiResponse<List<Address>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        listener.onSucessGetCities(response.body().getData());
                    } else {
                        new ExceptionHandler<List<Address>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Address>>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void getDistrict(int city) {
        getmService().getDistrict(city).enqueue(new CallBackBase<ApiResponse<List<Address>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Address>>> call, Response<ApiResponse<List<Address>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        listener.onSucessGetDistrict(response.body().getData());
                    } else {
                        new ExceptionHandler<List<Address>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Address>>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }
}
