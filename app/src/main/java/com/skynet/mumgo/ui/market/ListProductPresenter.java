package com.skynet.mumgo.ui.market;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Market;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.ui.base.Presenter;

import java.util.List;

public class ListProductPresenter extends Presenter<ListProductContract.View> implements ListProductContract.Presenter {
    ListProductContract.Interactor interactor;

    public ListProductPresenter(ListProductContract.View view) {
        super(view);
        interactor = new ListProductImplRemote(this);
    }

    @Override
    public void getListProduct(double lat,double lng) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListProduct(lat,lng);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetListProduct(List<Market > list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null) {
                    view.onSucessGetListProduct(list);
            }
        }
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
