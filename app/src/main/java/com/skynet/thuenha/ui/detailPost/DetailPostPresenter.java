package com.skynet.thuenha.ui.detailPost;

import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.ui.base.Presenter;

public class DetailPostPresenter extends Presenter<DetailPostContract.View> implements DetailPostContract.Presenter {
    DetailPostContract.Interactor interactor;

    public DetailPostPresenter(DetailPostContract.View view) {
        super(view);
        interactor = new DetailPostImplRemote(this);
    }

    @Override
    public void getDetailPost(int idPost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getDetailPost(idPost);
        }
    }

    @Override
    public void paidForThisPost(int idPost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.paidForThisPost(idPost);
        }
    }

    @Override
    public void toggleFav(int idPost, boolean isFav) {
        if (isAvaliableView()) {
            interactor.toggleFav(idPost, isFav);
        }
    }

    @Override
    public void deleteThisPost(int idPost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.deleteThisPost(idPost);
        }
    }

    @Override
    public void rentThisPost(int idPost) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.rentThisPost(idPost);
        }
    }

    @Override
    public void onDestroyView() {

        view = null;
    }

    @Override
    public void onSuccessGetDetail(DetailPost detailPost) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (detailPost != null)
                view.onSuccessGetDetail(detailPost);
        }
    }

    @Override
    public void onSuccessPaid() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSuccessPaid();
        }
    }

    @Override
    public void onSucessDelete() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessDelete();
        }
    }

    @Override
    public void onSucessRent() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessRent();
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
