package com.skynet.mumgo.ui.auth.updateProfile;

import com.google.gson.Gson;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.network.api.ApiResponse;
import com.skynet.mumgo.network.api.ApiService;
import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.network.api.CallBackBase;
import com.skynet.mumgo.ui.base.Interactor;
import com.skynet.mumgo.utils.AppConstant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class ProfileRemoteImpl extends Interactor implements ProfileContract.Interactor {
    public ProfileRemoteImpl(ProfileContract.OnFinishProfileListener listener) {
        this.listener = listener;
    }

    ProfileContract.OnFinishProfileListener listener;


    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void doGetInfor(String profileInfor) {
        Profile profile = new Gson().fromJson(profileInfor, Profile.class);
        if (profile == null) {
            listener.notFoundInfor();
            return;
        }
        getmService().getProfile(profile.getId(),AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        listener.getInforSuccess(response.body().getData());
                    } else {
                        listener.notFoundInfor();
                    }
                } else {
                    listener.notFoundInfor();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                listener.notFoundInfor();

            }
        });
    }


    @Override
    public void update(String name,String email, String address) {
        Profile profile = AppController.getInstance().getmProfileUser();

        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }

        // profile.setType(2);
//        profile.setAddress(type);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("address", ApiUtil.createPartFromString(address));
        map.put("type", ApiUtil.createPartFromString(AppConstant.TYPE_USER+""));
        map.put("id", ApiUtil.createPartFromString(profile.getId()));
        map.put("name", ApiUtil.createPartFromString(name));
        map.put("email", ApiUtil.createPartFromString(email));

        getmService().updateInfor(map).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        listener.onSuccessUpdate();
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public void doUpdateAvatar(File file, MultipartBody.Part part) {
        Profile profile = AppController.getInstance().getmProfileUser();

        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        RequestBody idRequest = ApiUtil.createPartFromString(AppController.getInstance().getmProfileUser().getId());
        RequestBody typeRequest = ApiUtil.createPartFromString(profile.getType()+"");
        Map<String, RequestBody> map = new HashMap<>();
        map.put("id", idRequest);
        map.put("type", typeRequest);
        getmService().uploadAvatar(part, map).enqueue(new CallBackBase<ApiResponse<String>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS ) {
                        listener.onSuccessUpdatedAvatar();
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<String>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });


    }


}
