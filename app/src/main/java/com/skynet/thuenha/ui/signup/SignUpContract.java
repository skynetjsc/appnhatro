package com.skynet.thuenha.ui.signup;


import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public interface SignUpContract {
    interface View extends BaseView {
        void signUpSuccess(String code);

    }

    interface Presenter extends IBasePresenter {
        void signUp(String phone, int type);

        void signUpSuccess(String code);



    }

    interface Interactor {
        void doSignUp(String phone,  int type);

    }
}
