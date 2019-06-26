package com.skynet.thuenha.ui.verifyaccount;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.ui.main.MainActivity;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyAccountActivity extends BaseActivity implements VerifyAccountContract.View {
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitle;
    @BindView(R.id.edtCode)
    EditText edtCode;    @BindView(R.id.edtCode2)
    EditText edtPass;
    @BindView(R.id.tvResend)
    TextView tvResend;
    @BindView(R.id.tvCountdown)
    TextView tvCountdown;
    @BindView(R.id.msg_verify)
    TextView msg_verify;
    @BindView(R.id.scrollview)
    ScrollView scrollview;

    ProgressDialogCustom dialogLoading;
    VerifyAccountContract.Presenter presenter;
    private String phone, code;
    private int type;
    private CountDownTimer countDownTimer;
    private boolean flagResend;

    @Override
    protected int initLayout() {
        return R.layout.acitivity_verify;
    }

    @Override
    protected void initVariables() {

        tvTitle.setText("Xác thực số điện thoại");

        dialogLoading = new ProgressDialogCustom(this);
        presenter = new VerifyPresenter(this);
        type = getIntent().getExtras().getInt("type");
        phone = getIntent().getExtras().getString("phone");
        code = getIntent().getExtras().getString("code");
        msg_verify.setText(String.format(getString(R.string.msg_verify),phone));
        LogUtils.e(code);
        countDownTimer = new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCountdown.setText(" sau " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                flagResend = true;
                tvResend.setText("Gửi lại");
                tvCountdown.setVisibility(View.INVISIBLE);
            }
        };
        countDownTimer.start();
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (edtCode.getText().toString().equals(code)) {
//                    presenter.signUp(phone, code, type);
//                }
            }
        });
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @OnClick({R.id.tvResend, R.id.btnsubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvResend:
                if (flagResend)
                    presenter.sendCode(phone, type);
                break;
            case R.id.btnsubmit:
                if (edtCode.getText().toString().equals(code)) {
                    presenter.signUp(phone, edtPass.getText().toString(), type);

                } else {
                    Toast.makeText(this, "Mã xác thực không chính xác", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onSuccessSignUp() {
        Intent intent = new Intent(VerifyAccountActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onSuccessSendCode(String code) {
        this.code = code;
        countDownTimer.start();
        tvCountdown.setVisibility(View.VISIBLE);
        flagResend = false;

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
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorAuthorization() {

    }
}
