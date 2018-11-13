package com.skynet.thuenha.utils;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by framgia on 23/05/2017.
 */
public class AlarmUtils {
    private static int INDEX = 1;

    public static void create(Context context, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, SchedulingService.class);
//        intent.putExtra("type", INDEX);
//        PendingIntent pendingIntent =
//                PendingIntent.getService(context, INDEX, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        INDEX++;
//        alarmManager
//                .setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(3, CommomUtils.createNotificationWithMsgStick(context, "vinenglish Trip", "Hệ thống đã ghi nhận chuyến đi đặt trước của bạn!"));

    }
}
