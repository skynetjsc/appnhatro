package com.skynet.mumgo.ui.category;

import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.ui.base.Presenter;

import java.util.List;

public class CategoryPresenter extends Presenter<CategoryContract.View> implements CategoryContract.Presenter {
    CategoryContract.Interactor interactor;

    public CategoryPresenter(CategoryContract.View view) {
        super(view);
        interactor = new CategoryImplRemote(this);
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
    public void getCategory() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getCategory();
        }
    }


    @Override
    public void onSucessGetCategory(List<Category> categories) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (categories != null) {
                view.onSuccessGetListCategory(categories);
            }
        }
    }
}
