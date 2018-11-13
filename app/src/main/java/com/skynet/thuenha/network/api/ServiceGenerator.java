package com.skynet.thuenha.network.api;

import com.blankj.utilcode.util.SPUtils;
import com.skynet.thuenha.utils.AppConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PhamThi on 3/8/2017.
 */

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiService.API_ROOT)
            .addConverterFactory(GsonConverterFactory.create());

    // service have not token
    public static <S> S createService(Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
//        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add("elapi.vinpearl.com", "sha256/b6rSxpFSRgP19XJTbJKUSCO0b2wpa2lhxpGFAMS7nak=")
//                .add("elapi.vinpearl.com", "sha256/IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=")
//                .add("elapi.vinpearl.com", "sha256/K87oWBWM9UZfyddvDfoxL+8lpNyoUB2ptGtn0fv6G2Q=")
//                .build();
//        httpClient.certificatePinner(certificatePinner);
        httpClient.readTimeout(3, TimeUnit.MINUTES);
        httpClient.connectTimeout(3, TimeUnit.MINUTES);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Apikey",
                                new SPUtils(AppConstant.KEY_SETTING).getString(AppConstant.KEY_TOKEN,"c6b5164284474adf4236635b332bea9f"))
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceToken( Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
//        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add("elapi.vinpearl.com", "sha256/b6rSxpFSRgP19XJTbJKUSCO0b2wpa2lhxpGFAMS7nak=")
//                .add("elapi.vinpearl.com", "sha256/IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=")
//                .add("elapi.vinpearl.com", "sha256/K87oWBWM9UZfyddvDfoxL+8lpNyoUB2ptGtn0fv6G2Q=")
//                .build();
//        httpClient.certificatePinner(certificatePinner);
        httpClient.readTimeout(3, TimeUnit.MINUTES);
        httpClient.connectTimeout(3, TimeUnit.MINUTES);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Apikey",
                               new SPUtils(AppConstant.KEY_SETTING).getString(AppConstant.KEY_TOKEN,"c6b5164284474adf4236635b332bea9f"))
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }



}
