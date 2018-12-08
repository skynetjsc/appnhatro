package com.skynet.thuenha.ui.choosewhorent;

import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class ListViewerPresenter extends Presenter<ListViewerContract.View> implements ListViewerContract.Presenter {
    ListViewerContract.Interactor interactor;

    public ListViewerPresenter(ListViewerContract.View view) {
        super(view);
        interactor = new ListViewerRemote(this);
    }

    @Override
    public void getList(int post) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getList(post);
        }
    }



    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetList(List<Profile> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null)
                view.onSucessGetList(list);
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
