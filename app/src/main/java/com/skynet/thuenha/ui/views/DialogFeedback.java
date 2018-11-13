package com.skynet.thuenha.ui.views;//package com.vinpearl.vinenglish.ui.views;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.support.annotation.NonNull;
//import android.support.constraint.ConstraintLayout;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.vinpearl.vinenglish.R;
//import com.squareup.picasso.Picasso;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class DialogFeedback {
//    Context context;
//
//    private Dialog mDialog;
//    TextView tvTitle;
//    CallbackDialog callbackDialog;
//
//    public DialogFeedback(@NonNull Context context,  CallbackDialog callbackDialog) {
//        this.context = context;
//        this.callbackDialog = callbackDialog;
//        LayoutInflater factory = LayoutInflater.from(context);
//        mDialog = new Dialog(context, android.R.style.Theme_Light);
//        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        View view = factory.inflate(R.layout.layout_feedback, null, false);
//        mDialog.getWindow().setBackgroundDrawable(
//                new ColorDrawable(Color.TRANSPARENT));
//        mDialog.setContentView(view);
//        mDialog.setCancelable(false);
//        init(view);
//    }
//
//    private void init(View view) {
//        ButterKnife.bind(this, view);
//
//    }
//
//    public void setText(String text) {
//        tvTitle.setText(text);
//    }
//
//    public void showDialog() {
//        if (mDialog != null) {
//            mDialog.show();
//        }
//    }
//
//    public void hideDialog() {
//        if (mDialog != null && mDialog.isShowing()) {
//            mDialog.dismiss();
//        }
//    }
//
//    @OnClick(R.id.btnSubmit3)
//    public void onViewClicked() {
//        hideDialog();
//        if (callbackDialog != null) callbackDialog.onCallBackRate("");
//    }
//
//   public interface CallbackDialog {
//        void onCallBackRate(String reason);
//    }
//}
