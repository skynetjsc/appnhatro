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
    public void getAllPostByService(int idService,int id,int index) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getAllPostByService(idService,id,index);
        }
    }

    @Override
    public void getAllPostByFilter(int index) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getAllPostByFilter(index);
        }
    }

    @Override
    public void queryPostByService(int idService, String query,int index) {

        if (isAvaliableView()) {
            view.showProgress();
            interactor.queryPostByService(idService, query,index);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetPost(List<Post> list,int index) {

        if (isAvaliableView()) {
            view.hiddenProgress();
            if (list != null)
                view.onSucessGetPost(list,index);
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
