package com.skynet.mumgo.ui.detailProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.jaeger.library.StatusBarUtil;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.detailshop.DetailShopActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityDetailProduct extends BaseActivity implements DetailProductContract.View {
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgCart)
    ImageView imgCart;
    @BindView(R.id.tvBuyleast)
    TextView tvBuyleast;
    @BindView(R.id.tvPriceDisplay)
    TextView tvPriceDisplay;
    @BindView(R.id.tvShipTime)
    TextView tvShipTime;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.imgShop)
    ImageView imgShop;
    @BindView(R.id.tvNameShop)
    TextView tvNameShop;
    @BindView(R.id.tvIntroShop)
    TextView tvIntroShop;
    @BindView(R.id.cbShop)
    CheckBox cbShop;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvMadein)
    TextView tvMadein;
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.btnDown)
    ImageView btnDown;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;
    @BindView(R.id.btnUp)
    ImageView btnUp;
    @BindView(R.id.imgNew)
    ImageView imgNew;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tvTotalQuantity)
    TextView tvTotalQuantity;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.edtNote)
    EditText edtNote;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.btnAddToCard)
    Button btnAddToCard;
    @BindView(R.id.view8)
    FrameLayout view8;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout3)
    AppBarLayout appBarLayout3;
    @BindView(R.id.scrollview)
    NestedScrollView scrollview;
    @BindView(R.id.tvNumberProductInCart)
    TextView tvNumberProductInCart;
    @BindView(R.id.layoutbottomCart)
    ConstraintLayout layoutbottomCart;

    private int quatity = 0, unit = 0;
    private double price = 0;
    private Product product;
    private DetailProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoaing;

    @Override
    protected int initLayout() {
        StatusBarUtil.setTransparent(this);
        return R.layout.activity_detail_product;
    }

    @Override
    protected void initVariables() {
        presenter = new DetailProductPresenter(this);
        dialogLoaing = new ProgressDialogCustom(this);
        presenter.getProduct(getIntent().getIntExtra(AppConstant.MSG, 0));
        presenter.getCart();
        cbShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.toggleFav(product.getShop().getId(), isChecked);
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppController.getInstance().getCart() != null && AppController.getInstance().getCart().getListProduct() != null && !AppController.getInstance().getCart().getListProduct().isEmpty()) {
            imgNew.setVisibility(View.VISIBLE);
        } else {
            imgNew.setVisibility(View.GONE);
        }
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

    private void exceute(int i) {
        quatity += i;
        if (quatity < 0) quatity = 0;
        price = quatity * product.getPrice();
        tvQuantity.setText(quatity + "");
        tvTotalQuantity.setText((quatity * product.getMin_buy()) + product.getName_unit());
        tvTotalPrice.setText(String.format("%,.0fđ", price));
    }

    @OnClick({R.id.imgBack, R.id.imgCart, R.id.btnDown, R.id.btnUp, R.id.btnBuy, R.id.btnAddToCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgCart:
                startActivityForResult(new Intent(ActivityDetailProduct.this, CartActivity.class), 1000);
                break;
            case R.id.btnDown:
                exceute(-1);
                break;
            case R.id.btnUp:
                exceute(1);
                break;
            case R.id.btnBuy:
                if (quatity == 0) return;
                presenter.addToCart(product.getId(), quatity, edtNote.getText().toString(), true);
                setResult(RESULT_OK);
                break;
            case R.id.btnAddToCard:
                if (quatity == 0) return;
                presenter.addToCart(product.getId(), quatity, edtNote.getText().toString(), false);
                setResult(RESULT_OK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            presenter.getCart();
        }
    }

    @Override
    public void onSucessGetProduct(Product shopDetail) {
        this.product = shopDetail;
        tvName.setText(product.getName());
        tvPrice.setText(String.format("%,.0fđ", product.getPrice()));
        tvUnit.setText(product.getMin_buy() + product.getName_unit());
        tvContent.setText(product.getContent());
        tvMadein.setText(Html.fromHtml(String.format(getString(R.string.madein), product.getOrigin())));
        tvBuyleast.setText(Html.fromHtml(String.format(getString(R.string.buylease), product.getMin_buy() + product.getName_unit())));
        tvPriceDisplay.setText(Html.fromHtml(getString(R.string.pricedisplay)));
        tvShipTime.setText(Html.fromHtml(String.format(getString(R.string.shiptime), product.getTime_ship())));
        if (product.getImg() != null && !product.getImg().isEmpty()) {
            Picasso.with(this).load(product.getImg()).fit().centerCrop().into(imgCover);
        }
        if (product.getShop() != null) {
            if (product.getShop().getAvatar() != null && !product.getShop().getAvatar().isEmpty()) {
                Picasso.with(this).load(product.getShop().getAvatar()).fit().centerCrop().into(imgShop);
            }
            tvNameShop.setText(product.getShop().getName());
            tvIntroShop.setText(product.getShop().getAddress());
            cbShop.setChecked(product.getShop().getIs_favourite() == 1);
        }
    }

    @Override
    public void onSucessGetCart(List<Product> list, boolean move) {
        setResult(RESULT_OK);
        if (list.size() > 0) {
            imgNew.setVisibility(View.VISIBLE);
            layoutbottomCart.setVisibility(View.VISIBLE);
        } else {
            imgNew.setVisibility(View.GONE);
            layoutbottomCart.setVisibility(View.GONE);
        }
        tvNumberProductInCart.setText(list.size()+"");

        if (move) startActivity(new Intent(ActivityDetailProduct.this, CartActivity.class));
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogLoaing.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoaing.hideDialog();
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

    @OnClick({R.id.tvName, R.id.tvAddress, R.id.imgShop})
    public void onViewSHopClicked(View view) {
        Intent i = new Intent(ActivityDetailProduct.this, DetailShopActivity.class);
        i.putExtra(AppConstant.MSG, product.getShop_id());
        startActivity(i);
    }

    @OnClick(R.id.layoutbottomCart)
    public void onViewClicked() {
        startActivityForResult(new Intent(ActivityDetailProduct.this, CartActivity.class), 1000);

    }
}