package com.skynet.thuenha.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallbackTwoM;
import com.skynet.thuenha.models.Post;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.listviewer.ListViewerFragment;
import com.skynet.thuenha.ui.search.FragmentSearch;
import com.skynet.thuenha.ui.splash.SplashActivity;
import com.skynet.thuenha.ui.updateProfile.ProfileUpdateFragment;
import com.skynet.thuenha.ui.views.AlertDialogCustom;
import com.skynet.thuenha.ui.views.DialogInput;
import com.skynet.thuenha.ui.views.DialogOneButtonUtil;
import com.skynet.thuenha.ui.views.DialogTwoButtonUtil;
import com.skynet.thuenha.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment implements ProfileContract.View, SwipeRefreshLayout.OnRefreshListener, ICallbackTwoM {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.imageView10)
    ImageView imageView10;
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
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.tvWallet)
    TextView tvWallet;
    @BindView(R.id.tvInput)
    TextView tvInput;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.layoutMyWall)
    ConstraintLayout layoutMyWall;
    //    @BindView(R.id.scroll)
//    NestedScrollView scroll;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private ProfileContract.Presenter presenter;
    private List<Post> listPost;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcv.setHasFixedSize(true);
        tvToolbar.setText("Cá nhân");
    }

    @Override
    protected void initVariables() {
        presenter = new ProfilePresenter(this);
        presenter.getProfile();
    }


    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
    }

    @OnClick({R.id.layoutProfile, R.id.tvInput, R.id.btnLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutProfile:
                startActivityForResult(new Intent(getActivity(), ProfileUpdateFragment.class), 1000);
                break;
            case R.id.tvInput:
                String content = "";
                if (AppController.getInstance().getmProfileUser().getType() == 1) {
                    content = "NGUOITHUE " + AppController.getInstance().getmProfileUser().getPhone();
                } else {
                    content = "CHUNHA " + AppController.getInstance().getmProfileUser().getPhone();

                }
                new DialogInput(getContext(), R.drawable.ic_question, "HƯỚNG DẪN NẠP TIỀN",
                        Html.fromHtml(
                                String.format(
                                        getString(R.string.ck), content)), new DialogInput.DialogOneButtonClickListener() {
                    @Override
                    public void okClick() {

                    }
                }).show();
                break;
            case R.id.btnLogout:
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                AppController.getInstance().setmProfileUser(null);
                AppController.getInstance().getmSetting().remove(AppConstant.KEY_PROFILE);
                AppController.getInstance().getmSetting().remove(AppConstant.KEY_TOKEN);
                startActivity(intent);
                getActivity().finishAffinity();
                break;
        }
    }

    @Override
    public void onSucessGetProfile(Profile profile) {
        tvName.setText(profile.getName());
        tvAddress.setText(profile.getAddress());
        tvEmail.setText(profile.getPhone());
        if (profile.getAvatar() != null && !profile.getAvatar().isEmpty())
            Picasso.with(getContext()).load(profile.getAvatar()).fit().centerCrop().into(imgAvt);
        tvWallet.setText(String.format("%,.0fđ", profile.getAccountWallet()));

        if (profile.getType() == 1 || profile.getPost() == null) {
            layoutMyWall.setVisibility(View.GONE);
        } else {
            listPost = profile.getPost();
            rcv.setAdapter(new AdapterPostProfile(profile.getPost(), getContext(), this));
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
        presenter.getProfile();
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
        fragmentManager.beginTransaction().replace(R.id.layoutRootProfile, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }
}
