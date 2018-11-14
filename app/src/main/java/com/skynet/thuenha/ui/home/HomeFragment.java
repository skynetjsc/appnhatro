package com.skynet.thuenha.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.search.FragmentSearch;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.ui.views.SlideView;
import com.skynet.thuenha.utils.AppConstant;

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
    Unbinder unbinder1;
    private OnFragmentHomeCallBack mListener;
    HomeContract.Presenter presenter;
    ProgressDialogCustom dialogLading;
    private List<Banner> listBanner;
    private List<Address> listCities;
    private List<Service> listService;
    private Address myCity, myDistrict;
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
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentSearch fragmentSearch = FragmentSearch.newInstance(listService.get(pos).getId());
            fragmentManager.beginTransaction().replace(R.id.layoutRoot, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
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


    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
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
        slidePhotos.setAdapter(new SlidePhotoHomeAdapter(slidePhotos, listBanner, onClickBanner));
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

    @OnClick(R.id.cardSearch)
    public void onClickSearch() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ChooseAddressFragment fragmentSearch = ChooseAddressFragment.newInstance(myCity);
        fragmentManager.beginTransaction().replace(R.id.layoutRoot, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSucessGetHome(Profile profile, List<Address> addresses, List<Service> services, List<Banner> banners) {
        listBanner = banners;
        listCities = addresses;
        listService = services;
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


    public interface OnFragmentHomeCallBack {
        void onFragmentInteraction(Uri uri);
    }
}
