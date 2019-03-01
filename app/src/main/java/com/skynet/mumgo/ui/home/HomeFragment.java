package com.skynet.mumgo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.ICallback;
import com.skynet.mumgo.interfaces.ICallbackT;
import com.skynet.mumgo.models.Banner;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.Combo;
import com.skynet.mumgo.models.News;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.models.Suggestion;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.ui.cart.CartActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.listProduct.ListProductActivity;
import com.skynet.mumgo.ui.main.MainActivity;
import com.skynet.mumgo.ui.market.ListMarketActivity;
import com.skynet.mumgo.ui.scanqr.ScannerQr;
import com.skynet.mumgo.ui.search.ActivitySearch;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;

import static cn.lightsky.infiniteindicator.IndicatorConfiguration.LEFT;

public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, XRecyclerView.LoadingListener, OnPageClickListener, AdapterMoreProduct.ICallBackListProduct {

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Unbinder unbinder;

    HomeContract.PresenterI presenter;
    @BindView(R.id.viewPager)
    InfiniteIndicator indicator;

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
    XRecyclerView rcvMore;
    List<Page> listBanner;
    @BindView(R.id.scrollUp)
    FloatingActionButton scrollUp;
    @BindView(R.id.nestscroll)
    NestedScrollView nestscroll;
    private List<Page> listBannerCombo;
    List<Product> listRecommend;
    List<News> listNews;
    List<Category> listCategories;
    @BindView(R.id.rcvCateBanner)
    RecyclerView rcvCateBanner;
    @BindView(R.id.rcvCateChild)
    RecyclerView rcvCateChild;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.viewPagerCombo)
    InfiniteIndicator viewPagerCombo;
    @BindView(R.id.layoutholdercate)
    ConstraintLayout layoutholdercate;
    @BindView(R.id.rcvHot)
    RecyclerView rcvHot;
    @BindView(R.id.rcvPayment)
    RecyclerView rcvPayment;
    private int requestType;
    private List<Product> list;
    private AdapterMoreProduct adapter;
    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;
    private ICallbackT<Category> callBackCategory = new ICallbackT<Category>() {
        @Override
        public void onCallBack(int pos, Category category) {
            Intent i = new Intent(getActivity(), ListMarketActivity.class);
            i.putExtra(AppConstant.MSG, category.getId());
            i.putExtra("name", category.getName());
            startActivity(i);
        }
    };
    private ICallback callBackRecommend = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            Intent i = new Intent(getActivity(), ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG, listRecommend.get(pos).getId());
            startActivity(i);
        }
    };
    private ICallback callBackNews = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            Intent i = new Intent(getActivity(), ActivityDetailNews.class);
            Bundle b = new Bundle();
            b.putParcelable(AppConstant.MSG, listNews.get(pos));
            i.putExtra(AppConstant.BUNDLE, b);
            startActivity(i);
        }
    };
    private int index = 0;
    private List<Combo> listCombo;

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
        rcvMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvMore.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new AdapterMoreProduct(list, getContext(), this);
        rcvMore.setPullRefreshEnabled(false);
        rcvMore.setLoadingMoreEnabled(false);
        rcvMore.setAdapter(adapter);
        rcvMore.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rcvMore.setRefreshProgressStyle(ProgressStyle.BallPulse);
        rcvMore.setLimitNumberToCallLoadMore(10);
        rcvMore.setLoadingListener(this);
        rcvCategory.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcvRecommend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvRecommend.setHasFixedSize(true);
        rcvCategory.setHasFixedSize(true);
        rcvCateBanner.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcvCateChild.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcvCateChild.setHasFixedSize(true);
        rcvCateBanner.setHasFixedSize(true);
        rcvHot.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvHot.setHasFixedSize(true);
        rcvPayment.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvPayment.setHasFixedSize(true);
