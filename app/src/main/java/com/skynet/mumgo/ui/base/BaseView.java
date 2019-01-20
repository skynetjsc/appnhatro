package com.skynet.mumgo.ui.base;

import android.content.Context;

/**
 * Created by hoaph on 10/4/2017.
 */

public interface BaseView extends OnFinishListener{
    Context getMyContext();
    void showProgress();

    void hiddenProgress();
}
