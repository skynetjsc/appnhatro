package com.skynet.mumgo.ui.combo;

import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(Combo combo);        void onSucessGetCart(Cart cart);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();
        void addToCart(int idProduct,int number);

    }

    interface Interactor {
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();
        void addToCart(int idProduct,int number);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(Combo combo);
        void onSucessGetCart(Cart cart);

    }
}
