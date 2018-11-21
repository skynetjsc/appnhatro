package com.skynet.thuenha.ui.search;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class SearchPresenter extends Presenter<SearchContract.View> implements SearchContract.Presenter {
    SearchContract.Interactor interactor;

    public SearchPresenter(SearchContract.View view) {
        super(view);
        interactor = new SearchImplRemote(this);
    }

    @Override
    public void getAllPostByService(int idService,int id) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getAllPostByService(idService,id);
        }
    }

    @Override
    public void getAllPostByFilter() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getAllPostByFilter();
        }
    }

    @Override
    public void queryPostByService(int idService, String query) {

        if (isAvaliableView()) {
            view.showProgress();
            interactor.queryPostByService(idService, query);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetPost(List<Post> list) {

        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null)
                view.onSucessGetPost(list);
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
