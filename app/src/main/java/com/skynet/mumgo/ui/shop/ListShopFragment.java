package com.skynet.mumgo.ui.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.Shop;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.ui.detailshop.DetailShopActivity;
import com.skynet.mumgo.utils.AppConstant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListShopFragment extends BaseFragment implements ShopContract.View, SwipeRefreshLayout.OnRefreshListener, AdapterHotShop.ICallBackListShop {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;



    @BindView(R.id.rcvMore)
    RecyclerView rcvMore;
    @BindView(R.id.button3)
    TextView button3;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private ShopContract.Presenter presenter;
    private List<Shop> listShop;
    private List<Shop> listHotShop;
    private List<Category> listCategories;
    private FusedLocationProviderClient mFusedLocationClient;

    LatLng latLng;

    public static ListShopFragment newInstance() {
        Bundle args = new Bundle();
        ListShopFragment fragment = new ListShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcvMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }
            return;
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
    }

    @Override
    protected void initVariables() {
        presenter = new ShopPresenter(this);
        presenter.getListFriend();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    presenter.getListFriend();
                }else if(tab.getPosition() == 1){
                    if(latLng!=null){
                        presenter.getListShopNearby(latLng.latitude,latLng.longitude);
                    }
                }else{
                    presenter.getListShop(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            }
                        }
                    });
        }
    }
    @Override
    public void onSuccessGetListShop(List<Shop> list) {
        this.listShop = list;
        rcvMore.setAdapter(new AdapterHotShop(list, getContext(), this));
    }

    @Override
    public void onSuccessGetListFriendShop(List<Shop> list) {

    }

    @Override
    public void onSuccessGetListNearbyShop(List<Shop> list) {

    }


    @Override
    public Context getMyContext() {
        return getContext();
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
        showDialogExpiredToken();
    }

    @OnClick(R.id.button3)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), NearbyActivity.class));
    }

    @Override
    public void onRefresh() {
        if(tabLayout.getSelectedTabPosition()==0){
            presenter.getListFriend();
        }else if(tabLayout.getSelectedTabPosition() == 1){
            if(latLng!=null){
                presenter.getListShopNearby(latLng.latitude,latLng.longitude);
            }
        }else{
            presenter.getListShop(1);
        }
    }

    @Override
    public void onClickShop(int pos, Shop shop) {
        Intent i = new Intent(getActivity(), DetailShopActivity.class);
        i.putExtra(AppConstant.MSG, shop.getId());
        startActivity(i);
    }
}
