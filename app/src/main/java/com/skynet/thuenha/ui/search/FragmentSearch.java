package com.skynet.thuenha.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.Filter;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressActivity;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.filter.FilterActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentSearch extends BaseFragment implements SearchContract.View, ICallback, OnRangeChangedListener {
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    Unbinder unbinder;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.filter)
    ImageView filter;
    @BindView(R.id.dot_filter)
    ImageView dot_filter;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.cardSearch)
    CardView cardSearch;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.rangeSeekBar)
    RangeSeekBar rangeSeekBar;
    @BindView(R.id.textView31)
    TextView textView31;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    private SearchContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private List<Post> listPost;
    private Timer timer;
    private Address myCity;
    private Address myDistrict;
    private float max, min;

    public static FragmentSearch newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("idService", id);
        FragmentSearch fragment = new FragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcv.setLayoutManager(new LinearLayoutManager(getMyContext()));
        rcv.setHasFixedSize(true);
        rangeSeekBar.setOnRangeChangedListener(this);
    }

    @Override
    protected void initVariables() {
        presenter = new SearchPresenter(this);
        dialogLoading = new ProgressDialogCustom(getMyContext());
        if (AppController.getInstance().getFilter() != null) {
            dot_filter.setVisibility(View.VISIBLE);
            presenter.getAllPostByFilter();
        } else {
            presenter.getAllPostByService(getArguments().getInt("idService"), 0);
            dot_filter.setVisibility(View.GONE);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null) {
                    timer.cancel();
                }

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.queryPostByService(getArguments().getInt("idService"), editable.toString());
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask

            }
        });
    }

    @Override
    public void onSucessGetPost(List<Post> list) {
        listPost = list;
        rcv.setAdapter(new SearchAdapter(list, getMyContext(), this));
        title.setText(Html.fromHtml(String.format(getString(R.string.tilte_search), listPost.size())));
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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
    }

    @OnClick(R.id.imageView11)
    public void onCLick() {
        tvCity.setText("Tỉnh thành");
        tvDistrict.setText("Tìm kiếm theo khu vực");
        presenter.getAllPostByService(getArguments().getInt("idService"), 0);

    }

    @OnClick({R.id.back, R.id.filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            case R.id.filter:
                startActivityForResult(new Intent(getActivity(), FilterActivity.class), 1000);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == getActivity().RESULT_OK) {
            if (AppController.getInstance().getFilter() != null) {
                dot_filter.setVisibility(View.VISIBLE);
                presenter.getAllPostByFilter();
            } else {
                dot_filter.setVisibility(View.GONE);
                presenter.getAllPostByService(getArguments().getInt("idService"), 0);

            }
        }
        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            setupAddress();
            if (AppController.getInstance().getFilter() != null) {
                dot_filter.setVisibility(View.VISIBLE);
                presenter.getAllPostByFilter();
            } else {
                dot_filter.setVisibility(View.GONE);
                presenter.getAllPostByService(getArguments().getInt("idService"), 1);

            }
        }
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
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), DetailPostActivity.class);
        i.putExtra(AppConstant.MSG, listPost.get(pos).getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.cardSearch)
    public void clickSearch() {
        startActivityForResult(new Intent(getActivity(), ChooseAddressActivity.class), 100);

    }


    @Override
    public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
        min = leftValue;
        max = rightValue;
    }

    @Override
    public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

    }

    @Override
    public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
        Filter filter = AppController.getInstance().getFilter();
        if (filter == null) filter = new Filter(0, min, max, "");
        filter.setMax(max);
        filter.setMin(min);
        AppController.getInstance().setFilter(filter);
        LogUtils.e(min + " ----------- " + max + "is from user");
        tvPrice.setText(String.format("Mức giá trung bình: %,.0fvnđ", ((min + max) / 2)));
        presenter.getAllPostByFilter();
    }
}
