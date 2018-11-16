package com.skynet.thuenha.network.api;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Message;
import com.skynet.thuenha.models.Post;
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

    @GET("list_post.php")
    Call<ApiResponse<List<Post>>> getListPost(@Query("id_service") int phone, @Query("id_district") int idDistrict);

    @GET("list_chat.php")
    Call<ApiResponse<List<ChatItem>>> getListChat(@Query("id_user") String phone, @Query("type") int type);

    @GET("list_favourite.php")
    Call<ApiResponse<List<Post>>> getFavouriteList(@Query("id") String idUser);

    @GET("list_post_host.php")
    Call<ApiResponse<List<Post>>> getListMyPost(@Query("id") String idUser);

    @GET("list_view_post.php")
    Call<ApiResponse<List<Profile>>> getListViewer(@Query("id") int idUser);

    @GET("search.php")
    Call<ApiResponse<List<Post>>> searchListPost(@Query("id_service") int phone, @Query("id_district") int idDistrict, @Query("title") String query);

    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse<Profile>> signUp(@Field("phone") String phone, @Field("password") String pass, @Field("type") int type);

    @FormUrlEncoded
    @POST("rent.php")
    Call<ApiResponse> rent(@Field("user_id") String user_id, @Field("host_id") String host_id, @Field("post_id") int post_id);

    @FormUrlEncoded
    @POST("delete_post.php")
    Call<ApiResponse> deletePost(@Field("id") int user_id);

    @FormUrlEncoded
    @POST("delete_message.php")
    Call<ApiResponse> deleteChat(@Field("id_host") String host_id,@Field("id_user") String user_id,@Field("id_post") int idPOst);

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<ApiResponse> forgotPass(@Field("phone") String email, @Field("type") int type);

    @FormUrlEncoded
    @POST("post_detail.php")
    Call<ApiResponse<DetailPost>> getDetailPost(@Field("user_id") String idUser, @Field("post_id") int postID, @Field("type") int type);

    @FormUrlEncoded
    @POST("favourite.php")
    Call<ApiResponse> toggleFav(@Field("id_user") String idUser, @Field("id_post") int postID, @Field("type") int isFav);

    @FormUrlEncoded
    @POST("pay_view.php")
    Call<ApiResponse> paidForthisPost(@Field("user_id") String idUser, @Field("post_id") int postID);

    @GET("get_message.php")
    Call<ApiResponse<ChatItem>> getListMessageBetween(@Query("id_user") String uiId,@Query("id_host") String id_host,@Query("id_post") int id_post);
    @FormUrlEncoded
    @POST("message.php")
    Call<ApiResponse<Message>> sendMessageTo(@Field("id_post") int id_post,@Field("id_user") String idUser,@Field("id_host") String idShop,@Field("time") String time,@Field("content") String content,@Field("type") int typeUser);
}
