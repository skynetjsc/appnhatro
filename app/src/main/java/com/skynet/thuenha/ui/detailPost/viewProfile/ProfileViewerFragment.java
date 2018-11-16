package com.skynet.thuenha.ui.detailPost.viewProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.listviewer.ListViewerFragment;
import com.skynet.thuenha.ui.splash.SplashActivity;
import com.skynet.thuenha.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileViewerFragment extends BaseFragment implements ProfileViewerContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {

    @BindView(R.id.tvToolbar)
    TextView tvToolbar;

    @BindView(R.id.imgAvt)
    CircleImageView imgAvt;
    @BindView(R.id.imgAward)
    ImageView imgAward;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.layoutProfile)
    ConstraintLayout layoutProfile;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.layoutMyWall)
    ConstraintLayout layoutMyWall;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.layoutRoot)
    FrameLayout layoutRoot;
    Unbinder unbinder;
    private ProfileViewerContract.Presenter presenter;
    private List<Post> listPost;

    public static ProfileViewerFragment newInstance(String idHost) {

        Bundle args = new Bundle();
        args.putString(AppConstant.MSG, idHost);
        ProfileViewerFragment fragment = new ProfileViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_profile_viewer;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
        tvToolbar.setText("Cá nhân");
    }

    @Override
    protected void initVariables() {
        presenter = new ProfileViewerPresenter(this);
        presenter.getProfile(getArguments().getString(AppConstant.MSG));
    }


    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSucessGetProfile(Profile profile) {
        tvName.setText(profile.getName());
        tvAddress.setText(profile.getAddress());
        tvEmail.setText(profile.getPhone());
        if (profile.getAvatar() != null && !profile.getAvatar().isEmpty())
            Picasso.with(getContext()).load(profile.getAvatar()).fit().centerCrop().into(imgAvt);
        if (profile.getType() == 1 || profile.getPost() == null) {
            layoutMyWall.setVisibility(View.GONE);
        } else {
            listPost = profile.getPost();
            rcv.setAdapter(new AdapterPostProfileViewer(profile.getPost(), getContext(), this));
        }
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

    @Override
    public void onRefresh() {
        presenter.getProfile(getArguments().getString(AppConstant.MSG));
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(getActivity(), DetailPostActivity.class);
        i.putExtra(AppConstant.MSG, listPost.get(pos).getId());
        i.putExtra("edit", true);
        startActivityForResult(i, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void onCallBackToggle(int pos, boolean isCheck) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ListViewerFragment fragmentSearch = ListViewerFragment.newInstance(listPost.get(pos).getId());
        fragmentManager.beginTransaction().replace(R.id.layoutRoot, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        getFragmentManager().popBackStack();
    }
}
