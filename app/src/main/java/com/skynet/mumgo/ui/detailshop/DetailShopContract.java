package com.skynet.mumgo.ui.detailshop;

import com.skynet.mumgo.models.ShopDetail;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

public interface DetailShopContract {
    interface View extends BaseView {
        void onSucessGetShop(ShopDetail shopDetail);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getShop(int id);
        void toggleFav(int id,boolean toggle);
    }

    interface Interactor {
        void getShop(int id);
        void toggleFav(int id,boolean toggle);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetShop(ShopDetail shopDetail);
    }
}
