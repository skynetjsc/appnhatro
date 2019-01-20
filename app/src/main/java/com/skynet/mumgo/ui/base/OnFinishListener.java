package com.skynet.mumgo.ui.base;

public interface OnFinishListener {


    void onErrorApi(String message);

    void onError(String message);

    void onErrorAuthorization();
}
