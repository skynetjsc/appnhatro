package com.skynet.thuenha.ui.home;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.Banner;
import com.skynet.thuenha.models.HomeResponse;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void onSucessGetHome(Profile profile, List<Address> addresses, List<Service> services, List<Banner> banners);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getHome();
    }

    interface Interactor {
        void getHome();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetHome(HomeResponse homeResponse);

    }
}