//        rcvMore.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (isAtBottom(rcvMore)) {
//                    scrollUp.setVisibility(View.GONE);
//                } else if (dy < 0 && scrollUp.getVisibility() == View.GONE) {
//                    scrollUp.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    public static boolean isAtBottom(RecyclerView recyclerView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return isAtBottomBeforeIceCream(recyclerView);
        } else {
            return !ViewCompat.canScrollVertically(recyclerView, 1);
        }
    }

    private static boolean isAtBottomBeforeIceCream(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int count = recyclerView.getAdapter().getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int pos = linearLayoutManager.findLastVisibleItemPosition();
            int lastChildBottom = linearLayoutManager.findViewByPosition(pos).getBottom();
            if (lastChildBottom == recyclerView.getHeight() - recyclerView.getPaddingBottom() && pos == count - 1)
                return true;
        }
        return false;
    }

    @Override
    protected void initVariables() {
        presenter = new HomePresenterI(this);
        listBanner = new ArrayList<>();
        listBannerCombo = new ArrayList<>();

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
        requestType = TYPE_REFREESH;
        index = 0;
        presenter.getListProduct(index);
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        presenter.getListProduct(index);
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
    public void onSucessGetBannerCombo(List<Banner> list) {
//        listBannerCombo.clear();
//        for (Banner banner : list) {
//            listBannerCombo.add(new Page(banner.getName(), banner.getImg(), this));
//        }
//        IndicatorConfiguration configurationCombo = new IndicatorConfiguration.Builder()
//                .imageLoader(new PicassoLoader())
//                .isStopWhileTouch(true)
//                .onPageClickListener(this)
//                .direction(LEFT)
//                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
//                .build();
//        viewPagerCombo.init(configurationCombo);
//        viewPagerCombo.notifyDataChange(listBannerCombo);
    }

    @Override
    public void onSucessGetCategory(List<Category> list) {
        this.listCategories = list;
        rcvCateChild.setAdapter(new AdapterCategory(list, getContext(), callBackCategory));

    }

    @Override
    public void onSucessGetCategoryParent(List<Category> list) {
        cardView3.setVisibility(View.VISIBLE);
        rcvCategory.setAdapter(new AdapterCategoryParent(list, getContext(), callBackCategory));
    }

    @Override
    public void onSucessGetCategoryHeader(List<Category> list) {
        rcvCateBanner.setAdapter(new AdapterCategory(list, getContext(), callBackCategory));
    }

    @Override
    public void onSucessGetRecommend(List<Product> list) {
        this.listRecommend = list;
        rcvRecommend.setAdapter(new AdapterRecommend(listRecommend, getContext(), callBackRecommend));
        rcvHot.setAdapter(new AdapterRecommend(listRecommend, getContext(), callBackRecommend));
//        rcvMore.setAdapter(new AdapterRecommend(listRecommend, getContext(), callBackRecommend));
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSucessGetListMoreProduct(List<Combo> list,int index) {
        this.listCombo = list;
        listBannerCombo.clear();
        for (Combo banner : list) {
            listBannerCombo.add(new Page(banner.getName(), banner.getImg(), this));
        }
        IndicatorConfiguration configurationCombo = new IndicatorConfiguration.Builder()
                .imageLoader(new PicassoLoader())
                .isStopWhileTouch(true)
                .onPageClickListener(this)
                .direction(LEFT)
                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
                .build();
        viewPagerCombo.init(configurationCombo);
        viewPagerCombo.notifyDataChange(listBannerCombo);
//        if (requestType == TYPE_REFREESH) {
//            this.list.clear();
//        }
//        if (!list.isEmpty()) {
////            this.list.addAll(list);
////            adapter.notifyDataSetChanged();
//        } else {
//            rcvMore.setNoMore(true);
//        }
//        this.index = index;
//        rcvMore.loadMoreComplete();
//        rcvMore.refreshComplete();


    }

    @Override
    public void onSucessGetNews(List<News> list) {
        this.listNews = list;
        rcvPayment.setAdapter(new AdapterNewsHome(listNews, getContext(), callBackNews));
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
        startActivity(new Intent(getActivity(), ListProductActivity.class));
    }

    @Override
    public void onPageClick(int position, Page page) {
        if(listCombo != null && !listCombo.isEmpty()){
            Intent i = new Intent(getActivity(), com.skynet.mumgo.ui.combo.ListProductActivity.class);
            i.putExtra(AppConstant.MSG, listCombo.get(position).getId());
            startActivity(i);
        }
    }

    @Override
    public void onClickProduct(int pos, Product shop) {
        Intent i = new Intent(getActivity(), ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, shop.getId());
        startActivity(i);
    }

    @OnClick({R.id.imageView5, R.id.imgCart2, R.id.imageView21, R.id.editText7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView5:
                ((MainActivity) getActivity()).openMenu();
                break;
            case R.id.imgCart2:
                startActivity(new Intent(getActivity(), CartActivity.class));
                break;
            case R.id.imageView21:
                startActivity(new Intent(getActivity(), ScannerQr.class));

                break;
            case R.id.editText7:
                Intent i = new Intent(getActivity(), ActivitySearch.class);
                Bundle b = new Bundle();
                b.putInt("type", ActivitySearch.TYPE_PRODUCT);
                b.putParcelableArrayList(AppConstant.MSG, (ArrayList<? extends Parcelable>) list);
                i.putExtra(AppConstant.BUNDLE, b);
                startActivity(i);
                break;
        }
    }

    @OnClick(R.id.scrollUp)
    public void onViewScrollClicked() {
        nestscroll.scrollTo(0, 0);
    }
}