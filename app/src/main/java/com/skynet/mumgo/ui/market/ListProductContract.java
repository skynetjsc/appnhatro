package com.skynet.mumgo.ui.market;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Market;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Market> list);


    }

    interface Presenter extends IBasePresenter, Listener {
        void getListProduct(double lat, double lng);
    }

    interface Interactor {
        void getListProduct(double lat, double lng);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(List<Market> list );


    }
}
