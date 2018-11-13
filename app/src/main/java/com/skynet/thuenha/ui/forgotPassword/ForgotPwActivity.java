package com.skynet.thuenha.ui.forgotPassword;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.SnackBarCallBack;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;
import com.skynet.thuenha.utils.CommomUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.Utils.getContext;

/**
 * Created by Huy on 3/15/2018.
 */

public class ForgotPwActivity extends BaseActivity implements ForgotPwContract.View {
    @BindView(R.id.email_txt)
    EditText mEmail;
    @BindView(R.id.layout_pss)
    LinearLayout layout_pss;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.radGroup)
    RadioGroup radGroup;
    private ForgotPwContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_forgotpassword;
    }


    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new ForgotPwPresenter(this);
        tvTitleToolbar.setText(R.string.forgot_password_title);

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return R.id.layoutRoot;
    }


    @OnClick(R.id.signup_txt)
    public void onClicked(View view) {
        String email = mEmail.getText().toString();
        presenter.signUp(email,radGroup.getCheckedRadioButtonId() == R.id.radCus ? 1 : 2);


    }

    @OnClick(R.id.tvCall)
    public void onCall() {
        CommomUtils.dialPhoneNumber(this, "0987875427");
    }

    @Override
    public void signUpSuccess() {
        showToast("Chúng tôi đã gửi mật khẩu về số điện thoại của bạn. Vui lòng kiểm tra điện thoại và đăng nhập lại!", AppConstant.POSITIVE, new SnackBarCallBack() {
            @Override
            public void onClosedSnackBar() {
                finish();
            }
        });
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onErrorAuthorization() {

    }

    @OnClick(R.id.imgBtn_back_toolbar)
    public void onViewClicked() {
        finish();
    }
}
