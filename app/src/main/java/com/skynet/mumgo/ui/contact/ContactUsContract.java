package com.skynet.mumgo.ui.contact;

import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

public interface ContactUsContract  {
    interface View extends BaseView {
        void onSucessgetFeedback();

    }
    interface Presenter extends IBasePresenter,Listener{
      void feedback(String name,String email,String phone,String content);

    }
    interface Interactor{
        void feedback(String name,String email,String phone,String content);


    }
    interface Listener extends OnFinishListener{
       void onSucessgetFeedback();
    }
}
