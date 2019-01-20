package com.skynet.mumgo.ui.main;

import android.content.ContentResolver;

import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface ContactContract {
    interface View extends BaseView {

    }

    interface Presenter extends IBasePresenter ,Listener{
        void updateToken();

    }

    interface Interactor {
        void updateToken();
    }

    interface Listener extends OnFinishListener {

    }
}
