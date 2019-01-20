package com.skynet.mumgo.ui.detailProduct;

import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface DetailProductContract {
    interface View extends BaseView {
        void onSucessGetProduct(Product shopDetail);
        void onSucessGetCart(List<Product> list,boolean move);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getProduct(int id);
        void toggleFav(int id, boolean toggle);
        void addToCart(int idProduct,int number, String note,boolean move);
        void getCart();
    }

    interface Interactor {
        void getProduct(int id);
        void toggleFav(int id, boolean toggle);
        void addToCart(int idProduct,int number, String note,boolean move);
        void getCart();
    }

    interface Listener extends OnFinishListener {
        void onSucessGetProduct(Product shopDetail);
        void onSucessGetCart(List<Product> list,boolean move);
    }
}
