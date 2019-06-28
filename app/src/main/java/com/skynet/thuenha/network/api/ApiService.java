package com.skynet.thuenha.network.api;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.models.Feedback;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Image;
import com.skynet.thuenha.models.Message;
import com.skynet.thuenha.models.Notification;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.PriceService;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Term;
import com.skynet.thuenha.models.Utility;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    public static String API_ROOT = "http://chapp.com.vn/cms/api/";

    @GET("get_info.php")
    Call<ApiResponse<Profile>> getProfile(@Query("id") String uid, @Query("type") int type);

    @GET("home.php")
    Call<ApiResponse<HomeResponse>> getHome(@Query("id") String uid, @Query("type") int type);

    @GET("service.php")
    Call<ApiResponse<List<Service>>> getServices();

    @GET("utility.php")
    Call<ApiResponse<List<Utility>>> getUtility();

    @GET("price.php")
    Call<ApiResponse<PriceService>> getPrice(@Query("id_service") int id_service);

    @Multipart
    @POST("update_image.php")
    Call<ApiResponse<List<Image>>> addPhoto(@Part("id") RequestBody id_post,
                                            @Part List<MultipartBody.Part> listFile);

    @FormUrlEncoded
    @POST("delete_image.php")
    Call<ApiResponse> deletePhoto(@Field("id") int idPhoto);

    @GET("login.php")
    Call<ApiResponse<Profile>> login(@Query("username") String username, @Query("password") String password, @Query("type") int type);

    @GET("verify_code.php")
    Call<ApiResponse<String>> sendCode(@Query("phone") String phone, @Query("type") int type);

    @GET("city.php")
    Call<ApiResponse<List<Address>>> getCities();

    @GET("district.php")
    Call<ApiResponse<List<Address>>> getDistrict(@Query("city_id") int phone);

    @GET("list_post.php")
    Call<ApiResponse<List<Post>>> getListPost(@Query("user_id") String user_id, @Query("id_service") int phone, @Query("id_district") int idDistrict, @Query("index") int index);

    @GET("filter.php")
    Call<ApiResponse<List<Post>>> getListFilterPost(@Query("user_id") String user_id, @Query("id_service") String phone, @Query("price_min") String min,
                                                    @Query("price_max") String max,   @Query("price") String price, @Query("id_utility") String id_utility, @Query("id_district") int idDistric);

    @GET("list_chat.php")
    Call<ApiResponse<List<ChatItem>>> getListChat(@Query("id_user") String phone, @Query("type") int type);

    @GET("list_favourite.php")
    Call<ApiResponse<List<Post>>> getFavouriteList(@Query("id") String idUser);

    @GET("list_post_host.php")
    Call<ApiResponse<List<Post>>> getListMyPost(@Query("id") String idUser);

    @GET("list_view_post.php")
    Call<ApiResponse<List<Profile>>> getListViewer(@Query("id") int idUser);

    @GET("search.php")
    Call<ApiResponse<List<Post>>> searchListPost(@Query("user_id") String user_id,
                                                 @Query("id_service") int id_service,
                                                 @Query("id_district") int idDistrict,
                                                 @Query("title") String query);

    @GET("search.php")
    Call<ApiResponse<List<Post>>> searchListPost(@Query("user_id") String user_id,
                                                 @Query("id_service") String id_service,
                                                 @Query("id_district") int idDistrict,
                                                 @Query("title") String query,
                                                 @Query("price_min") String min,
                                                 @Query("price_max") String max,
                                                 @Query("id_utility") String id_utility);

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
    @POST("rent.php")
    Call<ApiResponse> rentPost(@Field("user_id") String user_id,@Field("host_id") String host_id, @Field("post_id") int post_id);

    @FormUrlEncoded
    @POST("delete_message.php")
    Call<ApiResponse> deleteChat(@Field("id_host") String host_id, @Field("id_user") String user_id, @Field("id_post") int idPOst);

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
    Call<ApiResponse<ChatItem>> getListMessageBetween(@Query("id_user") int uiId, @Query("id_host") int id_host, @Query("id_post") int id_post);

    @FormUrlEncoded
    @POST("message.php")
    Call<ApiResponse<Message>> sendMessageTo(@Field("id_post") int id_post, @Field("id_user") int idUser,
                                             @Field("id_host") int idShop, @Field("time") String time,
                                             @Field("content") String content, @Field("type") int typeUser,
                                             @Field("attach") int attach);

    @GET("notification.php")
    Call<ApiResponse<List<Notification>>> getListNotification(@Query("id") String uid, @Query("type") int type);

    @GET("list_feedback.php")
    Call<ApiResponse<List<Feedback>>> getListFeedback(@Query("user_id") String uid, @Query("type") int type);

    @FormUrlEncoded
    @POST("like.php")
    Call<ApiResponse> likeFeedback(@Field("id") int idFeedback, @Field("user_id") String user_id, @Field("type") int type,
                                   @Field("is_like") int is_like);

    @FormUrlEncoded
    @POST("vnpay_php/create_link.php")
    Call<ApiResponse<String>> createLink(@Field("order_desc") String order_desc, @Field("amount") String amount);

    @FormUrlEncoded
    @POST("update_account.php")
    Call<ApiResponse> updateAccount(@Field("id") String id, @Field("type") int type, @Field("money") double money
            , @Field("vnp_TransactionNo") String vnp_TransactionNo, @Field("vnp_TxnRef") String vnp_TxnRef, @Field("type_transaction") String type_transaction);

    @FormUrlEncoded
    @POST("comment.php")
    Call<ApiResponse> commentFeedback(@Field("id") int idFeedback, @Field("user_id") String user_id, @Field("type") int type,
                                      @Field("comment") String comment);

    @FormUrlEncoded
    @POST("feedback.php")
    Call<ApiResponse> makeNewFeedback(@Field("user_id") String user_id, @Field("type") int type,
                                      @Field("content") String comment);

    @GET("notification_detail.php")
    Call<ApiResponse<Notification>> getDetailNotification(@Query("id") String id, @Query("type") int type, @Query("user_id") String shID);

    @Multipart
    @POST("post.php")
    Call<ApiResponse<Integer>> submitPost(@Part("host_id") RequestBody host_id, @Part("id_service") RequestBody idServiceBody, @Part("title") RequestBody title,
                                          @Part("price") RequestBody price, @Part("area") RequestBody area, @Part("city_id") RequestBody city_id,
                                          @Part("district_id") RequestBody district_id, @Part("address") RequestBody address, @Part("content") RequestBody content,
                                          @Part("id_utility") RequestBody id_utility, @Part("number_bed") RequestBody number_bed, @Part("number_wc") RequestBody number_wc,
                                          @Part List<MultipartBody.Part> listFile);

    @Multipart
    @POST("post.php")
    Call<ApiResponse<Integer>> submitPost(@Part("host_id") RequestBody host_id, @Part("id_service") RequestBody idServiceBody, @Part("title") RequestBody title,
                                          @Part("price") RequestBody price, @Part("area") RequestBody area, @Part("city_id") RequestBody city_id,
                                          @Part("district_id") RequestBody district_id, @Part("address") RequestBody address, @Part("content") RequestBody content,
                                          @Part("id_utility") RequestBody id_utility, @Part("number_bed") RequestBody number_bed, @Part("number_wc") RequestBody number_wc);

    @FormUrlEncoded
    @POST("edit-post.php")
    Call<ApiResponse<Integer>> edtPost(@Field("id") int idPost, @Field("host_id") String host_id, @Field("id_service") int idServiceBody,
                                       @Field("title") String title,
                                       @Field("price") double price, @Field("area") double area,
                                       @Field("city_id") int city_id,
                                       @Field("district_id") int district_id, @Field("address") String address,
                                       @Field("content") String content,
                                       @Field("id_utility") String id_utility, @Field("number_bed") int number_bed,
                                       @Field("number_wc") int number_wc);

    @Multipart
    @POST("update_profile.php")
    Call<ApiResponse> updateInfor(@PartMap() Map<String, okhttp3.RequestBody> partMap);

    @Multipart
    @POST("update_avatar.php")
    Call<ApiResponse<String>> uploadAvatar(@Part MultipartBody.Part image, @PartMap() Map<String, okhttp3.RequestBody> partMap);

    @GET("term.php")
    Call<ApiResponse<Term>> getTerm();

    @GET("instruction.php")
    Call<ApiResponse<Term>> getIntruction();

    @GET("privacy.php")
    Call<ApiResponse<Term>> getPrivacy();
}
