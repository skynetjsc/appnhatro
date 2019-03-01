package com.skynet.mumgo.ui.favourite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallbackTwoM;
import com.skynet.mumgo.models.FavouriteItem;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.Shop;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.detailshop.DetailShopActivity;
import com.skynet.mumgo.utils.AppConstant;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends BaseFragment implements FavouriteContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imgBtn_info)
    ImageView imgBtnInfo;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.layoutRootChat)
    FrameLayout layoutRootChat;
    private List<Product> list;
    private List<Shop> listShop;
    private FavouriteContract.Presenter presenter;

    public static FavouriteFragment newInstance() {

        Bundle args = new Bundle();

        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.fragment_favourite;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this,view);
        swipe.setOnRefreshListener(this);
        rcv.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        rcv.setHasFixedSize(true);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onRefresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected void initVariables() {
        presenter = new FavouritePresenter(this);
        swipe.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void doAction() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSucessGetList(FavouriteItem list) {

        this.list = list.getProducts();
        this.listShop = list.getShops();
        if(tab.getSelectedTabPosition() == 0) {
            rcv.setLayoutManager(new LinearLayoutManager(getContext()));
            rcv.setAdapter(new AdapterShopFavourite(this.listShop, getMyContext(), this));
        }else {
            rcv.setLayoutManager(new GridLayoutManager(getContext(),2));

            rcv.setAdapter(new AdapterFavourite(this.list, getMyContext(), this));
        }
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

    @Override
    public void onDestroy() {
        presenter.onDestroyView();

        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        presenter.getList();
    }

    @Override
    public void onCallBack(int pos) {
        Intent i;
        if(tab.getSelectedTabPosition() == 0) {
           i = new Intent(getActivity(),DetailShopActivity.class);
           i.putExtra(AppConstant.MSG,listShop.get(pos).getShop_id());

        }else {
            i = new Intent(getActivity(),ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG,list.get(pos).getProduct_id());

        }
        startActivity(i);
    }

    @Override
    public void onCallBackToggle(int pos, boolean isCheck) {
        presenter.toggleFav(list.get(pos).getId(), isCheck);
    }

}
