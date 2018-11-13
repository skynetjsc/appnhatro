package com.skynet.thuenha.ui.chosseAddress;

import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class ChooseAddressPresenter extends Presenter<ChooseAddressContract.View> implements ChooseAddressContract.Presenter {
    ChooseAddressContract.Interactor interactor;

    public ChooseAddressPresenter(ChooseAddressContract.View view) {
        super(view);
        interactor = new ChooseAddressRemoteImpl(this);
    }

    @Override
    public void getCity() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getCity();
        }
    }

    @Override
    public void getDistrict(int city) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getDistrict(city);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetCities(List<Address> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetCities(list);
        }
    }

    @Override
    public void onSucessGetDistrict(List<Address> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetDistrict(list);
        }
    }

    @Override
    public void onErrorApi(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }
}
