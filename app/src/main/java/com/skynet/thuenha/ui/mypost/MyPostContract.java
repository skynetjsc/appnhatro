package com.skynet.thuenha.ui.mypost;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface MyPostContract {
    interface View extends BaseView {
        void onSucessGetList(List<Post> list);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getList();
        void toggleFav(int idPost, boolean isFav);
    }

    interface Interactor {
        void getList();
        void toggleFav(int idPost, int isFav);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetList(List<Post> list);
    }
}
