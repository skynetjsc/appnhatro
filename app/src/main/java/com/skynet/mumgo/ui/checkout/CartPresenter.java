package com.skynet.mumgo.ui.checkout;

import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.ui.base.Presenter;

public class CartPresenter extends Presenter<CartContract.View> implements CartContract.Presenter {
    CartContract.Interactor interactor;

    public CartPresenter(CartContract.View view) {
        super(view);
        interactor = new CartImplRemote(this);
    }


    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }


    @Override
    public void getCart() {
        if(isAvaliableView()){
            view.showProgress();
            interactor.getCart();
        }
    }




    @Override
    public void onSucessGetCart(Cart list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null) {
                AppController.getInstance().setCart(list);
                view.onSucessGetCart(list);
            }
        }
    }

}
