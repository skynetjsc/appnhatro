package com.skynet.mumgo.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

/**
 * Created by Huy on 6/28/2017.
 */

public class ChatParentLayout extends RelativeLayout {
    private OnKeyBoardChangeListener onKeyBoardChangeListener;
    public ChatParentLayout(Context context) {
        super(context);
    }

    public ChatParentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatParentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getHeight();
        int newHeight = MeasureSpec.getSize(heightMeasureSpec);
        DisplayMetrics dis = getResources().getDisplayMetrics();
        if(Math.abs(height - newHeight)<dis.heightPixels - 200){
            if ((height - newHeight)>100 && onKeyBoardChangeListener!=null) onKeyBoardChangeListener.onShow(-newHeight + height);
            if ((newHeight - height)>100 && onKeyBoardChangeListener!=null && height > 0) onKeyBoardChangeListener.onHide(newHeight - height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public void setOnKeyBoardChangeListener(OnKeyBoardChangeListener listener){
        this.onKeyBoardChangeListener = listener;
    }
    public static interface OnKeyBoardChangeListener{
        void onShow(int keyboardHeight);
        void onHide(int keyboardHeight);
    }
}