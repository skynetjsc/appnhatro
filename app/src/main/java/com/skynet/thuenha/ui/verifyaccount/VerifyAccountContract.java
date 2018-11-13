package com.skynet.thuenha.ui.verifyaccount;


import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface VerifyAccountContract {

    interface View extends BaseView {
        void onSuccessSignUp();

        void onSuccessSendCode(String code);

    }

    interface Presenter extends IBasePresenter, VerifyListener {
        void signUp( String phone, String password, int type);

        void sendCode(String phone,int type);

    }

    interface Interactor {
        void signUp( String phone, String password, int type);

        void sendCodeTo(String phone,int type);

    }

    interface VerifyListener extends OnFinishListener {
        void onSuccessSignUp(Profile profile);

        void onSuccessSendCode(String code);
    }

}
