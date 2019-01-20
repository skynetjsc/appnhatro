package com.skynet.mumgo.network.api;

import android.net.Uri;

import com.blankj.utilcode.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {

    public static ApiService createNotTokenApi() {
        return ServiceGenerator.createService(ApiService.class);
    }
    public static ApiService getAPIPLACE() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(ApiService.class);
    }
    public static ApiService getAPILIAN() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dev-api2.lianvass.com/app/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(ApiService.class);
    }
    public static ApiService getAPILoginVin() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://elapi.vinpearl.com/CMS/api/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(ApiService.class);
    }
    public static ApiService createTokenApi() {
        return ServiceGenerator.createServiceToken(ApiService.class);
    }
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = FileUtils.getFileByPath(fileUri.getPath());
//        RequestBody requestFile =
//                RequestBody.create(
//                        MediaType.parse(context.getContentResolver().getType(fileUri)),
//                        file
//                );

        RequestBody reqFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(partName, file.getName(), reqFile);
        return body;
    }

    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}
