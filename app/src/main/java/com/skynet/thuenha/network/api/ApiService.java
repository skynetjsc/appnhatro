package com.skynet.thuenha.network.api;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Profile;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by thaopt on 9/6/17.
 */

public interface ApiService {
    public static String API_ROOT = "http://thuenha.site/api/";

    @GET("get_info.php")
    Call<ApiResponse<Profile>> getProfile(@Query("id") String uid, @Query("type") int type);

    @GET("home.php")
    Call<ApiResponse<HomeResponse>> getHome(@Query("id") String uid, @Query("type") int type);

    @GET("login.php")
    Call<ApiResponse<Profile>> login(@Query("username") String username, @Query("password") String password, @Query("type") int type);

    @GET("verify_code.php")
    Call<ApiResponse<String>> sendCode(@Query("phone") String phone, @Query("type") int type);

    @GET("city.php")
    Call<ApiResponse<List<Address>>> getCities();

    @GET("district.php")
    Call<ApiResponse<List<Address>>> getDistrict(@Query("city_id") int phone);

    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse<Profile>> signUp(@Field("phone") String phone, @Field("password") String pass, @Field("type") int type);

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<ApiResponse> forgotPass(@Field("phone") String email, @Field("type") int type);

}
