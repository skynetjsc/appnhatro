package com.skynet.thuenha.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.ui.main.MainActivity;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity implements SlideContract.View {


    private ProgressDialogCustom dialogCustom;
    private SlideContract.PresenterI presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        dialogCustom = new ProgressDialogCustom(this);
        presenter = new SlidePresenterI(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getInfor();
            }
        },500);
//        getDialogProgress().hideDialog();
//        MainApplication.getInstance().setDay(day);


//        Spannable wordtoSpan = new SpannableString("VINENGLISH");
//        wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#ecac1d")), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvName.setText(wordtoSpan);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            tvName.setText(Html.fromHtml(getString(R.string.splash_name), Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            tvName.setText(Html.fromHtml(getString(R.string.splash_name)));
//        }
    }


    @Override
    public void onSuccessGetInfor() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogCustom.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogCustom.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onErrorAuthorization() {
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
