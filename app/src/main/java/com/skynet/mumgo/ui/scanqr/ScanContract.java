package com.skynet.mumgo.ui.scanqr;

import com.skynet.mumgo.models.ShopDetail;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

public interface ScanContract {
    interface View extends BaseView {
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getShop(int id);
    }

    interface Interactor {
        void getShop(int id);
    }

    interface Listener extends OnFinishListener {
    }
}
