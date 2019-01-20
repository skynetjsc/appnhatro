package com.skynet.mumgo.ui.profile;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skynet.mumgo.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosePhotoBottomSheet extends BottomSheetDialog {


    @BindView(R.id.btnCapture)
    Button btnCapture;
    @BindView(R.id.btnGallery)
    Button btnGallery;
    @BindView(R.id.btnback)
    Button btnback;
    ChoosePhotoOptionCallback paymentBottomCallback;

    public ChoosePhotoBottomSheet(@NonNull final Context context, final ChoosePhotoOptionCallback paymentBottomCallback) {

        super(context, R.style.CoffeeDialog);
        View contentView = View.inflate(getContext(), R.layout.bottom_choose_photo, null);
        this.paymentBottomCallback = paymentBottomCallback;
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        configureBottomSheetBehavior(contentView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

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


    @OnClick({R.id.btnCapture, R.id.btnGallery, R.id.btnback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCapture:
                paymentBottomCallback.onClickCapturePhoto();
                dismiss();
                break;
            case R.id.btnGallery:
                paymentBottomCallback.onClickGalleryPhoto();
                dismiss();

                break;
            case R.id.btnback:
                dismiss();

                break;
        }
    }


    public interface ChoosePhotoOptionCallback {
        void onClickCapturePhoto();

        void onClickGalleryPhoto();
    }
}
