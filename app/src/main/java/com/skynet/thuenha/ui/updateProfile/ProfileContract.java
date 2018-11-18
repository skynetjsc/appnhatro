package com.skynet.thuenha.ui.updateProfile;




import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.io.File;

import okhttp3.MultipartBody;

public interface ProfileContract {
    interface View extends BaseView {
        void onSuccessGetInfor();
        void onSuccessUpdatedAvatar();
        void onSuccessUpdate();
        void onSuccessSignUp();


    }
    interface Presenter extends IBasePresenter,OnFinishProfileListener{
        void getInfor();
        void uploadAvatar(File file);
        void update(String name,  String address, String password);
        void signUp(String name, String address, String password);

    }

    interface Interactor {
        void doGetInfor(String profileInfor);
        void doUpdateAvatar(File file, MultipartBody.Part part);
        void update(String name, String address, String password);
        void signUp(String name, String address, String password);

    }

    interface OnFinishProfileListener extends OnFinishListener {
        void getInforSuccess(Profile profile);
        void notFoundInfor();
        void onSuccessUpdate();
        void onSuccessUpdatedAvatar();
        void onSuccessSignUp(Profile profile);

    }
}
