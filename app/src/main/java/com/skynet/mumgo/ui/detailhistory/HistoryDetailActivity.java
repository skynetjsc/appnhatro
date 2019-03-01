package com.skynet.mumgo.ui.detailhistory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.History;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.chatting.ChatActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.skynet.mumgo.utils.DateTimeUtil;

import java.text.SimpleDateFormat;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryDetailActivity extends BaseActivity implements AdapterCarHistorytItem.CallBackCart, SwipeRefreshLayout.OnRefreshListener, HistoryContract.View {

    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imageView10)
    View imageView10;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTotalPriceHeader)
    TextView tvTotalPriceHeader;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.constraintLayout6)
    ConstraintLayout constraintLayout6;
    @BindView(R.id.tvStep)
    TextView tvStep;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.view12)
    View view12;
    @BindView(R.id.tvTotalPriceFooter)
    TextView tvTotalPriceFooter;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.constraintLayout5)
    ConstraintLayout constraintLayout5;
    @BindView(R.id.textView44)
    TextView textView44;
    @BindView(R.id.btnNext)
    Button btnNext;
    private HistoryContract.Presenter presenter;
    private ProgressDialogCustom dialogCustom;
    private History history;

    @Override
    protected int initLayout() {
        return R.layout.activity_detail_history;
    }

    @Override
    protected void initVariables() {
        presenter = new HistoryPresenter(this);
        onRefresh();
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        dialogCustom = new ProgressDialogCustom(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onClickDelete(int pos, Product product) {
        Intent i = new Intent(this, ChatActivity.class);
        Bundle b = new Bundle();
        b.putParcelable(AppConstant.INTENT, product.getShop());
        b.putParcelable("user", AppController.getInstance().getmProfileUser());
        b.putInt("idPost", product.getBooking_id());
        b.putString("avt", AppController.getInstance().getmProfileUser().getType() == 1 ? product.getShop().getAvatar() : AppController.getInstance().getmProfileUser().getAvatar());
        i.putExtra(AppConstant.BUNDLE, b);
        startActivityForResult(i, 1000);
    }

    @Override
    public void onClickEdit(int pos, Product product) {

    }

    @Override
    public void onClickDetail(int pos, Product product) {
        Intent i = new Intent(HistoryDetailActivity.this, ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, product.getProduct_id());
        startActivityForResult(i, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        presenter.getHistory(getIntent().getIntExtra(AppConstant.MSG, 0));
    }


    @Override
    public Context getMyContext() {
        return null;
    }

    @Override
    public void showProgress() {
        dialogCustom.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogCustom.hideDialog();
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
        showDialogExpired();
    }

    @Override
    public void onSucessGetCart(History history) {
        this.history = history;
        recyclerView.setAdapter(new AdapterCarHistorytItem(history.getList_product(), this, this));
        tvTitleToolbar.setText("ĐƠN HÀNG #" + history.getId());
        tvDate.setText(history.getTime_ship());
        tvTotalPriceFooter.setText(String.format("%,.0fđ", history.getPrice()));
        tvTotalPriceHeader.setText(String.format("%,.0fđ", history.getPrice()));
        btnNext.setVisibility(View.GONE);
        switch (history.getActive()) {
            case 1: {
                textView44.setText("Đã đặt");
                textView44.setTextColor(Color.parseColor("#00C464"));

                btnNext.setVisibility(View.VISIBLE);
                break;
            }
            case 2: {
                textView44.setText("Đang chờ giao");
                textView44.setTextColor(Color.parseColor("#ECC731"));
                textView44.setBackgroundResource(R.drawable.ic_wait);
                btnNext.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                textView44.setText("Đã giao");
                textView44.setTextColor(Color.parseColor("#00C464"));

                break;
            }
            case 4: {
                textView44.setText("Đã huỷ");
                textView44.setTextColor(Color.parseColor("#FF1313"));
                textView44.setBackgroundResource(R.drawable.ic_cancel_status);
                break;
            }
            case 5: {
                textView44.setText("Đã nhận");
                textView44.setTextColor(ContextCompat.getColor(this, R.color.green));

                break;
            }
        }
        if(DateTimeUtil.convertToDate(history.getDate_booking(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).getTime() + (15*60*1000) < System.currentTimeMillis()){
            btnNext.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnNext)
    public void onClickCancel() {
        presenter.cancle(history.getId());
    }


    @Override
    public void onSucessCancel() {
        showToast("Huỷ đơn hàng thành công", AppConstant.POSITIVE);
        setResult(RESULT_OK);
    }
}
