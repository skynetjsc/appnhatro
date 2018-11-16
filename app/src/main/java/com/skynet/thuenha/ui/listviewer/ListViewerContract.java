package com.skynet.thuenha.ui.listviewer;

import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseView;
import com.skynet.thuenha.ui.base.IBasePresenter;
import com.skynet.thuenha.ui.base.OnFinishListener;

import java.util.List;

public interface ListViewerContract {
    interface View extends BaseView {
        void onSucessGetList(List<Profile> list);

    }

    interface Presenter extends IBasePresenter, Listener {
        void getList(int post);
    }

    interface Interactor {
        void getList(int post);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetList(List<Profile> list);
    }
}
