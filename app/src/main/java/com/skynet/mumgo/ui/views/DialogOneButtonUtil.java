package com.skynet.mumgo.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skynet.mumgo.R;

import androidx.annotation.NonNull;


/**
 * Created by thaopt on 8/28/17.
 */

public class DialogOneButtonUtil extends Dialog {
    private TextView mContentTextView;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private TextView buton;
    private int mType = 0;
    private Context mContext;
    private DialogOneButtonClickListener mListener;

    public DialogOneButtonUtil(@NonNull Context context) {
        super(context);
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_one_button);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initView();
    }

    public DialogOneButtonUtil(@NonNull Context context, int resourceImageView, int colorButton, String text, String titleButton, DialogOneButtonClickListener listener) {
        super(context);
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_one_button);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.mListener = listener;

        initView();
        imageView.setImageResource(resourceImageView);
        mContentTextView.setText(text);
        buton.setText(titleButton);
        linearLayout.setBackground(mContext.getDrawable(colorButton));
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imgHeader);
        buton = (TextView) findViewById(R.id.btn_dialog_show_error);
        linearLayout = (LinearLayout) findViewById(R.id.layoutButton);
        mContentTextView = (TextView) findViewById(R.id.content_dialog_one_button);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.okClick();
                dismiss();
            }
        });
    }

    public void setText(String content) {
        mContentTextView.setText(content);
    }

    public void setResourceImage(int content) {
        imageView.setImageResource(content);
    }

    public void setTextButton(String titleButton) {
        buton.setText(titleButton);
    }

    public void setColorButton(int res) {
        linearLayout.setBackground(mContext.getDrawable(res));

    }

    public void setTextHtml(String content) {
        mContentTextView.setText(Html.fromHtml(content));
    }

    public void setContentHtml(Spannable spannable) {
        mContentTextView.setText(spannable);
    }

    public void setType(int type) {
        this.mType = type;
    }

    //callback

    public interface DialogOneButtonClickListener {
        void okClick();
    }


    public void setDialogOneButtonClick(DialogOneButtonClickListener listener) {
        this.mListener = listener;
    }
}
