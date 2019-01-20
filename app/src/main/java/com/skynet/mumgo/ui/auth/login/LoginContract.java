package com.skynet.mumgo.ui.auth.login;


import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

public interface LoginContract  {
    interface View extends BaseView {
        void onSuccessLogin(Profile profile);
        void onSuccesLoginFacebook(Profile profile);

    }

    interface PresenterI extends IBasePresenter,OnFinishListener {
        void login(String username, String password, int type);
        void onSuccessLogin(Profile profile);
    }

    interface Interactor {
        void doLogin(String username, String password, int type);
        void doLoginGGG(String idGG, String email, String name);

    }
}
