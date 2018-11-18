package com.skynet.thuenha.ui.feedback;

import com.skynet.thuenha.models.Feedback;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface FeedbackContract {
    interface View extends BaseView {
        void onSucessGetListFeedback(List<Feedback> list);

        void onCommentSucess();

        void onSucessMakeNewFeedback(String comment);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getListFeedback();

        void toggleLikeFeedback(int id, boolean isLiked);

        void commentFeedback(int idFeedback, String comment);

        void makeNewFeedback(String comment);
    }

    interface Interactor {
        void getListFeedback();

        void toggleLikeFeedback(int id, int isLiked);

        void commentFeedback(int idFeedback, String comment);

        void makeNewFeedback(String comment);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListFeedback(List<Feedback> list);

        void onCommentSucess();

        void onSucessMakeNewFeedback(String comment);

    }
}
