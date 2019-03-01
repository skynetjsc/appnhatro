package com.skynet.mumgo.ui.scanqr;

import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.models.ShopDetail;
import com.skynet.mumgo.network.api.ApiResponse;
import com.skynet.mumgo.network.api.ApiService;
import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.network.api.CallBackBase;
import com.skynet.mumgo.ui.base.Interactor;
import com.skynet.mumgo.ui.detailshop.DetailShopContract;
import com.skynet.mumgo.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class ScanImplRemote extends Interactor implements ScanContract.Interactor {
    ScanContract.Listener listener;

    public ScanImplRemote(ScanContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }


    @Override
    public void getShop(int id) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().scanShop(profile.getId(), id).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                    } else {
                        listener.onErrorApi(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

}
