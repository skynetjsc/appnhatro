package com.skynet.mumgo.ui.detailProduct;

import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.Presenter;

import java.util.List;

public class DetailProductPresenter extends Presenter<DetailProductContract.View> implements DetailProductContract.Presenter {
    DetailProductContract.Interactor interactor;

    public DetailProductPresenter(DetailProductContract.View view) {
        super(view);
        interactor = new DetailProductImplRemote(this);
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
    public void getProduct(int id) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getProduct(id);
        }
    }

    @Override
    public void toggleFav(int id, boolean toggle) {
        if (isAvaliableView()) {
            interactor.toggleFav(id,toggle);
        }
    }

    @Override
    public void addToCart(int idProduct, int number, String note,boolean move) {
        if(isAvaliableView()){
            view.showProgress();
            interactor.addToCart(idProduct,number,note,move);
        }
    }

    @Override
    public void getCart() {
        if(isAvaliableView()){
            interactor.getCart();
        }
    }

    @Override
    public void onSucessGetProduct(Product shopDetail) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (shopDetail != null) {
                view.onSucessGetProduct(shopDetail);
            }
        }
    }

    @Override
    public void onSucessGetCart(List<Product> list,boolean move) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null) {
                view.onSucessGetCart(list,move);
            }
        }
    }
}
