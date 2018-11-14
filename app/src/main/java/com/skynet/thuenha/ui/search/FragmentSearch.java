package com.skynet.thuenha.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentSearch extends BaseFragment implements SearchContract.View, ICallback {
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
    private SearchContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private List<Post> listPost;
    private Timer timer;

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
    }

    @Override
    protected void initVariables() {
        presenter = new SearchPresenter(this);
        dialogLoading = new ProgressDialogCustom(getMyContext());
        presenter.getAllPostByService(getArguments().getInt("idService"));
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
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
    }

    @OnClick({R.id.back, R.id.filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            case R.id.filter:
                break;
        }
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), DetailPostActivity.class);
        i.putExtra(AppConstant.MSG, listPost.get(pos).getId());
        startActivity(i);
    }
}
