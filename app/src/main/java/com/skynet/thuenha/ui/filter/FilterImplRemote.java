package com.skynet.thuenha.ui.filter;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.PriceService;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.network.api.ExceptionHandler;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class FilterImplRemote extends Interactor implements FilterContract.Interactor {
    FilterContract.Listener listener;

    public FilterImplRemote(FilterContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getService() {
        getmService().getServices().enqueue(new CallBackBase<ApiResponse<List<Service>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Service>>> call, Response<ApiResponse<List<Service>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetService(response.body().getData());
                    } else {
                        new ExceptionHandler<List<Service>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Service>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getUtility() {
        getmService().getUtility().enqueue(new CallBackBase<ApiResponse<List<Utility>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Utility>>> call, Response<ApiResponse<List<Utility>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetUtility(response.body().getData());
                    } else {
                        new ExceptionHandler<List<Utility>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Utility>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void submitPost(int idService, String title, double price, double area, int city,
                           int district, String address, String listUtility, String content,
                           int numberBed, int numberWC, List<File> listPhotos) {}

    @Override
    public void getPriceService(int idService) {}
}
