package com.skynet.thuenha.ui.search;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void onSucessGetPost(List<Post> list);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getAllPostByService(int idService);

        void queryPostByService(int idService, String query);

    }

    interface Interactor {
        void getAllPostByService(int idService);

        void queryPostByService(int idService, String query);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetPost(List<Post> list);
//        void onSucessQueryPost(List<Post> list);
    }
}
