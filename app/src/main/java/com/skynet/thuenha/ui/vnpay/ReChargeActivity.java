package com.skynet.thuenha.ui.vnpay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.SnackBarCallBack;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReChargeActivity extends BaseActivity implements RechargeContract.View, FragmentWeb.OnCallbackWebView {
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.tvOption1)
    TextView tvOption1;
    @BindView(R.id.tvOption2)
    TextView tvOption2;
    @BindView(R.id.tvOption3)
    TextView tvOption3;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.editText)
    EditText editText;    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.btnNext)
    Button btnNext;

    public void tranToFragment(Fragment listProductsFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.layoutRootRecharge, listProductsFragment, listProductsFragment.getTag()).commit();
    }


    private RechargeContract.Presenter presenter;
    private ProgressDialogCustom dialog;

    @Override
    protected int initLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initVariables() {
        tvTitleToolbar.setText("NẠP TIỀN VỚI VNPAY");
        presenter = new RechargePresenter(this);

    }

    @Override
    protected void initViews() {
        dialog = new ProgressDialogCustom(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return R.id.layoutRootRecharge;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgBtn_back_toolbar, R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.btnNext, R.id.tvNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_back_toolbar:
                onBackPressed();
                break;
            case R.id.tvOption1:
                editText.setText("10000");
                break;
            case R.id.tvOption2:
                editText.setText("50000");
                break;
            case R.id.tvOption3:
                editText.setText("100000");
                break;
            default:
                presenter.doPayment(editText.getText().toString(),editText2.getText().toString());
                break;
        }
    }

    @Override
    public void onSuccessGetPaymenConfirm(String url) {
        showToast("Tiếp tục thanh toán với VNPAY, vui lòng đợi ...", AppConstant.POSITIVE);
        tranToFragment(FragmentWeb.newInstance(url));
    }

    @Override
    public void onSucessPaid() {
        setResult(RESULT_OK);
        showToast("Nạp tiền thành công.Số dư Tài khoản đã được cập nhật", AppConstant.POSITIVE, new SnackBarCallBack() {
            @Override
            public void onClosedSnackBar() {
              //  finish();
            }
        });

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialog.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialog.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }

    @Override
    public void onURLCallback(String url) {
//        getSupportFragmentManager().popBackStack();
        presenter.updateAmount(url);
//        http://45.118.145.57/paymentProcess?paymentId=PAYID-LSUBPXY9L6984631E214784V&token=EC-3N021161YB144592Y&PayerID=6DBT8WLWS3BWS
    }
}
