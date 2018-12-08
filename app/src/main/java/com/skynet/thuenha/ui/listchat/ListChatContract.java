package com.skynet.thuenha.ui.listchat;

import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface ListChatContract {
    interface View extends BaseView{
        void onSucessGetListChat(List<ChatItem> list);
        void onSucessConfirmChat();
    }
    interface Presenter extends IBasePresenter,Listener{
        void getListChat();
        void confirmHired(int idPost,String idHost,String idRent);
        void deleteChat(String idHost,int idPost);
    }
    interface Interactor {
        void getListChat();
        void confirmHired(int idPost,String idHost,String idRent);
        void deleteChat(String idHost,int idPost);
    }
    interface Listener extends OnFinishListener{
        void onSucessGetListChat(List<ChatItem> list);
        void onSucessConfirmChat();
        void onSucessDelete();
    }
}
