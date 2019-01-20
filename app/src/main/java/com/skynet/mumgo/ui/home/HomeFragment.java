package com.skynet.mumgo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.models.Banner;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.News;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.models.Suggestion;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.listProduct.ListProductActivity;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;

import static cn.lightsky.infiniteindicator.IndicatorConfiguration.LEFT;

public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, OnPageClickListener {

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Unbinder unbinder;

    HomeContract.PresenterI presenter;
    @BindView(R.id.viewPager)
    cn.lightsky.infiniteindicator.InfiniteIndicator indicator;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.rcvCategory)
    RecyclerView rcvCategory;
    @BindView(R.id.cardView3)
    CardView cardView3;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.tvShowMore)
    TextView tvShowMore;
    @BindView(R.id.rcvRecommend)
    RecyclerView rcvRecommend;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.rcvMore)
    RecyclerView rcvMore;
    List<Page> listBanner;
    List<Suggestion> listRecommend;
    List<News> listNews;
    List<Category> listCategories;

    private ICallback callBackCategory = new ICallback() {
        @Override
        public void onCallBack(int pos) {

        }
    };
    private ICallback callBackRecommend = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            Intent i =new Intent(getActivity(),ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG,listRecommend.get(pos).getId());
            startActivity(i);
        }
    };
    private ICallback callBackNews = new ICallback() {
        @Override
        public void onCallBack(int pos) {

        }
    };

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcvMore.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvMore.setHasFixedSize(true);
        rcvCategory.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvRecommend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvRecommend.setHasFixedSize(true);
        rcvCategory.setHasFixedSize(true);

    }

    @Override
    protected void initVariables() {
        presenter = new HomePresenterI(this);
        listBanner = new ArrayList<>();

        // bindData();
        onRefresh();
    }


    private void bindData() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile != null) {

        } else {
            showDialogExpiredToken();
            return;
        }
    }

    private void setupChart(Profile profile) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void doAction() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onRefresh() {
//        presenter.getInfor();
        presenter.getHome();
    }

    @Override
    public void onSuccessGetInfor() {
        bindData();
    }

    @Override
    public void onSucessGetBanner(List<Banner> list) {
        listBanner.clear();
        for (Banner banner : list) {
            listBanner.add(new Page(banner.getName(), banner.getImg(), this));
        }
        IndicatorConfiguration configuration = new IndicatorConfiguration.Builder()
                .imageLoader(new PicassoLoader())
                .isStopWhileTouch(true)
                .onPageClickListener(this)
                .direction(LEFT)
                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
                .build();
        indicator.init(configuration);
        indicator.notifyDataChange(listBanner);
    }

    @Override
    public void onSucessGetCategory(List<Category> list) {
        this.listCategories = list;
        cardView3.setVisibility(View.VISIBLE);
        rcvCategory.setAdapter(new AdapterCategory(list, getContext(), callBackCategory));
    }

    @Override
    public void onSucessGetRecommend(List<Suggestion> list) {
        this.listRecommend = list;
        rcvRecommend.setAdapter(new AdapterRecommend(listRecommend,getContext(),callBackRecommend));
    }

    @Override
    public void onSucessGetNews(List<News> list) {
        this.listNews = list;
        rcvMore.setAdapter(new AdapterNewsHome(listNews,getContext(),callBackNews));
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


    @OnClick(R.id.tvShowMore)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(),ListProductActivity.class));
    }

    @Override
    public void onPageClick(int position, Page page) {

    }
}
