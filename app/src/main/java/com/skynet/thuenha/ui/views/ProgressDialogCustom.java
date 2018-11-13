package com.skynet.thuenha.ui.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.skynet.thuenha.R;


/**
 * Created by DuongKK on 4/29/2016.
 */
public class ProgressDialogCustom extends ProgressDialog {

    Context context;
    private Dialog mProgressDialog;
    public ProgressDialogCustom(Context context, String msg, boolean isCancelable) {
        super(context);
        this.context=context;
        mProgressDialog= new ProgressDialog(context);
     //   mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(isCancelable);
                  }
    public ProgressDialogCustom(Context context){
        super(context);

        this.context=context;
        LayoutInflater factory = LayoutInflater.from(context);
        mProgressDialog = new Dialog(context);
        mProgressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = factory.inflate(R.layout.dialog_loading, null, false);

        mProgressDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(view);
        mProgressDialog.setCancelable(false);
    }
   public void showDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }





}
