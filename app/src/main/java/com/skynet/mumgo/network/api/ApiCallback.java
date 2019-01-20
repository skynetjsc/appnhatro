package com.skynet.mumgo.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class ApiCallback<T> implements Callback<ApiResponse<T>> {
    @Override
    public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
        if (response.body() != null && response.body().getData()!=null) {
            handleResponseData(response.body().getData());
        } else {
            handleError(response);
        }
    }

    abstract protected void handleResponseData(T data);

    abstract protected void handleError(Response<ApiResponse<T>> response);

    abstract protected void handleException(Exception t);

    @Override
    public void onFailure(Call<ApiResponse<T>> call, Throwable t) {
        if (t instanceof Exception) {
            handleException((Exception) t);
        } else {
            //do something else
        }
    }
}