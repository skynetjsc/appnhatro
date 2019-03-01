package com.skynet.mumgo.ui.location;

import com.google.android.gms.maps.model.LatLng;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.MyPlace;
import com.skynet.mumgo.models.Shop;
import com.skynet.mumgo.models.ShopResponse;
import com.skynet.mumgo.ui.base.BaseView;
import com.skynet.mumgo.ui.base.IBasePresenter;
import com.skynet.mumgo.ui.base.OnFinishListener;

import java.util.List;

public interface LocationContract {
    interface View extends BaseView {
        void onSuccessGetMyAddress(MyPlace response);
    }

    interface Presenter extends IBasePresenter, Listener {
        void getMyAddress(LatLng latLng);
    }

    interface Interactor {
        void getMyAddress(LatLng latLng);

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetMyAddress(MyPlace response);
    }
}
