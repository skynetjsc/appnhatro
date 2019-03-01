package com.skynet.mumgo.ui.location;


import com.google.android.gms.maps.model.LatLng;
import com.skynet.mumgo.models.MyPlace;

public class LocationPresenter implements LocationContract.Presenter {
    LocationContract.View view;
    LocationContract.Interactor interactor;

    public LocationPresenter(LocationContract.View view) {
        this.view = view;
        this.interactor = new LocationImplRemote(this);
    }


    @Override
    public void onDestroyView() {
        view = null;
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

    @Override
    public void getMyAddress(LatLng latLng) {
        if (view == null) return;
        view.showProgress();
        interactor.getMyAddress(latLng);

    }

    @Override
    public void onSuccessGetMyAddress(MyPlace response) {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessGetMyAddress(response);
    }
}
