package com.skynet.thuenha.ui.chosseAddress;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallbackObj;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.blankj.utilcode.util.Utils.getContext;

public class ChooseAddressActivity extends BaseActivity implements ChooseAddressContract.View, ICallbackObj {
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
    private AdapterDistrict adapter;


    @Override
    protected int initLayout() {
        return R.layout.activity_choose_address;
    }


    @Override
    protected void initVariables() {
        presenter = new ChooseAddressPresenter(this);
        dialogLoading = new ProgressDialogCustom(getMyContext());
        presenter.getCity();
        listDistricts = new ArrayList<>();
        adapter = new AdapterDistrict(listDistricts, this, this);
        rcv.setAdapter(adapter);

        editext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable.toString());

            }
        });
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    public void onSucessGetCities(List<Address> list) {
        listCities = list;
        spiner.setOnItemSelectedListener(null);
        spiner.setAdapter(new AdapterCity(getMyContext(), R.layout.item_city, listCities));
        Address city = new Gson().fromJson(AppController.getInstance().getmSetting().getString("city"), Address.class);
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
        spiner.performClick();

    }

    @Override
    public void onSucessGetDistrict(List<Address> list) {
        listDistricts.clear();
        listDistricts.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getMyContext() {
        return this;
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
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }


    @OnClick(R.id.tvCancle)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onCallBack(Object pos) {
        Address obj = (Address) pos;
        AppController.getInstance().getmSetting().put(AppConstant.district, new Gson().toJson(obj));
        setResult(RESULT_OK);
        finish();
    }

}
