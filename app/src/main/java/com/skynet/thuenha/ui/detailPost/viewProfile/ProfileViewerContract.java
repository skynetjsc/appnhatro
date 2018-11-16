package com.skynet.thuenha.ui.detailPost.viewProfile;

import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface ProfileViewerContract {
    interface View extends BaseView {
        void onSucessGetProfile(Profile profile);

    }

    interface Presenter extends IBasePresenter,Listener {
        void getProfile(String idHost);
    }

    interface Interactor {
        void getProfile(String idHost);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetProfile(Profile profile);
    }
}
