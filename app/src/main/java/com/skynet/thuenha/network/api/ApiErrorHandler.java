package com.skynet.thuenha.network.api;

import retrofit2.Response;

class ApiErrorHandler {

    public static <R> Exception getErrorData(Response<ApiResponse<R>> response) {
        return new Exception(response.body().message);
    }
}
