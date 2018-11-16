package com.skynet.thuenha.ui.profile;

import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface ProfileContract {
    interface View extends BaseView {
        void onSucessGetProfile(Profile profile);

    }

    interface Presenter extends IBasePresenter,Listener {
        void getProfile();
    }

    interface Interactor {
        void getProfile();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetProfile(Profile profile);
    }
}
