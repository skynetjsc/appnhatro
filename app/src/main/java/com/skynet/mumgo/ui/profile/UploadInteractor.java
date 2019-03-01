package com.skynet.mumgo.ui.profile;

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

public class UploadInteractor extends Interactor implements UploadContract.Interactor {
    UploadContract.Listener listener;

    public UploadInteractor(UploadContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getInfor() {
        Profile profile = AppController.getInstance().getmProfileUser();

        if (profile == null) {
            listener.onErrorAuthorization();
            return;

        }
        getmService().getProfile(profile.getId(),AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, final Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AppController.getInstance().setmProfileUser(response.body().getData());
                                listener.onSuccessGetInfor();
                            }
                        }, 2000);
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public void upload(File file, MultipartBody.Part part, int type) {
        Profile profile = AppController.getInstance().getmProfileUser();

        if (profile == null) {
            return;
        }
        RequestBody idRequest = ApiUtil.createPartFromString(AppController.getInstance().getmProfileUser().getId());
        RequestBody typeRequest = ApiUtil.createPartFromString(AppConstant.TYPE_USER + "");
        Map<String, RequestBody> map = new HashMap<>();
        map.put("id", idRequest);
        map.put("type", typeRequest);
        getmService().uploadAvatar(part, map).enqueue(new CallBackBase<ApiResponse<String>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        if (type == 1)
                            AppController.getInstance().getmProfileUser().setAvatar(response.body().getData());
                        else
                            AppController.getInstance().getmProfileUser().setCover(response.body().getData());

                        listener.onSucessUploadAvat();
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
