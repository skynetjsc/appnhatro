package com.skynet.thuenha.ui.filter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Filter;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.makepost.AdapterService;
import com.skynet.thuenha.ui.makepost.AdapterUtility;
import com.skynet.thuenha.ui.makepost.MakeAPostContract;
import com.skynet.thuenha.ui.makepost.MakeAPostPresenter;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity implements FilterContract.View, OnRangeChangedListener {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.rcvTypeService)
    RecyclerView rcvTypeService;
    @BindView(R.id.rangeSeekBar)
    RangeSeekBar rangeSeekBar;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.rcvUtility)
    RecyclerView rcvUtility;
    private List<Service> listServices;
    private List<Utility> listUtilities;
    private List<Utility> listUtilityRequest = new ArrayList<>();
    FilterContract.Presenter presenter;
    ProgressDialogCustom dialogLoading;
    private AdapterService adapterService;
    private AdapterUtility adapterUtility;
    private float min, max;
    private Filter oldFilter;

    @Override
    protected int initLayout() {
        return R.layout.activity_filter;
    }

    @Override
    protected void initVariables() {
        presenter = new FilterPresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getService();
        presenter.getUtility();
        oldFilter = AppController.getInstance().getFilter();
        if (oldFilter != null) {
            rangeSeekBar.setValue(oldFilter.getMin() < 500000 ? 500000 : oldFilter.getMin(), oldFilter.getMax() < 500000 ? 500000 : oldFilter.getMax());
        }
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        tvToolbar.setText("Sử dụng bộ lọc");
        rcvTypeService.setLayoutManager(new GridLayoutManager(this, 3));
        rcvTypeService.setHasFixedSize(true);
        rcvUtility.setLayoutManager(new GridLayoutManager(this, 2));
        rcvUtility.setHasFixedSize(true);
        rangeSeekBar.setOnRangeChangedListener(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @OnClick({R.id.btn_back, R.id.button4, R.id.btn_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.button4:
                int idService = 0;
                String idUtiliy = "";

                if (listServices != null && listServices.size() > 0 && adapterService != null) {
                    for (Service s : listServices) {
                        if (s.isChecked()) {
                            idService = s.getId();
                        }
                    }
                }
                if (listUtilities != null && listUtilities.size() > 0 && adapterService != null) {
                    for (Utility s : listUtilities) {
                        if (s.isChecked()) {
                            idUtiliy += s.getId() + ",";
                        }
                    }
                    if (!idUtiliy.isEmpty())
                        idUtiliy = idUtiliy.substring(0, idUtiliy.length() - 1);
                }
                AppController.getInstance().setFilter(new Filter(idService, min, max, idUtiliy));
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.btn_refresh:
                if (listServices != null && listServices.size() > 0 && adapterService != null) {
                    for (Service s : listServices) {
                        s.setChecked(false);
                    }
                    adapterService.updateAdapter();
                }
                if (listUtilities != null && listUtilities.size() > 0 && adapterService != null) {
                    for (Utility s : listUtilities) {
                        s.setChecked(false);
                    }
                    adapterUtility.updateAdapter();
                }

                rangeSeekBar.setValue(500000, 500000);
                tvPrice.setText("Mức giá trung bình: Tất cả");
                AppController.getInstance().setFilter(null);
                showToast("Đã loại bỏ bộ lọc. Vui lòng quay lại!", AppConstant.POSITIVE);
                setResult(RESULT_OK);
                break;
        }
    }

    @Override
    public void onSucessGetService(List<Service> list) {
        this.listServices = list;
        if (listServices.size() > 0 && oldFilter != null &&
                oldFilter.getIdService() != -1) {
            for (Service s : listServices) {
                if (s.getId() == oldFilter.getIdService()) {
                    s.setChecked(true);
                    break;
                }
            }
        }
        adapterService = new AdapterService(this.listServices, this,null);
        rcvTypeService.setAdapter(adapterService);
    }

    @Override
    public void onSucessGetUtility(List<Utility> list) {
        this.listUtilities = list;
        if (listUtilities != null && oldFilter != null
                && oldFilter.getListIdUtility() != null
                && !oldFilter.getListIdUtility().isEmpty()) {
            String id[] = oldFilter.getListIdUtility().split(",");
            for (Utility utility : this.listUtilities) {
                for (int i = 0; i < id.length; i++) {
                    if (Integer.parseInt(id[i]) == utility.getId()) {
                        utility.setChecked(true);
                        break;
                    }
                }
            }
        }
        adapterUtility = new AdapterUtility(this.listUtilities, this);
        rcvUtility.setAdapter(adapterUtility);
    }

    @Override
    public void onSucessSubmitPost(int idPost) {
    }

    @Override
    public void onSucessGetPriceService(double price) {
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
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }

    @Override
    public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
        LogUtils.e(leftValue + " ----------- " + rightValue + "is from user" + isFromUser);
        min = leftValue;
        max = rightValue;
        tvPrice.setText(String.format("Mức giá trung bình: %,.0fvnđ", ((leftValue + rightValue) / 2)));
    }

    @Override
    public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

    }

    @Override
    public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

    }
}
