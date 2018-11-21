package com.skynet.thuenha.ui.makepost;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.skynet.thuenha.ui.base.Presenter;

import java.io.File;
import java.util.List;

public class MakeAPostPresenter extends Presenter<MakeAPostContract.View> implements MakeAPostContract.Presenter {
    MakeAPostContract.Interactor interactor;

    public MakeAPostPresenter(MakeAPostContract.View view) {
        super(view);
        interactor = new MakeAPostImplRemote(this);
    }

    @Override
    public void getService() {
        if (isAvaliableView()) {
            interactor.getService();
        }
    }

    @Override
    public void getUtility() {
        if (isAvaliableView()) {
            interactor.getUtility();
        }
    }

    @Override
    public void submitPost(int idService, String title, String price, String area, int city,
                           int district, String address, List<Utility> listUtility, String content,
                           String numberBed, String numberWC, List<File> listPhotos) {
        if (isAvaliableView()) {
            double priceD, areaD;
            int numberBedD, numberWcD;
            String jsonUtility = "";
            try {
                priceD = Double.parseDouble(price);
                areaD = Double.parseDouble(area);
                numberBedD = Integer.parseInt(numberBed);
                numberWcD = Integer.parseInt(numberWC);
                jsonUtility = new Gson().toJson(listUtility);
                LogUtils.e("Json utility \n " + jsonUtility);
            } catch (Exception e) {
                LogUtils.e(e.fillInStackTrace());
                onError(e.getMessage());
                return;
            }
            view.showProgress();
            interactor.submitPost(idService, title, priceD, areaD, city, district, address, jsonUtility, content, numberBedD, numberWcD, listPhotos);
        }
    }

    @Override
    public void edtPost(int idPost, int idService, String title, String price, String area, int city, int district, String address, List<Utility> listUtility, String content, String numberBed, String numberWC, List<File> listPhotos) {
        if (isAvaliableView()) {
            double priceD, areaD;
            int numberBedD, numberWcD;
            String jsonUtility = "";
            try {
                priceD = Double.parseDouble(price);
                areaD = Double.parseDouble(area);
                numberBedD = Integer.parseInt(numberBed);
                numberWcD = Integer.parseInt(numberWC);
                jsonUtility = new Gson().toJson(listUtility);
                LogUtils.e("Json utility \n " + jsonUtility);
            } catch (Exception e) {
                LogUtils.e(e.fillInStackTrace());
                onError(e.getMessage());
                return;
            }
            view.showProgress();
            interactor.edtPost(idPost, idService, title, priceD, areaD, city, district, address, jsonUtility, content, numberBedD, numberWcD, listPhotos);
        }
    }

    @Override
    public void getPriceService(int idService) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getPriceService(idService);
        }
    }

    @Override
    public void getPriceServiceToChooseService(int idService) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getPriceServiceToChooseService(idService);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetService(List<Service> list) {
        if (isAvaliableView() && list != null) {
            view.onSucessGetService(list);
        }
    }

    @Override
    public void onSucessGetPriceToChooseService(double price) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetPriceToChooseService(price);
        }
    }

    @Override
    public void onSucessGetUtility(List<Utility> list) {
        if (isAvaliableView() && list != null) {

            view.onSucessGetUtility(list);
        }
    }

    @Override
    public void onSucessSubmitPost(int idPost) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessSubmitPost(idPost);
        }
    }

    @Override
    public void onSucessEditPost(int idPost) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessEditPost(idPost);
        }
    }

    @Override
    public void onSucessGetPriceService(double price) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetPriceService(price);
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
