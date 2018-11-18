package com.skynet.thuenha.ui.filter;

import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.io.File;
import java.util.List;

public interface FilterContract {
    interface View extends BaseView {
        void onSucessGetService(List<Service> list);

        void onSucessGetUtility(List<Utility> list);

        void onSucessSubmitPost(int idPost);

        void onSucessGetPriceService(double price);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getService();

        void getUtility();

        void submitPost(int idService, String title, String price, String area, int city, int district,
                        String address, List<Utility> listUtility, String content, String numberBed,
                        String numberWC, List<File> listPhotos);

        void getPriceService(int idService);

    }

    interface Interactor {
        void getService();

        void getUtility();

        void submitPost(int idService, String title, double price, double area, int city, int district,
                        String address, String listUtility, String content, int numberBed,
                        int numberWC, List<File> listPhotos);

        void getPriceService(int idService);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetService(List<Service> list);

        void onSucessGetUtility(List<Utility> list);

        void onSucessSubmitPost(int idPost);

        void onSucessGetPriceService(double price);

    }
}
