package com.skynet.mumgo.ui.profile;

import android.net.Uri;

import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.ui.base.Presenter;

import java.io.File;


public class UploadPresenter extends Presenter<UploadContract.View> implements UploadContract.Presenter {
    UploadContract.Interactor interactor;

    public UploadPresenter(UploadContract.View view) {
        super(view);
        interactor = new UploadInteractor(this);
    }

    @Override
    public void upload(File file, int type) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.upload(file, ApiUtil.prepareFilePart("img", Uri.fromFile(file)), type);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }

    @Override
    public void onSucessUploadAvat() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onSucessUploadAvat();
        }
    }
    @Override
    public void getInfor() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getInfor();
        }
    }

    @Override
    public void onSuccessGetInfor() {
        if(isAvaliableView()){
            view.hiddenProgress();
            view.onSuccessGetInfor();
        }
    }
}
