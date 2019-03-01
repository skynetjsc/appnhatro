package com.skynet.mumgo.ui.market;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Cart;
import com.skynet.mumgo.models.Market;
import com.skynet.mumgo.models.MyPlace;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.location.LocationActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMarketActivity extends BaseActivity implements AdapterProduct.CallBackProduct, XRecyclerView.LoadingListener, ListProductContract.View {
    @BindView(R.id.imgHome)
    ImageView imgHome;

    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.rcv)
    XRecyclerView rcv;
    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;

    private int requestType;
    private int index = 0;
    private List<Market> list;
    private AdapterProduct adapter;
    ListProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    boolean isFirstShow = true;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng myLatlng;

    @Override
    protected int initLayout() {
        return R.layout.activity_market;
    }

    @Override
    protected void initVariables() {

        dialogLoading = new ProgressDialogCustom(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        rcv.setLayoutManager(layoutManager);
        rcv.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new AdapterProduct(list, this, this);
        rcv.setPullRefreshEnabled(true);
        rcv.setLoadingMoreEnabled(true);
        rcv.setAdapter(adapter);
        rcv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rcv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        rcv.setLimitNumberToCallLoadMore(10);
        rcv.setLoadingListener(this);
        presenter = new ListProductPresenter(this);
        if (AppController.getInstance().getmSetting().getString("latlng") != null) {
            myLatlng = new Gson().fromJson(AppController.getInstance().getmSetting().getString("latlng"), LatLng.class);
        }
        onRefresh();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                            AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                            onRefresh();
                        }
                    }
                });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                                AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                                onRefresh();
                            }
                        }
                    });
        }
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
    public void toggleFav(int pos, Product product, boolean toggle) {
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = getIntent();
        i.setClass(ListMarketActivity.this, LocationActivity.class);
        i.putExtra("market", list.get(pos).getId());
        if (AppController.getInstance().getCart().getId_market() != list.get(pos).getId()) {
            if (!AppController.getInstance().getCart().getListProduct().isEmpty()) {
                new MaterialDialog.Builder(this).title("Thông báo")
                        .content("Bạn cần thanh toán tiền ở trong giỏ trước khi chuyển sang mua ở chợ khác!")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivity(new Intent(ListMarketActivity.this, CartActivity.class));
                            }
                        })
                        .positiveText("Thanh toán ngay")
                        .negativeText("Xoá giỏ hàng")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                AppController.getInstance().getCart().getListProduct().clear();
                            }
                        }).build().show();
                return;
            }
        }
        AppController.getInstance().getCart().setId_market(list.get(pos).getId());
        startActivityForResult(i, 1000);
        finish();
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
        if (myLatlng != null) {
            presenter.getListProduct(myLatlng.latitude, myLatlng.longitude);
        } else {
            presenter.getListProduct(myLatlng.latitude, myLatlng.longitude);
        }
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
    }

    @Override
    public void onSucessGetListProduct(List<Market> list) {
        if (requestType == TYPE_REFREESH) {
            this.list.clear();
        }
        if (!list.isEmpty()) {
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            rcv.setNoMore(true);
        }
        this.index = index;
        rcv.loadMoreComplete();
        rcv.refreshComplete();
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
}
