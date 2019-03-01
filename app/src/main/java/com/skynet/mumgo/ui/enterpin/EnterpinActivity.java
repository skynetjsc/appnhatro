package com.skynet.mumgo.ui.enterpin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.main.MainActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.DateTimeUtil;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpinActivity extends BaseActivity implements PurchaseContract.View {
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imageView10)
    View imageView10;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTotalPriceHeader)
    TextView tvTotalPriceHeader;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.constraintLayout6)
    ConstraintLayout constraintLayout6;
    @BindView(R.id.imageView17)
    ImageView imageView17;
    @BindView(R.id.textView42)
    TextView textView42;
    @BindView(R.id.layoutPin)
    ConstraintLayout layoutpayment;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnback)
    Button btnback;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView43)
    TextView textView43;
    @BindView(R.id.btnHome)
    Button btnHome;
    @BindView(R.id.btnAgain)
    Button btnAgain;
    @BindView(R.id.btnbackResult)
    Button btnbackResult;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.layoutResult)
    ConstraintLayout layoutResult;

    private PurchaseContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private String verifyCode = "";

    @Override
    protected int initLayout() {
        return R.layout.activity_enterpin;
    }

    @Override
    protected void initVariables() {
        tvDate.setText(DateTimeUtil.convertTimeToString(System.currentTimeMillis(), "dd/MM/yyyy"));
        tvTotalPriceHeader.setText(String.format("%,.0fđ", AppController.getInstance().getCart().getFinal_price()));
        presenter = new PurchasePresenter(this);
        presenter.book();
    }

    @Override
    protected void initViews() {
        dialogLoading = new ProgressDialogCustom(this);
        layoutpayment.setVisibility(View.GONE);
        layoutResult.setVisibility(View.GONE);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnNext, R.id.btnback,R.id.btnHome,R.id.btnbackResult,R.id.btnAgain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                if (verifyCode.equals(editText.getText().toString()) && !verifyCode.isEmpty()) {
                    presenter.book();
                }
                break;
            case R.id.btnback:
                onBackPressed();
                break;
            case R.id.btnbackResult:
                onBackPressed();
                break;
            case R.id.btnHome:
                setResult(Activity.RESULT_CANCELED);
                Intent intent = new Intent(EnterpinActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                AppController.getInstance().setCart(new Cart());
                startActivity(intent);
//                finishAffinity();
                break;
            case R.id.btnAgain:
                layoutResult.setVisibility(View.GONE);
                layoutpayment.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onSucessBooking() {
        layoutpayment.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);
        btnHome.setVisibility(View.VISIBLE);
        btnAgain.setVisibility(View.GONE);
        btnbackResult.setVisibility(View.GONE);
        tvResult.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.ic_sucess),null,null);
    }

    @Override
    public void onSucessSendPin(String code) {
        this.verifyCode = code;
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
        layoutpayment.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);
        tvResult.setText(message);
        btnHome.setVisibility(View.GONE);
        tvResult.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.ic_fail),null,null);

    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        layoutpayment.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);
        tvResult.setText(message);
        btnHome.setVisibility(View.GONE);
        tvResult.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.ic_fail),null,null);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }
}
