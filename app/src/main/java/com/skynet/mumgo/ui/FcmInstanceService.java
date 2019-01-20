package com.skynet.mumgo.ui;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.utils.AppConstant;

public class FcmInstanceService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token -------------_> ", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        AppController.getInstance().getmSetting().put(AppConstant.KEY_TOKEN_FCM,refreshedToken);
    }
}
