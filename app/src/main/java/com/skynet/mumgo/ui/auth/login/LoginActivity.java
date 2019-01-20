package com.skynet.mumgo.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.auth.forgotPassword.ForgotPwActivity;
import com.skynet.mumgo.ui.auth.signup.ActivitySignUp;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.main.MainActivity;
import com.skynet.mumgo.ui.privacy.PrivacyActivity;
import com.skynet.mumgo.ui.privacy.TermActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final int RC_SIGN_IN = 1000;
    @BindView(R.id.username_txt)
    EditText edtEmailPhone;
    @BindView(R.id.pass_txt)
    EditText edtPassword;


    private ProgressDialogCustom dialogCustom;
    private LoginContract.PresenterI presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initVariables() {
        dialogCustom = new ProgressDialogCustom(this);
        presenter = new LoginPresenterI(this);

    }

    @Override
    protected void initViews() {
//        StatusBarUtil.setTransparent(this);
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @OnClick({R.id.login_btn, R.id.forget_txt, R.id.btnSignUpFB2, R.id.btnSignInGG2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                presenter.login(edtEmailPhone.getText().toString(), edtPassword.getText().toString(), AppConstant.TYPE_USER);
                break;
            case R.id.forget_txt:
                  startActivity(new Intent(LoginActivity.this, ForgotPwActivity.class));
                break;
            case R.id.btnSignUpFB2:
                startActivity(new Intent(LoginActivity.this, PrivacyActivity.class));
                break;
            case R.id.btnSignInGG2:
                startActivity(new Intent(LoginActivity.this, TermActivity.class));
                break;
        }
    }

    @Override
    public void onSuccessLogin(Profile profile) {
        setResult(RESULT_OK);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("isFromLogin", true);
        startActivity(i);
        finish();
    }

    @Override
    public void onSuccesLoginFacebook(Profile profile) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
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
//      showDialogError(getString(R.string.error_unk),getString(R.string.back),R.drawable.bg_button_red_dialog,R.drawable.ic_error);
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
//        showDialogError(message,getString(R.string.back),R.drawable.bg_button_red_dialog,R.drawable.ic_error);
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick(R.id.imgBackToolbar)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvSignup)
    public void onViewSignUpClicked() {
        startActivity(new Intent(LoginActivity.this,ActivitySignUp.class));
    }
}
