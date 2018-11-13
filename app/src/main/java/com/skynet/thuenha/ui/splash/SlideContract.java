package com.skynet.thuenha.ui.splash;


import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface SlideContract  {
    interface View extends BaseView {
        void onSuccessGetInfor();

    }
    interface PresenterI extends IBasePresenter,OnFinishListener {
       void getInfor();
       void getInforSuccess(Profile profile);
       void notFoundInfor();
    }

    interface Interactor {
        void doGetInfor(String profileInfor);
    }
}
