package com.skynet.thuenha.ui.chatting;


import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.models.Message;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChattingRemoteImpl extends Interactor implements ChattingContract.Interactor {
    ChattingContract.ChattingListener listener;

    public ChattingRemoteImpl(ChattingContract.ChattingListener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getMessages(String idShop, String uiId, int idPost) {
        getmService().getListMessageBetween(uiId, idShop, idPost).enqueue(new CallBackBase<ApiResponse<ChatItem>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<ChatItem>> call, Response<ApiResponse<ChatItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.code() == AppConstant.CODE_API_SUCCESS ) {
                        if( response.body().getData() != null)
                        listener.onSuccessGetMessages(response.body().getData().getContent());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<ChatItem>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public void sendMessage(int idPost, String idShop, String idUser, String content, String time) {
        getmService().sendMessageTo(idPost, idUser, idShop, time, content, AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Message>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Message>> call, Response<ApiResponse<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.code() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSuccessSendMessage(response.body().getData());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Message>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }
}
