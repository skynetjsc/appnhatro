package com.skynet.mumgo.ui.main;

import android.content.Context;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skynet.mumgo.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionBottomSheet extends BottomSheetDialog {
    MoreOptionCallback paymentBottomCallback;
    @BindView(R.id.imgConfirm)
    ImageView imgConfirm;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.b)
    View b;
    @BindView(R.id.OptionRight)
    TextView OptionRight;
    @BindView(R.id.OptionLeft)
    TextView OptionLeft;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.confirmlayout)
    LinearLayout confirmlayout;


    public OptionBottomSheet(@NonNull final Context context, final MoreOptionCallback paymentBottomCallback) {

        super(context, R.style.CoffeeDialog);
        View contentView = View.inflate(getContext(), R.layout.bottom_confirm, null);
        this.paymentBottomCallback = paymentBottomCallback;
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        configureBottomSheetBehavior(contentView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

    }

    public OptionBottomSheet(@NonNull final Context context, int resIcon, Spanned content, String left, String right,final MoreOptionCallback paymentBottomCallback) {

        super(context, R.style.CoffeeDialog);
        View contentView = View.inflate(getContext(), R.layout.bottom_confirm, null);
        this.paymentBottomCallback = paymentBottomCallback;
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        configureBottomSheetBehavior(contentView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setData(resIcon,content,left,right);
    }

    private void configureBottomSheetBehavior(View contentView) {
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from((View) contentView.getParent());

        if (mBottomSheetBehavior != null) {
            mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    //showing the different states
                    switch (newState) {
                        case BottomSheetBehavior.STATE_HIDDEN:
                            dismiss(); //if you want the modal to be dismissed when user drags the bottomsheet down
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
    }

    @OnClick({R.id.OptionRight, R.id.OptionLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.OptionRight:
                dismiss();
                paymentBottomCallback.onMoreOptionCallback();
                break;
            case R.id.OptionLeft:
                dismiss();
                break;
        }
    }

    public void setData(int resIcon, Spanned content, String left, String right) {
        imgConfirm.setImageResource(resIcon);
        tvContent.setText(content);
        OptionLeft.setText(left);
        OptionRight.setText(right);
    }


    public interface MoreOptionCallback {
        void onMoreOptionCallback();
    }
}
