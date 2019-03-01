package com.skynet.mumgo.ui.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.models.Receiver;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.choosepayment.ChoosepaymetnActivity;
import com.skynet.mumgo.ui.enterpin.EnterpinActivity;
import com.skynet.mumgo.ui.views.AlertDialogCustom;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.DateTimeUtil;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutActivity extends BaseActivity implements CartContract.View {
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
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.edtName)
    TextInputEditText edtName;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputLayout2;
    @BindView(R.id.edtSdt)
    TextInputEditText edtSdt;
    @BindView(R.id.textInputLayout3)
    TextInputLayout textInputLayout3;
    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.textInputLayout4)
    TextInputLayout textInputLayout4;
    @BindView(R.id.edtAddress)
    TextInputEditText edtAddress;
    @BindView(R.id.textInputLayout5)
    TextInputLayout textInputLayout5;
    @BindView(R.id.view11)
    View view11;
    @BindView(R.id.tvStep)
    TextView tvStep;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.view12)
    View view12;
    @BindView(R.id.tvTotalPriceFooter)
    TextView tvTotalPriceFooter;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.constraintLayout5)
    ConstraintLayout constraintLayout5;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnback)
    Button btnback;
    @BindView(R.id.layoutpayment)
    ConstraintLayout layoutpayment;
    @BindView(R.id.tvTimeShip)
    TextView tvTimeShip;
    @BindView(R.id.layoutTime)
    ConstraintLayout layoutTime;
    private CartContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;

    @Override
    protected int initLayout() {
        return R.layout.activity_checkout;
    }

    @Override
    protected void initVariables() {
        presenter = new CartPresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getCart();
        tvDate.setText(DateTimeUtil.convertTimeToString(System.currentTimeMillis(), "dd/MM/yyyy"));
        Profile profile = getProfile();
        edtAddress.setText(profile.getAddress());
        edtEmail.setText(profile.getEmail());
        edtName.setText(profile.getName());
        edtSdt.setText(profile.getPhone());

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @OnClick({R.id.tvEdit, R.id.btnNext, R.id.btnback, R.id.tvEditPayment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvEdit:
                if (edtSdt.isEnabled()) {
                    tvEdit.setText("Sửa");
                    edtSdt.setEnabled(false);
                    edtName.setEnabled(false);
                    edtEmail.setEnabled(false);
                    edtAddress.setEnabled(false);
                    presenter.updateInfo(edtName.getText().toString(), edtEmail.getText().toString(), edtSdt.getText().toString(), edtAddress.getText().toString());
                } else {
                    edtSdt.setEnabled(true);
                    edtName.setEnabled(true);
                    edtEmail.setEnabled(true);
                    edtAddress.setEnabled(true);
                    tvEdit.setText("Lưu");
                }
                break;
            case R.id.btnNext:
                if (layoutpayment.getVisibility() == View.VISIBLE) {
                    startActivityForResult(new Intent(CheckoutActivity.this, EnterpinActivity.class), 1000);
                } else {
                    startActivityForResult(new Intent(CheckoutActivity.this, ChoosepaymetnActivity.class), 1000);
                }
                break;
            case R.id.tvEditPayment:
                startActivityForResult(new Intent(CheckoutActivity.this, ChoosepaymetnActivity.class), 1000);
                break;
            case R.id.btnback:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            layoutpayment.setVisibility(View.VISIBLE);
            tvStep.setText(3 + "");
            startActivityForResult(new Intent(CheckoutActivity.this, EnterpinActivity.class), 1000);
        }
    }

    @Override
    public void onSucessGetCart(Cart cart) {
        recyclerView.setAdapter(new AdapterCartCheckout(cart.getListProduct(), this));
        tvTotalPriceFooter.setText(String.format("%,.0fđ", cart.getFinal_price()));
        tvTotalPriceHeader.setText(String.format("%,.0fđ", cart.getFinal_price()));
        tvTimeShip.setText(Html.fromHtml(String.format(getString(R.string.format_time_ship_s),cart.getTime_ship())));
        Receiver receiver = cart.getReceiver();
        if (receiver != null) {
            edtName.setText(receiver.getName());
            edtSdt.setText(receiver.getPhone());
            edtAddress.setText(receiver.getAddress());
            edtEmail.setText(receiver.getEmail());
        }
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

    }

    @Override
    public void onErrorApi(String message) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onErrorAuthorization() {

    }

    @OnClick(R.id.layoutTime)
    public void onViewClicked() {
        AlertDialogCustom.showDialogDateTime(this, new AlertDialogCustom.CallBack() {
            @Override
            public void onCallBack(Calendar start) {
                tvTimeShip.setText(Html.fromHtml(String.format(getString(R.string.format_time_ship), start.get(Calendar.HOUR_OF_DAY),
                        start.get(Calendar.MINUTE), start.get(Calendar.DATE), start.get(Calendar.MONTH)+1, start.get(Calendar.YEAR))));
                presenter.updateTimeShip(String.format("%02d:%02d %02d/%02d/%d", start.get(Calendar.HOUR_OF_DAY),
                        start.get(Calendar.MINUTE), start.get(Calendar.DATE), start.get(Calendar.MONTH)+1, start.get(Calendar.YEAR)));
            }
        }).show();
    }
}
