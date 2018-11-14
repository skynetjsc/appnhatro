package com.skynet.thuenha.ui.detailPost;

import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface DetailPostContract {
    interface View extends BaseView {
        void onSuccessGetDetail(DetailPost detailPost);
        void onSuccessPaid();

    }

    interface Presenter extends IBasePresenter, Listener {
        void getDetailPost(int idPost);

        void paidForThisPost(int idPost);

        void toggleFav(int idPost, boolean isFav);
    }

    interface Interactor {
        void getDetailPost(int idPost);

        void paidForThisPost(int idPost);

        void toggleFav(int idPost, boolean isFav);
    }

    interface Listener extends OnFinishListener {
        void onSuccessGetDetail(DetailPost detailPost);
        void onSuccessPaid();
    }
}
