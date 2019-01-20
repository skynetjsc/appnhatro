package com.skynet.mumgo.ui.cart;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Promotion;
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
    public void deleteItem(int idProduct) {
        if(isAvaliableView()) {
            view.showProgress();
            interactor.deleteItem(idProduct);


        }
    }

    @Override
    public void updateItem(int idProduct, int number, String note) {
        if(isAvaliableView()){
            view.showProgress();
            interactor.updateItem(idProduct,number,note);
        }
    }

    @Override
    public void checkPromotion(String promo) {
        if(isAvaliableView()){
            view.showProgress();
            interactor.checkPromotion(promo);
        }
    }


    @Override
    public void onSucessGetCart(Cart list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null) {
                view.onSucessGetCart(list);
            }
        }
    }

    @Override
    public void onSucessCheckPromotion(Promotion promotion) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (promotion != null) {
                view.onSucessCheckPromotion(promotion);
            }
        }
    }
}
