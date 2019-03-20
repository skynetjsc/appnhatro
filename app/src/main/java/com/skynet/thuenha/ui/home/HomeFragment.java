package com.skynet.thuenha.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.Banner;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.feedback.FeedbackActivity;
import com.skynet.thuenha.ui.makepost.MakeAPostActivity;
import com.skynet.thuenha.ui.notification.NotificationActivity;
import com.skynet.thuenha.ui.privacy.HelpActivity;
import com.skynet.thuenha.ui.search.FragmentSearch;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.ui.views.SlideView;
import com.skynet.thuenha.utils.AppConstant;
import com.skynet.thuenha.utils.CommomUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.slidePhotos)
    SlideView slidePhotos;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    Unbinder unbinder;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.tvWallet)
    TextView tvWallet;
    @BindView(R.id.cardSearch)
    CardView cardSearch;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    Unbinder unbinder1;
    private OnFragmentHomeCallBack mListener;
    HomeContract.Presenter presenter;
    ProgressDialogCustom dialogLading;
    private List<Banner> listBanner;
    private List<Address> listCities;
    private List<Service> listService;
    private Address myCity, myDistrict;
    private SlidePhotoHomeAdapter adapterSlide;
    private ICallback onClickBanner = new ICallback() {
        @Override
        public void onCallBack(int pos) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(listBanner.get(pos).getUrl()));
//            startActivity(browserIntent);
        }
    };
    private ICallback onClickService = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            if (AppController.getInstance().getmProfileUser().getType() == 1) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentSearch fragmentSearch = FragmentSearch.newInstance(listService.get(pos).getId(), listService.get(pos).getName());
                fragmentManager.beginTransaction().replace(R.id.layoutRoot, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            } else {
                Intent i = new Intent(getActivity(), MakeAPostActivity.class);
                i.putExtra(AppConstant.MSG, listService.get(pos).getId());
                startActivity(i);
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.button3)
    public void onClickMakePost() {
        if (AppController.getInstance().getmProfileUser().getType() == 2) {
//            startActivity(new Intent(getActivity(), MakeAPostActivity.class));
            Intent i = new Intent(getActivity(), MakeAPostActivity.class);
            if (listService != null && listService.size() > 0)
                i.putExtra(AppConstant.MSG, listService.get(0).getId());
            startActivity(i);
        } else {
            showToast("Tài khoản của bạn không thể thực hiện chức năng này. Vui lòng đăng nhập với tư cách người cho thuê!", AppConstant.POSITIVE);
        }
    }

    @OnClick({R.id.layoutFeedback})
    public void onClickMakePost(View view) {
        startActivity(new Intent(getActivity(), FeedbackActivity.class));
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @OnClick({R.id.imageView3})
    public void onClickimageView3MakePost(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, null, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        rcv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcv.setHasFixedSize(true);
    }

    @Override
    protected void initVariables() {
        presenter = new HomePresenter(this);
        dialogLading = new ProgressDialogCustom(getMyContext());
        presenter.getHome();
        listBanner = new ArrayList<>();
        if (AppController.getInstance().getListBanner() != null)
            listBanner.addAll(AppController.getInstance().getListBanner());
        adapterSlide = new SlidePhotoHomeAdapter(slidePhotos, listBanner, onClickBanner);
        slidePhotos.setAdapter(adapterSlide);
//        setupAddress();
        if (AppController.getInstance().getmProfileUser().getType() == 2) {
            cardSearch.setVisibility(View.GONE);
        }
        String type = (AppController.getInstance().getmProfileUser().getType() == 1 ? "Người thuê" : "Chủ nhà");
        tvWallet.setText(String.format("Số dư TK: %,.0fvnđ\nTài khoản: %s", AppController.getInstance().getmProfileUser().getAccountWallet(), type));
//        tvWallet.setTextColor(Color.RED);
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAddress();
    }

    public void setupAddress() {
        if (AppController.getInstance().getmSetting().getString(AppConstant.city) != null && !AppController.getInstance().getmSetting().getString(AppConstant.city).isEmpty())
            myCity = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.city), Address.class);
        if (AppController.getInstance().getmSetting().getString(AppConstant.district) != null && !AppController.getInstance().getmSetting().getString(AppConstant.district).isEmpty())
            myDistrict = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.district), Address.class);
        if (myCity != null)
            tvCity.setText(myCity.getName());
        if (myDistrict != null)
            tvDistrict.setText(myDistrict.getName());
    }

    @Override
    public boolean getUserVisibleHint() {
        setupAddress();
        return true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentHomeCallBack) {
            mListener = (OnFragmentHomeCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentHomeCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.tvNoti)
    public void onClickSearch() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NotificationActivity fragmentSearch = NotificationActivity.newInstance();
        fragmentManager.beginTransaction().replace(R.id.layoutRoot, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
        drawerLayout.closeDrawer(Gravity.LEFT);

    }

    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onSucessGetHome(Profile profile, List<Address> addresses, List<Service> services, List<Banner> banners) {
        listBanner = banners;
        listCities = addresses;
        listService = services;
        adapterSlide.setUrlPhotos(listBanner);
        rcv.setAdapter(new AdapterService(listService, getContext(), onClickService));
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgress() {
        dialogLading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLading.hideDialog();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.nav_feedback, R.id.nav_help, R.id.nav_share})
    public void onViewClicked(View view) {
        final String appPackageName = getContext().getPackageName(); // getPackageName() from Context or Activity object

        switch (view.getId()) {
            case R.id.nav_feedback:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.nav_help:
                startActivity(new Intent(getActivity(),HelpActivity.class));
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + appPackageName);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
    }


    public interface OnFragmentHomeCallBack {
        void onFragmentInteraction(Uri uri);
    }
}
