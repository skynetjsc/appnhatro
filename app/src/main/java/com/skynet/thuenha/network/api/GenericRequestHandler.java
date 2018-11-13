package com.skynet.thuenha.network.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Response;


public abstract class GenericRequestHandler<R> {
    abstract protected Call<ApiResponse<R>> makeRequest();
    public final LiveData<DataWrapper<R>> doRequest() {
        final MutableLiveData<DataWrapper<R>> liveData = new MutableLiveData<>();
        final DataWrapper<R> dataWrapper = new DataWrapper<R>();
        makeRequest().enqueue(new ApiCallback<R>() {
            @Override
            protected void handleResponseData(R data) {
                dataWrapper.setData(data);
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleError(Response<ApiResponse<R>> response) {
                dataWrapper.setApiException(ApiErrorHandler.getErrorData(response));
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleException(Exception t) {
                dataWrapper.setApiException(t);
                liveData.setValue(dataWrapper);
            }
        });
        return liveData;
    }
}

/*
new ApiCallback<R>() {
            @Override
            protected void handleResponseData(R data) {

            }

            @Override
            protected void handleError(Response<ApiResponse<R>> response) {

            }




            @Override
            protected void handleException(Exception t) {

            }
        }
 */