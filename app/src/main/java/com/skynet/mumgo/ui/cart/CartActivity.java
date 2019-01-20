package com.skynet.mumgo.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.Promotion;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.checkout.CheckoutActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.views.DialogEditProduct;
import com.skynet.mumgo.utils.AppConstant;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements AdapterCartItem.CallBackCart, SwipeRefreshLayout.OnRefreshListener, CartContract.View {
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.tvNumShop)
    TextView tvNumShop;
    @BindView(R.id.tvNumProduct)
    TextView tvNumProduct;
    @BindView(R.id.constraintLayout3)
    ConstraintLayout constraintLayout3;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.tvPriceProduct)
    TextView tvPriceProduct;
    @BindView(R.id.tvFinalPrice)
    TextView tvFinalPrice;
    @BindView(R.id.tvPricePromo)
    TextView tvPricePromo;
    @BindView(R.id.tvFee)
    TextView tvFee;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView25)
    TextView textView25;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.constraintLayout5)
    ConstraintLayout constraintLayout5;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.edtPromo)
    EditText edtPromo;
    @BindView(R.id.checkout)
    Button checkout;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Cart cart;
    List<Product> list;
    AdapterCartItem adapter;
    private Promotion promotion;
    private CartContract.Presenter presenter;
    private Timer timer;
    @Override
    protected int initLayout() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initVariables() {
        presenter = new CartPresenter(this);
//        cart = AppController.getInstance().getCart();
//        onSucessGetCart(cart);
        onRefresh();

        edtPromo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) return;
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here
                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.checkPromotion(s.toString());
                            }
                        });
                    }
                }, 600);
            }
        });
    }

    @Override
    protected void initViews() {
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
        swipe.setOnRefreshListener(this);
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

    @OnClick({R.id.imageView13, R.id.checkout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView13:
                onBackPressed();
                break;
            case R.id.checkout:
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
                break;
        }
    }

    @Override
    public void onClickDelete(int pos, Product product) {
        presenter.deleteItem(product.getProduct_id());
        adapter.remove(pos);
    }

    @Override
    public void onClickEdit(int pos, Product product) {
        new DialogEditProduct(this, product, new DialogEditProduct.DialogOneButtonClickListener() {
            @Override
            public void okClick(Product product) {
                list.set(pos, product);
                presenter.updateItem(product.getProduct_id(), product.getQuatity(), product.getNote());
                adapter.notifyItemChanged(pos);
            }
        }).show();
    }

    @Override
    public void onClickDetail(int pos, Product product) {
        Intent i = new Intent(CartActivity.this, ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, product.getProduct_id());
        startActivityForResult(i, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        presenter.getCart();
    }

    @Override
    public void onSucessGetCart(Cart cart) {
        list = cart.getListProduct();
        adapter = new AdapterCartItem(list, this, this);
        rcv.setAdapter(adapter);
        tvNumShop.setText(cart.getNumberShop() + " cửa hàng / ");
        tvNumProduct.setText(String.format("Tổng tiền %,.0fđ", cart.getTotalPrice()));
        tvPriceProduct.setText(String.format("%,.0fđ", cart.getTotalPrice()));
        tvFee.setText(String.format("%,.0fđ", cart.getFee()));
//        double total = 0;
//        total = cart.getTotalPrice() + cart.getFee();
//        if (cart.getPricePromotion() != 0) {
//            total = total - cart.getPricePromotion();
//        }else{
//            tvPricePromo.setText("0đ");
//
//        }
        tvPricePromo.setText(String.format("-%,.0fđ", cart.getPricePromotion()));
        tvFinalPrice.setText(String.format("%,.0fđ", cart.getFinal_price()));
    }

    @Override
    public void onSucessCheckPromotion(Promotion promotion) {
        this.promotion = promotion;
        onSucessGetCart(cart);
    }

    @Override
    public Context getMyContext() {
        return null;
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hiddenProgress() {
        swipe.setRefreshing(false);

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
}
