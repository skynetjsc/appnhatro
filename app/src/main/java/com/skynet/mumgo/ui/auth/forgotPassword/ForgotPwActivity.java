package com.skynet.mumgo.ui.auth.forgotPassword;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.SnackBarCallBack;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.Utils.getContext;

/**
 * Created by Huy on 3/15/2018.
 */

public class ForgotPwActivity extends BaseActivity implements ForgotPwContract.View {
    @BindView(R.id.username_txt)
    EditText mEmail;

    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;

    private ForgotPwContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_forgotpassword;
    }


    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new ForgotPwPresenter(this);
        tvTitleToolbar.setText("Lấy lại mật khẩu");

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @OnClick(R.id.btn_signup)
    public void onClicked(View view) {
        String email = mEmail.getText().toString();
        presenter.signUp(email,AppConstant.TYPE_USER);
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

    @OnClick(R.id.imgBackToolbar)
    public void onViewClicked() {
        finish();
    }
}
