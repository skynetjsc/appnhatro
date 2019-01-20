package com.skynet.mumgo.ui.auth.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.auth.verifyaccount.VerifyAccountActivity;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Huy on 3/15/2018.
 */

public class ActivitySignUp extends BaseActivity implements SignUpContract.View {


    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.username_txt)
    EditText usernameTxt;


    private SignUpContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new SignUpPresenter(this);
        tvTitleToolbar.setText("Đăng ký mới");
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }




    @Override
    public void signUpSuccess(String code) {
//        Toast.makeText(getContext(), R.string.signup_success, Toast.LENGTH_SHORT).show();
//        // ((AccountActivity) getActivity()).layoutPrivacy.setVisibility(View.GONE);
//        finishFragment();

        Intent i = new Intent(ActivitySignUp.this, VerifyAccountActivity.class);
        i.putExtra("phone", usernameTxt.getText().toString());
        i.putExtra("code", code);
        startActivity(i);
    }



    @Override
    public void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
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
//        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {

    }

    @OnClick({R.id.imgBackToolbar, R.id.btn_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBackToolbar:
                onBackPressed();
                break;
            case R.id.btn_signup:
                String phone = usernameTxt.getText().toString();
                presenter.signUp( phone);

                break;

        }
    }
}
