package com.skynet.thuenha.ui.vnpay;


import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

public interface RechargeContract {
    interface View extends BaseView {
        void onSuccessGetPaymenConfirm(String url);

        void onSucessPaid();

    }

    interface Presenter extends IBasePresenter, OnHomeRequestFinish {
        void doPayment(String amount,String note);

        void updateAmount(String url);
    }

    interface Interactor {
        void doPayment(String amount,String note);

        void updateAmount(String url);

    }

    interface OnHomeRequestFinish extends OnFinishListener {
        void onSuccessGetPaymenConfirm(String url);

        void onSucessPaid();
    }
}
