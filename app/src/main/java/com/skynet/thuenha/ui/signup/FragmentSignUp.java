package com.skynet.thuenha.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.login.LoginActivity;
import com.skynet.thuenha.ui.privacy.PrivacyActivity;
import com.skynet.thuenha.ui.privacy.TermActivity;
import com.skynet.thuenha.ui.verifyaccount.VerifyAccountActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Huy on 3/15/2018.
 */

public class FragmentSignUp extends BaseActivity implements SignUpContract.View {

    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.username_txt)
    EditText usernameTxt;
    @BindView(R.id.signup_txt)
    TextView signupTxt;
    @BindView(R.id.forget_txt)
    TextView forgetTxt;
    @BindView(R.id.privacy)
    TextView privacy;
    @BindView(R.id.layoutRoot)
    LinearLayout layoutRoot;
    @BindView(R.id.radGroup)
    RadioGroup radGroud;
    private SignUpContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int initLayout() {
        return R.layout.acitivity_signup;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new SignUpPresenter(this);
        tvTitleToolbar.setText("Đăng ký");
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

        Intent i = new Intent(FragmentSignUp.this, VerifyAccountActivity.class);
        i.putExtra("phone", usernameTxt.getText().toString());
        i.putExtra("code", code);
        i.putExtra("type", radGroud.getCheckedRadioButtonId() == R.id.radCus ? 1 : 2);
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

    @OnClick({R.id.imgBtn_back_toolbar, R.id.signup_txt, R.id.privacy,R.id.term})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_back_toolbar:
                onBackPressed();
                break;
            case R.id.signup_txt:
                String phone = usernameTxt.getText().toString();
                presenter.signUp( phone, radGroud.getCheckedRadioButtonId() == R.id.radCus ? 1 : 2);

                break;
            case R.id.privacy:
                startActivity(new Intent(FragmentSignUp.this, PrivacyActivity.class));
                break;
            case R.id.term:
                startActivity(new Intent(FragmentSignUp.this, TermActivity.class));
                break;
        }
    }
}
