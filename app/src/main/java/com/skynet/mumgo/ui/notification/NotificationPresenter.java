package com.skynet.mumgo.ui.Notification;


import com.skynet.mumgo.models.Notification;
import com.skynet.mumgo.models.Promotion;

import java.util.List;

public class NotificationPresenter implements NotificationContract.Presenter {
    NotificationContract.View view;
    NotificationContract.Interactor interactor;

    public NotificationPresenter(NotificationContract.View view) {
        this.view = view;
        this.interactor = new NotificationRemoteImpl(this);
    }


    @Override
    public void getAllService(String idShop) {
        view.showProgress();
        interactor.doGetAllService(idShop);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }


    @Override
    public void onSuccessGetServices(List<Notification> listGroupServices) {
        if(listGroupServices == null ){
            onError("Không có dữ liệu");
            return;
        }
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessGetServices(listGroupServices);
    }

    @Override
    public void onErrorApi(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorApi(message);
    }

    @Override
    public void onError(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError(message);

    }

    @Override
    public void onErrorAuthorization() {

    }
}
