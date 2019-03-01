package com.skynet.mumgo.ui.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.lany.picker.HourMinutePicker;
import com.skynet.mumgo.R;

import java.util.Calendar;


/**
 * Created by Huy on 7/4/2017.
 */

public class AlertDialogCustom {
    public static AlertDialog dialogMessage(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.dialog_lost_connect, null, false);
//        TextView button = (TextView) view.findViewById(R.id.tv_ok);
//        builder.setView(view);
//        builder.setCancelable(false);
//        final AlertDialog alertDialog = builder.create();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.getWindow().setBackgroundDrawable(
//                new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return null;
    }

    public static AlertDialog showDialogDateTime(final Context context, final CallBack iCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_pick_datetime, null, false);
//        LinearLayout layout = (LinearLayout) view.findViewById(R.id.rate);
        final HourMinutePicker timePicker = view.findViewById(R.id.timePicker);
        TextView textView = view.findViewById(R.id.ok);
        timePicker.setSelectionDivider(new ColorDrawable(0xffff0000));
        timePicker.setSelectionDividerHeight(2);
        String timeStart, timeEnd;
        final Calendar startC = Calendar.getInstance();
        timePicker.setOnTimeChangedListener(new HourMinutePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(HourMinutePicker hourMinutePicker, int i, int i1) {
                    startC.set(Calendar.HOUR_OF_DAY, i);
                    startC.set(Calendar.MINUTE, i1);
            }
        });

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startC.getTimeInMillis() <= System.currentTimeMillis()) {
                    Toast.makeText(context, "Thời gian không hợp lệ", Toast.LENGTH_LONG).show();
                    return;
                }
                iCallback.onCallBack(startC);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }
    public interface CallBack{
        void onCallBack(Calendar start);
    }

}
