package com.skynet.mumgo.ui;

import android.app.NotificationManager;

import com.blankj.utilcode.util.LogUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.utils.AppConstant;
import com.skynet.mumgo.utils.CommomUtils;

public class FCMService extends FirebaseMessagingService {
    String TAG = "FCM SERVICE ----- ";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        LogUtils.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            LogUtils.d(TAG, "Message data payload: " + remoteMessage.getData());

            String data = remoteMessage.getData().get("data");
            if (data != null) {
                com.skynet.mumgo.models.Notification notification = new Gson().fromJson(data, com.skynet.mumgo.models.Notification.class);
                if (notification != null && AppController.getInstance().getmSetting().getBoolean(AppConstant.NOTI_ON, true)) {
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(0, CommomUtils.createNotificationWithMsg(this, notification.getTitle(), notification.getName(), data));
                }
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            LogUtils.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }


}