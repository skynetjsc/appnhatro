package com.skynet.thuenha.ui.chosseAddress;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.home.HomeFragment;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseAddressFragment extends BaseFragment implements ChooseAddressContract.View, ICallback {
    @BindView(R.id.spiner)
    Spinner spiner;
    @BindView(R.id.editext)
    EditText editext;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    Unbinder unbinder;
    List<Address> listCities;
    List<Address> listDistricts;

    private ChooseAddressContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private CallBackChooseAddress mListener;

    public static ChooseAddressFragment newInstance(Address city) {

        Bundle args = new Bundle();
        args.putParcelable(AppConstant.MSG, city);
        ChooseAddressFragment fragment = new ChooseAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_choose_address;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
    }

    @Override
    protected void initVariables() {
        presenter = new ChooseAddressPresenter(this);
        dialogLoading = new ProgressDialogCustom(getMyContext());
        presenter.getCity();
    }

    @Override
    public void onSucessGetCities(List<Address> list) {
        listCities = list;
        spiner.setOnItemSelectedListener(null);
        spiner.setAdapter(new AdapterCity(getMyContext(), R.layout.item_city, listCities));
        Address city = getArguments().getParcelable(AppConstant.MSG);
        if (city != null) {
            for (int i = 0; i < listCities.size(); i++) {
                if (listCities.get(i).getId() == city.getId()) {
                    spiner.setSelection(i);
                    break;
                }
            }
        }
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getDistrict(listCities.get(position).getId());
                AppController.getInstance().getmSetting().put(AppConstant.city, new Gson().toJson(listCities.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSucessGetDistrict(List<Address> list) {
        listDistricts = list;
        rcv.setAdapter(new AdapterDistrict(list, getContext(), this));
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgress() {
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);

    }

    @Override
    public void onErrorAuthorization() {

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
        presenter.onDestroyView();
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvCancle)
    public void onViewClicked() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onCallBack(int pos) {
        AppController.getInstance().getmSetting().put(AppConstant.district, new Gson().toJson(listDistricts.get(pos)));
        mListener.onChooseAddress();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallBackChooseAddress) {
            mListener = (CallBackChooseAddress) context;
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

    public interface CallBackChooseAddress {
        void onChooseAddress();
    }
}
