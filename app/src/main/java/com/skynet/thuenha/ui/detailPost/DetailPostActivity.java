package com.skynet.thuenha.ui.detailPost;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.views.DialogTwoButtonUtil;
import com.skynet.thuenha.ui.views.LockableScrollView;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.Gravity.LEFT;

public class DetailPostActivity extends BaseActivity implements DetailPostContract.View, CompoundButton.OnCheckedChangeListener, OnPageClickListener, DialogTwoButtonUtil.DialogOneButtonClickListener {
    @BindView(R.id.indicator_default_circle)
    InfiniteIndicator indicatorDefaultCircle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.layoutAddress)
    ConstraintLayout layoutAddress;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textView15)
    TextView textView15;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.tvNameHost)
    TextView tvNameHost;
    @BindView(R.id.tvNumberPostHost)
    TextView tvNumberPostHost;
    @BindView(R.id.layoutHost)
    ConstraintLayout layoutHost;
    @BindView(R.id.btnChat)
    TextView btnChat;
    @BindView(R.id.btnGo)
    TextView btnGo;
    @BindView(R.id.cbBottom)
    CheckBox cbBottom;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.cardbottom)
    CardView cardbottom;
    @BindView(R.id.scroll)
    LockableScrollView scroll;
    @BindView(R.id.layoutBottomPaid)
    FrameLayout layoutBottomPaid;
    private DetailPostContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private ArrayList<Page> pageViews;
    private DialogTwoButtonUtil dialogConfirmPrice;
    private boolean flagChecked = false;

    @Override
    protected int initLayout() {
        return R.layout.activity_detail_post;
    }

    @Override
    protected void initVariables() {
        presenter = new DetailPostPresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getDetailPost(getIntent().getExtras().getInt(AppConstant.MSG));
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        IndicatorConfiguration configuration = new IndicatorConfiguration.Builder()
                .imageLoader(new PicassoLoader())
                .isStopWhileTouch(true)
                .onPageClickListener(this)
                .direction(LEFT)
                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
                .build();
        indicatorDefaultCircle.init(configuration);
        indicatorDefaultCircle.notifyDataChange(pageViews);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    private void initData(List<String> listImg) {
        pageViews = new ArrayList<>();
        for (String img : listImg) {
            pageViews.add(new Page("A", img, this));
        }
        indicatorDefaultCircle.notifyDataChange(pageViews);
    }

    @Override
    public void onSuccessGetDetail(DetailPost detailPost) {

        if (detailPost.getIsPay() == 0) {
            layoutAddress.setVisibility(View.GONE);
            layoutHost.setVisibility(View.GONE);
            cardbottom.setVisibility(View.GONE);
            scroll.setScrollingEnabled(false);
            dialogConfirmPrice = new DialogTwoButtonUtil(this, R.drawable.ic_question, "Xem chi tiết tin đăng",
                    Html.fromHtml(String.format(getString(R.string.content_confirm), detailPost.getPriceBuy())),
                    this);

        } else {
            layoutBottomPaid.setVisibility(View.GONE);
            layoutAddress.setVisibility(View.VISIBLE);
            layoutHost.setVisibility(View.VISIBLE);
            cardbottom.setVisibility(View.VISIBLE);
            scroll.setScrollingEnabled(true);
        }

        tvName.setText(detailPost.getPost().getTitle());
        if (detailPost.getPost().getAddress() != null)
            tvAddress.setText(detailPost.getPost().getAddress() + "," + detailPost.getDistrict() + "," + detailPost.getCity());
        tvPrice.setText(String.format("%,.0fđ/Tháng", detailPost.getPost().getPrice()));
        tvStatus.setText(detailPost.getPost().getActive().equals("1") ? "Đang còn" : "Hết phòng");
        tvArea.setText(String.format("%,.0fm²", detailPost.getPost().getArea()));
        tvContent.setText(detailPost.getPost().getContent());
        if (detailPost.getImage() != null)
            initData(detailPost.getImage());
        if (detailPost.getListUtilies() != null) {
            recyclerView.setAdapter(new AdapterUtility(detailPost.getListUtilies(), this));
        }
        bindHostData(detailPost);
        cbBottom.setChecked(detailPost.getIs_favourite() == 1);
        checkBox.setChecked(detailPost.getIs_favourite() == 1);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!flagChecked) {
                    flagChecked = true;
                    cbBottom.setChecked(isChecked);
                }
                presenter.toggleFav(getIntent().getExtras().getInt(AppConstant.MSG), isChecked);

            }
        });
        cbBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!flagChecked) {
                    flagChecked = true;
                    checkBox.setChecked(isChecked);
                }
                presenter.toggleFav(getIntent().getExtras().getInt(AppConstant.MSG), isChecked);

            }
        });


    }

    private void bindHostData(DetailPost detailPost) {
        if (detailPost.getHost() == null) return;
        tvNameHost.setText(detailPost.getHost().getName());
        tvNumberPostHost.setText(detailPost.getHost().getNumber_post() + " phòng cho thuê");
        Picasso.with(this).load(detailPost.getHost().getAvatar()).fit().centerCrop().into(circleImageView);
    }

    @Override
    public void onSuccessPaid() {
        showToast("Đã mua lượt xem thành công", AppConstant.POSITIVE);
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
    protected void onPause() {
        indicatorDefaultCircle.stop();
        if (dialogConfirmPrice != null && dialogConfirmPrice.isShowing())
            dialogConfirmPrice.dismiss();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        indicatorDefaultCircle.start();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.btn_share, R.id.layoutHost, R.id.btnChat, R.id.btnGo, R.id.btnPaid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_share:
                break;
            case R.id.layoutHost:
                break;
            case R.id.btnChat:
                break;
            case R.id.btnGo:
                break;
            case R.id.btnPaid:
                if (dialogConfirmPrice != null && !dialogConfirmPrice.isShowing())
                    dialogConfirmPrice.show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onPageClick(int i, Page page) {

    }

    @Override
    public void okClick() {
        presenter.paidForThisPost(getIntent().getExtras().getInt(AppConstant.MSG));
    }
}
