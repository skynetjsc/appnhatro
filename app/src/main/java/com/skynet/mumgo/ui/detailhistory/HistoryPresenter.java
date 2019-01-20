package com.skynet.mumgo.ui.detailhistory;

import com.skynet.mumgo.models.History;
import com.skynet.mumgo.ui.base.Presenter;

public class HistoryPresenter extends Presenter<HistoryContract.View> implements HistoryContract.Presenter {
    HistoryContract.Interactor interactor;

    public HistoryPresenter(HistoryContract.View view) {
        super(view);
        interactor = new HistoryImplRemote(this);
    }


    @Override
    public void onDestroyView() {
        view = null;
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



    @Override
    public void getHistory(int id) {
        if(isAvaliableView()){
            view.showProgress();
            interactor.getHistory(id);
        }
    }

    @Override
    public void cancle(int id) {
        if(isAvaliableView()){
            view.showProgress();
            interactor.cancle(id);
        }
    }

    @Override
    public void onSucessGetCart(History history) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetCart(history);
        }
    }

    @Override
    public void onSucessCancel() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessCancel();
        }
    }
}
