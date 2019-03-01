package com.skynet.mumgo.ui.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.ui.category.listProduct.ListProductActivity;
import com.skynet.mumgo.ui.location.LocationActivity;
import com.skynet.mumgo.ui.search.ActivitySearch;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCategoryFragment extends BaseFragment implements CategoryContract.View, SwipeRefreshLayout.OnRefreshListener, ICallback {


    TextView tvShowMore;
    @BindView(R.id.rcvMore)
    RecyclerView rcvMore;
    @BindView(R.id.button3)
    TextView button3;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private CategoryContract.Presenter presenter;

    private List<Category> listCategories;

    public static ListCategoryFragment newInstance() {
        Bundle args = new Bundle();
        ListCategoryFragment fragment = new ListCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcvMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void initVariables() {
        presenter = new CategoryPresenter(this);
        presenter.getCategory();
    }

    @Override
    public void onSuccessGetListCategory(List<Category> categories) {
        this.listCategories = categories;
       rcvMore.setAdapter(new AdapterCategory(listCategories,getContext(),this));
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
        Intent i = new Intent(getActivity(),ActivitySearch.class);
        Bundle b = new Bundle();
        b.putInt("type",ActivitySearch.TYPE_CATEGORY);
        b.putParcelableArrayList(AppConstant.MSG, (ArrayList<? extends Parcelable>) listCategories);
        i.putExtra(AppConstant.BUNDLE,b);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        presenter.getCategory();

    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), LocationActivity.class);
        i.putExtra(AppConstant.MSG,listCategories.get(pos).getId());
        i.putExtra("name",listCategories.get(pos).getName());
        startActivity(i);
    }
}
