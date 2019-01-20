package com.skynet.mumgo.ui.shop;

import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.Shop;
import com.skynet.mumgo.models.ShopResponse;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface ShopContract {
    interface View extends BaseView {
        void onSuccessGetListShop(List<Shop> list);
        void onSuccessGetListHotShop(List<Shop> list);
        void onSucessGetCategory(List<Category> categories);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListShop(int type);
        void getCategory();
    }

    interface Interactor {
        void getListShop(int type);
        void getCategory();

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetListShop(ShopResponse response);
        void onSucessGetCategory(List<Category> categories);
    }
}
