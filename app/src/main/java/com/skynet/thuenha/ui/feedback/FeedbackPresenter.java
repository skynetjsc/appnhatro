package com.skynet.thuenha.ui.feedback;

import com.skynet.thuenha.models.Feedback;
import com.skynet.thuenha.ui.base.Presenter;

import java.util.List;

public class FeedbackPresenter extends Presenter<FeedbackContract.View> implements FeedbackContract.Presenter {
    FeedbackContract.Interactor interactor;

    public FeedbackPresenter(FeedbackContract.View view) {
        super(view);
        interactor = new FeedbackImplRemote(this);
    }

    @Override
    public void getListFeedback() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListFeedback();
        }
    }

    @Override
    public void toggleLikeFeedback(int id, boolean isLiked) {
        if (isAvaliableView()) {
            interactor.toggleLikeFeedback(id,isLiked ? 1 : 0);
        }
    }

    @Override
    public void commentFeedback(int idFeedback, String comment) {
        if (isAvaliableView()) {
            interactor.commentFeedback(idFeedback,comment);
        }
    }

    @Override
    public void makeNewFeedback(String comment) {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.makeNewFeedback(comment);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetListFeedback(List<Feedback> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessGetListFeedback(list);
        }
    }

    @Override
    public void onCommentSucess() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onCommentSucess();
        }
    }

    @Override
    public void onSucessMakeNewFeedback(String comment) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onSucessMakeNewFeedback(comment);
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
