package com.skynet.thuenha.ui.base;


import com.skynet.thuenha.network.api.ApiService;

/**
 * Created by thaopt on 12/1/17.
 */

public abstract class Interactor {
    private ApiService mService;
    public Interactor(){
        mService = createService();

    }
    public abstract ApiService createService();
    public ApiService getmService() {
        return mService;
    }
}
