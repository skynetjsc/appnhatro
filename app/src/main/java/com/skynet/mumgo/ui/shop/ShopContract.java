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
        void onSuccessGetListFriendShop(List<Shop> list);
        void onSuccessGetListNearbyShop(List<Shop> list);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListShop(int type);
        void getListFriend();
        void getListShopNearby(double lat,double lng);
    }

    interface Interactor {
        void getListShop(int type);
        void getListFriend(int type);
        void getListShopNearby(double lat,double lng);

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetListShop(List<Shop> response);
    }
}
