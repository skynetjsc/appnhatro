package com.skynet.thuenha.ui.search;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void onSucessGetPost(List<Post> list,int index);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getAllPostByService(int idService, int idDistrict, int index);

        void getAllPostByFilter(int index);

        void queryPostByService(int idService, String query, int index);

    }

    interface Interactor {
        void getAllPostByService(int idService, int idDistrict, int index);

        void getAllPostByFilter(int index);

        void queryPostByService(int idService, String query, int index);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetPost(List<Post> list,int index);
//        void onSucessQueryPost(List<Post> list);
    }
}

