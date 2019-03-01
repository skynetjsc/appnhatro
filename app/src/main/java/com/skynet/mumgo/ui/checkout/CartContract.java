package com.skynet.mumgo.ui.checkout;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

public interface CartContract  {
    interface View extends BaseView {
        void onSucessGetCart(Cart cart);
    }
    interface Presenter extends IBasePresenter,Listener{
        void getCart();
        void updateInfo(String name,String email,String phone,String address);
        void updateTimeShip(String time);
    }
    interface Interactor{
        void getCart();
        void updateInfo(String name,String email,String phone,String address);
        void updateTimeShip(String time);
    }
    interface Listener extends OnFinishListener{
        void onSucessGetCart(Cart cart);
    }
}
