package com.skynet.mumgo.ui.auth.verifyaccount;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.main.MainActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyAccountActivity extends BaseActivity implements VerifyAccountContract.View {
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitle;
    @BindView(R.id.txt_pin_entry)
    PinEntryEditText edtCode;
    @BindView(R.id.tvResend)
    TextView tvResend;
    @BindView(R.id.tvCountdown)
    TextView tvCountdown;


    ProgressDialogCustom dialogLoading;
    VerifyAccountContract.Presenter presenter;
    private String phone, code;
    private CountDownTimer countDownTimer;
    private boolean flagResend;

    @Override
    protected int initLayout() {
        return R.layout.activity_verify;
    }

    @Override
    protected void initVariables() {

        tvTitle.setText("Xác thực");

        dialogLoading = new ProgressDialogCustom(this);
        presenter = new VerifyPresenter(this);
        phone = getIntent().getExtras().getString("phone");
        code = getIntent().getExtras().getString("code");
        LogUtils.e(code);
        countDownTimer = new CountDownTimer(30000, 1000) {
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

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @OnClick({R.id.tvResend, R.id.btnVerify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvResend:
                if (flagResend)
                    presenter.sendCode(phone);
                break;
            case R.id.btnVerify:
                if (edtCode.getText().toString().equals(code)) {
                    presenter.signUp(phone, code);

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
