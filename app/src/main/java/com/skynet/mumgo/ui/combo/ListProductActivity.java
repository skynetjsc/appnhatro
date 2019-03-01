package com.skynet.mumgo.ui.combo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListProductActivity extends BaseActivity implements AdapterProduct.CallBackProduct, XRecyclerView.LoadingListener, ListProductContract.View {
    @BindView(R.id.imgHome)
    ImageView imgHome;

    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;

    @BindView(R.id.include4)
    ConstraintLayout include4;
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.textView52)
    TextView textView52;
    @BindView(R.id.tvOldPrice)
    TextView tvOldPrice;
    @BindView(R.id.tvPercent)
    TextView tvPercent;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    private int requestType;
    private int index = 0;
    private List<Product> list;
    private AdapterProduct adapter;
    ListProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    boolean isFirstShow = true;
    private Combo combo;

    @Override
    protected int initLayout() {
        return R.layout.activity_combo;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);

        list = new ArrayList<>();
        adapter = new AdapterProduct(list, this, this);
        rcv.setLayoutManager(new GridLayoutManager(this, 2));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adapter);
        presenter = new ListProductPresenter(this);
        onRefresh();
    }

    @Override
    protected void initViews() {

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

    @OnClick({R.id.imgHome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                onBackPressed();
                break;

        }
    }


    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(ListProductActivity.this, ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, list.get(pos).getId());
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
        requestType = TYPE_REFREESH;
        index = 0;
        presenter.getListProduct(getIntent().getIntExtra(AppConstant.MSG, 0));
//        presenter.getCart();
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        presenter.getListProduct(index);
    }

    @Override
    public void onSucessGetListProduct(Combo combo) {
        this.combo = combo;
        this.list.clear();
        this.list.addAll(combo.getList());
        adapter.notifyDataSetChanged();
        Picasso.with(this).load(combo.getImg()).fit().centerCrop().into(banner);
        tvOldPrice.setText(String.format("%,.0fvnđ", combo.getPrice()));
        tvPercent.setText("Giảm " + combo.getDiscount() + "%");


    }

    @Override
    public void onSucessGetCart(Cart cart) {
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        if (isFirstShow) {
            dialogLoading.showDialog();
            isFirstShow = false;
        }
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();

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

    @Override
    public void toggleFav(int pos, Product product, boolean toggle) {

    }

    @OnClick(R.id.btnBuy)
    public void onViewClicked() {
        if(!list.isEmpty()){
            for (Product p :list) {
                if(p.getIs_favourite()==1){
                    AppController.getInstance().getCart().getListProduct().add(p);
                    presenter.addToCart(p.getId(),p.getNumber_sale());
                }
            }
            AppController.getInstance().getCart().setFinal_price(combo.getPrice());
            Intent  i = new Intent(ListProductActivity.this,CartActivity.class);
            i.putExtra("price",combo.getPrice());
            startActivity(i);
        }
    }
}
