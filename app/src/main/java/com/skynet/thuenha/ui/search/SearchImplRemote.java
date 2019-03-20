package com.skynet.thuenha.ui.search;

import com.google.gson.Gson;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.Filter;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Post;
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

public class SearchImplRemote extends Interactor implements SearchContract.Interactor {
    SearchContract.Listener listener;
    Address district;

    public SearchImplRemote(SearchContract.Listener listener) {
        this.listener = listener;

    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getAllPostByService(int idService, int idDistrict,int index) {
        if (AppController.getInstance().getmProfileUser() == null) {
            listener.onErrorAuthorization();
            return;
        }
        String json = AppController.getInstance().getmSetting().getString(AppConstant.district);
        if (json != null && !json.isEmpty())
            district = new Gson().fromJson(json, Address.class);
        int id = idDistrict;
        if (id != 0) {
            id = district != null ? district.getId() : 0;
        }
        getmService().getListPost(AppController.getInstance().getmProfileUser().getId(), idService, id,index).enqueue(new CallBackBase<ApiResponse<List<Post>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Post>>> call, Response<ApiResponse<List<Post>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetPost(response.body().getData(), Integer.parseInt(response.body().getMessage()));
                    } else {
                        new ExceptionHandler<List<Post>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Post>>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void getAllPostByFilter(int index) {
        String json = AppController.getInstance().getmSetting().getString(AppConstant.district);
        if (json != null && !json.isEmpty())
            district = new Gson().fromJson(json, Address.class);
        Filter filter = AppController.getInstance().getFilter();
        if (filter == null) {
            return;
        }
        if (AppController.getInstance().getmProfileUser() == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getListFilterPost(AppController.getInstance().getmProfileUser().getId(),
                filter.getIdService() == 0 ? "" : filter.getIdService() + "",
                filter.getMin() == 0 ? "" : filter.getMin() + "",
                filter.getMax() == 0 ? "" : filter.getMax() + "", filter.getPrice() == 0 ? "" : filter.getPrice() + "",
                filter.getListIdUtility(), district != null ? district.getId() : 0)
                .enqueue(new CallBackBase<ApiResponse<List<Post>>>() {
                    @Override
                    public void onRequestSuccess(Call<ApiResponse<List<Post>>> call, Response<ApiResponse<List<Post>>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                                listener.onSucessGetPost(response.body().getData(),0);
                            } else {
                                new ExceptionHandler<List<Post>>(listener, response.body()).excute();
                            }
                        } else {
                            listener.onError(response.message());
                        }
                    }

                    @Override
                    public void onRequestFailure(Call<ApiResponse<List<Post>>> call, Throwable t) {
                        listener.onErrorApi(t.getMessage());
                    }
                });
    }

    @Override
    public void queryPostByService(int idService, String query,int index) {
        String json = AppController.getInstance().getmSetting().getString(AppConstant.district);
        if (json != null && !json.isEmpty())
            district = new Gson().fromJson(json, Address.class);
        if (AppController.getInstance().getmProfileUser() == null) {
            listener.onErrorAuthorization();
            return;
        }
        Filter filter = AppController.getInstance().getFilter();
        Call<ApiResponse<List<Post>>> search;
        if (filter == null) {
            search = getmService().searchListPost(AppController.getInstance().getmProfileUser().getId(), idService,
                    district != null ? district.getId() : 1, query);
        } else {
            search = getmService().searchListPost(AppController.getInstance().getmProfileUser().getId(),
                    filter.getIdService() == 0 ? "" : filter.getIdService() + "",
                    district != null ? district.getId() : 1, query,
                    filter.getMin() == 0 ? "500000" : filter.getMin() + "",
                    filter.getMax() == 0 ? "20000000" : filter.getMax() + "",
                    filter.getListIdUtility());
        }
        search.enqueue(new CallBackBase<ApiResponse<List<Post>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Post>>> call, Response<ApiResponse<List<Post>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetPost(response.body().getData(),0);
                    } else {
                        new ExceptionHandler<List<Post>>(listener, response.body()).excute();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Post>>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }
}
