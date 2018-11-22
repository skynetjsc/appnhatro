package com.skynet.thuenha.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.ui.main.MainActivity;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.forgotPassword.ForgotPwActivity;
import com.skynet.thuenha.ui.privacy.PrivacyActivity;
import com.skynet.thuenha.ui.privacy.TermActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final int RC_SIGN_IN = 1000;
    @BindView(R.id.username_txt)
    EditText edtEmailPhone;
    @BindView(R.id.pass_txt)
    EditText edtPassword;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvToolbar;
    @BindView(R.id.radGroup)
    RadioGroup radGroup;


    private ProgressDialogCustom dialogCustom;
    private LoginContract.PresenterI presenter;

    @Override
    protected int initLayout() {
        return R.layout.acitivity_login;
    }

    @Override
    protected void initVariables() {
        dialogCustom = new ProgressDialogCustom(this);
        presenter = new LoginPresenterI(this);
        tvToolbar.setText("Đăng nhập");
        tvToolbar.setTextColor(Color.parseColor("#5c6979"));
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return R.id.layoutRoot;
    }

    @OnClick({R.id.login_btn, R.id.forget_txt, R.id.privacy, R.id.term})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                presenter.login(edtEmailPhone.getText().toString(), edtPassword.getText().toString(),radGroup.getCheckedRadioButtonId() == R.id.radCus ? 1 : 2);
                break;
            case R.id.forget_txt:
                startActivity(new Intent(LoginActivity.this, ForgotPwActivity.class));
                break;
            case R.id.privacy:
                startActivity(new Intent(LoginActivity.this, PrivacyActivity.class));
                break;
                case R.id.term:
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


    @OnClick(R.id.imgBtn_back_toolbar)
    public void onViewClicked() {
        onBackPressed();
    }


}
