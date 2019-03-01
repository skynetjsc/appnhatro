package com.skynet.mumgo.ui.category.listProduct;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Nearby;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.ui.base.Presenter;

public class ListProductPresenter extends Presenter<ListProductContract.View> implements ListProductContract.Presenter {
    ListProductContract.Interactor interactor;

    public ListProductPresenter(ListProductContract.View view) {
        super(view);
        interactor = new ListProductImplRemote(this);
    }

    @Override
    public void getListProduct(int id, int idCate, double lat, double lng) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListProduct(id, idCate, lat, lng);
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
    public void onSucessGetListProduct(Nearby response) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (response != null) {
                if (response.getListProduct() != null) {
                    view.onSucessGetListProduct(response.getListProduct(), response.getIndex());
                }
                if(response.getListShop() != null && !response.getListShop().isEmpty()){
                    view.onSucessGetListShop(response.getListShop());

                }if(response.getListFriend() != null && !response.getListFriend().isEmpty()){
                    view.onSucessGetListFriendShop(response.getListFriend());
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
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getCart();
        }
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
}
