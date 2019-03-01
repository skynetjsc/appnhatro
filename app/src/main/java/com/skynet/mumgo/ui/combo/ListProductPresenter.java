package com.skynet.mumgo.ui.combo;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.ui.base.Presenter;

public class ListProductPresenter extends Presenter<ListProductContract.View> implements ListProductContract.Presenter {
    ListProductContract.Interactor interactor;

    public ListProductPresenter(ListProductContract.View view) {
        super(view);
        interactor = new ListProductImplRemote(this);
    }

    @Override
    public void getListProduct(int id) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListProduct(id);
        }
    }

    @Override
    public void toggleFav(int id, boolean toggle) {
        if (isAvaliableView()) {
            interactor.toggleFav(id, toggle);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetListProduct(Combo response) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (response != null) {
                if (response.getList() != null) {
                    view.onSucessGetListProduct(response);
                }
            }
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
    public void getCart() {
        if(isAvaliableView()){
            view.showProgress();
            interactor.getCart();
        }
    }

    @Override
    public void addToCart(int idProduct, int number) {
        interactor.addToCart(idProduct,number);
    }

    @Override
    public void onErrorApi(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }
}
