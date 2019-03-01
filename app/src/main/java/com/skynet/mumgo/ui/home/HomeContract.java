package com.skynet.mumgo.ui.home;


import com.skynet.mumgo.models.Banner;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.HomeResponse;
import com.skynet.mumgo.models.News;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.ProductResponse;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.models.Suggestion;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void onSuccessGetInfor();

        void onSucessGetBanner(List<Banner> list);
        void onSucessGetBannerCombo(List<Banner> list);

        void onSucessGetCategory(List<Category> list);
        void onSucessGetCategoryParent(List<Category> list);
        void onSucessGetCategoryHeader(List<Category> list);

        void onSucessGetRecommend(List<Product> list);
        void onSucessGetListMoreProduct(List<Combo> list, int index);
        void onSucessGetNews(List<News> list);
    }

    interface PresenterI extends IBasePresenter, Listener {
        void getInfor();
        void getHome();
        void getListProduct(int id);

    }

    interface Interactor {
        void doGetInfor(String profileInfor);
        void getListProduct(int id);

        void getHome();

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetInfor(Profile profile);
        void onSucessGetListProduct(List<Combo> response);

        void onSucessGetHome(HomeResponse response);
    }
}
