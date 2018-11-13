package com.skynet.thuenha.network.api;

public class DataWrapper<T> {
    private Exception apiException;
    private T data;

    public Exception getApiException() {
        return apiException;
    }

    public void setApiException(Exception apiException) {
        this.apiException = apiException;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}