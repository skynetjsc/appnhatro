package com.skynet.mumgo.ui.choosepayment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.ui.base.BaseActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosepaymetnActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.radioPaypal)
    RadioButton radioPaypal;
    @BindView(R.id.imageView18)
    ImageView imageView18;
    @BindView(R.id.layoutPaypal)
    ConstraintLayout layoutPaypal;
    @BindView(R.id.radVia)
    RadioButton radVia;
    @BindView(R.id.layoutVisa)
    ConstraintLayout layoutVisa;
    @BindView(R.id.radPay)
    RadioButton radPay;
    @BindView(R.id.imageView20)
    ImageView imageView20;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.layoutPay)
    ConstraintLayout layoutPay;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.tvTotalPriceFooter2)
    TextView tvTotalPriceFooter2;
    @BindView(R.id.btnNext2)
    Button btnNext2;

    RadioButton oldRad;

    @Override
    protected int initLayout() {
        return R.layout.activity_choose_payment;
    }

    @Override
    protected void initVariables() {
        tvTotalPriceFooter2.setText(String.format("%,.0fđ", AppController.getInstance().getCart().getFinal_price()));
    }

    @Override
    protected void initViews() {
        radioPaypal.setOnCheckedChangeListener(this);
        radPay.setOnCheckedChangeListener(this);
        radVia.setOnCheckedChangeListener(this);
        radPay.setChecked(true);
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

    @OnClick({R.id.imgBack, R.id.btnNext2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.btnNext2:
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (oldRad != null) oldRad.setChecked(false);
        if (isChecked) {
            oldRad = (RadioButton) buttonView;
        }
    }

    @OnClick({R.id.layoutPaypal, R.id.layoutVisa, R.id.layoutPay})
    public void onViewPayClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutPaypal:
                radioPaypal.toggle();
                break;
            case R.id.layoutVisa:
                radVia.toggle();
                break;
            case R.id.layoutPay:
                radPay.toggle();
                break;
        }
    }
}
