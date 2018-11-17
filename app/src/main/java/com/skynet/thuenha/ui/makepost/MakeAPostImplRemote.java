package com.skynet.thuenha.ui.makepost;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Post;
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

public class MakeAPostImplRemote extends Interactor implements MakeAPostContract.Interactor {
    MakeAPostContract.Listener listener;

    public MakeAPostImplRemote(MakeAPostContract.Listener listener) {
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
                           int numberBed, int numberWC, List<File> listPhotos) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        RequestBody paramidHost = RequestBody.create(MediaType.parse("text/plain"), profile.getId());
        RequestBody paramidService = RequestBody.create(MediaType.parse("text/plain"), idService + "");
        RequestBody paramtitle = RequestBody.create(MediaType.parse("text/plain"), title + "");
        RequestBody paramprice = RequestBody.create(MediaType.parse("text/plain"), price + "");
        RequestBody paramarea = RequestBody.create(MediaType.parse("text/plain"), area + "");
        RequestBody paramcity = RequestBody.create(MediaType.parse("text/plain"), city + "");
        RequestBody paramdistrict = RequestBody.create(MediaType.parse("text/plain"), district + "");
        RequestBody paramaddress = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody paramlistUtility = RequestBody.create(MediaType.parse("text/plain"), listUtility);
        RequestBody paramcontent = RequestBody.create(MediaType.parse("text/plain"), content);
        RequestBody paramnumberBed = RequestBody.create(MediaType.parse("text/plain"), numberBed + "");
        RequestBody paramnumberWC = RequestBody.create(MediaType.parse("text/plain"), numberWC + "");
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : listPhotos) {
            RequestBody requestImageFile = RequestBody.create(MediaType.parse("image/*"), file);
            parts.add(MultipartBody.Part.createFormData("img[]", file.getName(), requestImageFile));
        }
        getmService().submitPost(paramidHost, paramidService, paramtitle,
                paramprice, paramarea, paramcity, paramdistrict, paramaddress,
                paramcontent, paramlistUtility, paramnumberBed, paramnumberWC, parts).enqueue(new CallBackBase<ApiResponse<Integer>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Integer>> call, Response<ApiResponse<Integer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessSubmitPost(response.body().getData());
                    } else {
                        new ExceptionHandler<Integer>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Integer>> call, Throwable t) {
                LogUtils.e(t.getMessage());
            }
        });
    }

    @Override
    public void getPriceService(int idService) {
        getmService().getPrice(idService).enqueue(new CallBackBase<ApiResponse<PriceService>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<PriceService>> call, Response<ApiResponse<PriceService>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        if (response.body().getData() != null)
                            listener.onSucessGetPriceService(response.body().getData().getValue());
                        else
                            listener.onSucessGetPriceService(5000);
                    } else {
                        new ExceptionHandler<PriceService>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<PriceService>> call, Throwable t) {

            }
        });
    }
}
