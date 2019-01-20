package com.skynet.mumgo.ui.detailshop;

import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.ShopDetail;
import com.skynet.mumgo.models.ShopResponse;
import com.skynet.mumgo.ui.base.Presenter;
import com.skynet.mumgo.ui.shop.ShopContract;
import com.skynet.mumgo.ui.shop.ShopImplRemote;

import java.util.List;

public class DetailShopPresenter extends Presenter<DetailShopContract.View> implements DetailShopContract.Presenter {
    DetailShopContract.Interactor interactor;

    public DetailShopPresenter(DetailShopContract.View view) {
        super(view);
        interactor = new DetailShopImplRemote(this);
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
    public void getShop(int id) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getShop(id);
        }
    }

    @Override
    public void toggleFav(int id, boolean toggle) {
        if (isAvaliableView()) {
            interactor.toggleFav(id,toggle);
        }
    }

    @Override
    public void onSucessGetShop(ShopDetail shopDetail) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (shopDetail != null) {
                view.onSucessGetShop(shopDetail);
            }
        }
    }
}
