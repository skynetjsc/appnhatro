package com.skynet.thuenha.ui.chosseAddress;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface ChooseAddressContract {
    interface View extends BaseView {
        void onSucessGetCities(List<Address> list);

        void onSucessGetDistrict(List<Address> list);
    }

    interface Presenter extends IBasePresenter, Listener {
        void getCity();

        void getDistrict(int city);
    }

    interface Interactor {
        void getCity();

        void getDistrict(int city);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetCities(List<Address> list);

        void onSucessGetDistrict(List<Address> list);
    }
}
