package com.skynet.thuenha.ui.listchat;

import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class ListChatPresenter extends Presenter<ListChatContract.View> implements ListChatContract.Presenter {
    ListChatContract.Interactor interactor;

    public ListChatPresenter(ListChatContract.View view) {
        super(view);
        interactor = new ListChatImplRemote(this);
    }

    @Override
    public void getListChat() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListChat();
        }
    }

    @Override
    public void confirmHired(int idPost, String idHost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.confirmHired(idPost, idHost);
        }
    }

    @Override
    public void deleteChat(String idHost,int ipost) {
        if (isAvaliableView()) {
//            view.showProgress();
            interactor.deleteChat(idHost,ipost);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetListChat(List<ChatItem> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null && list.size() > 0)
                view.onSucessGetListChat(list);
        }
    }

    @Override
    public void onSucessConfirmChat() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessConfirmChat();
        }
    }

    @Override
    public void onSucessDelete() {
        if (isAvaliableView()) {
            view.hiddenProgress();
        }
    }

    @Override
    public void onErrorApi(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }
}
