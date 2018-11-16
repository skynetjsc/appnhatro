package com.skynet.thuenha.ui.mypost;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class MyPostPresenter extends Presenter<MyPostContract.View> implements MyPostContract.Presenter {
    MyPostContract.Interactor interactor;

    public MyPostPresenter(MyPostContract.View view) {
        super(view);
        interactor = new MyPostImplRemote(this);
    }

    @Override
    public void getList() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getList();
        }
    }

    @Override
    public void toggleFav(int idPost, boolean isFav) {
        if (isAvaliableView()) {
            interactor.toggleFav(idPost, isFav ? 1 : 2);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetList(List<Post> list) {
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
