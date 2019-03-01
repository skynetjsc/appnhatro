package com.skynet.mumgo.ui.DetailNews;


import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Promotion;
import com.skynet.mumgo.network.api.ApiResponse;
import com.skynet.mumgo.network.api.ApiService;
import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.network.api.CallBackBase;
import com.skynet.mumgo.ui.base.Interactor;
import com.skynet.mumgo.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class DetailNotificationRemoteImpl extends Interactor implements DetailNotificationContract.Interactor {
    DetailNotificationContract.OnFinishDetailNotificationListener listener;

    public DetailNotificationRemoteImpl(DetailNotificationContract.OnFinishDetailNotificationListener listener) {
        this.listener = listener;
    }

    @Override
    public void doGetDetail(String id) {
        if(AppController.getInstance().getmProfileUser() == null ){
            listener.onErrorAuthorization();
            return;
        }

        getmService().getDetailNotification(id,2,AppController.getInstance().getmProfileUser().getId()).enqueue(new CallBackBase<ApiResponse<Promotion>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Promotion>> call, Response<ApiResponse<Promotion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {

                        listener.onSuccessGetDetail(response.body().getData());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Promotion>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }
}
