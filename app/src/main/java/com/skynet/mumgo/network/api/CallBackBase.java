package com.skynet.mumgo.network.api;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thaopt on 2/21/18.
 */

public abstract class CallBackBase<T> implements Callback<T> {
    public abstract void onRequestSuccess(Call<T> call, Response<T> response) ;
    public abstract void onRequestFailure(Call<T> call,  Throwable t);
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        LogUtils.a(call.request().toString() + "\n" + new Gson().toJson(response.body()));
        onRequestSuccess(call,response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        LogUtils.a(call.request().toString() + "\n" + t.getMessage());
        onRequestFailure(call,t);
    }
}
