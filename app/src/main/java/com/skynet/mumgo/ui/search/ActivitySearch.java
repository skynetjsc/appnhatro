package com.skynet.mumgo.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Category;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.category.listProduct.ListProductActivity;
import com.skynet.mumgo.ui.detailProduct.ActivityDetailProduct;
import com.skynet.mumgo.utils.AppConstant;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySearch extends BaseActivity implements AdapterProduct.CallBackProduct {
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.constraintLayout9)
    ConstraintLayout constraintLayout9;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    List<Category> listCategory;
    List<Product> listProduct;
    int type = 0;
    public final static int TYPE_PRODUCT = 1;
    public final static int TYPE_CATEGORY = 2;
    private AdapterCategory adapterCategory;
    private AdapterProduct adapterProduct;
    private Timer timer;

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initVariables() {
        Bundle b = getIntent().getBundleExtra(AppConstant.BUNDLE);
        if (b != null) {
            type = b.getInt("type");
            if (type == TYPE_CATEGORY) {
                listCategory = b.getParcelableArrayList(AppConstant.MSG);
                adapterCategory = new AdapterCategory(listCategory, this, this);
                rcv.setAdapter(adapterCategory);
            } else {
                listProduct = b.getParcelableArrayList(AppConstant.MSG);
                adapterProduct = new AdapterProduct(listProduct, this, this);
                rcv.setAdapter(adapterProduct);
            }
        }

        editText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (type == TYPE_CATEGORY)
                                    adapterCategory.getFilter().filter(s.toString());
                                else
                                    adapterProduct.getFilter().filter(s.toString());
                            }
                        });
                    }
                }, 600);
            }
        });
    }

    @Override
    protected void initViews() {
        rcv.setLayoutManager(new GridLayoutManager(this,2));
        rcv.setHasFixedSize(true);
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

    @OnClick(R.id.imageView13)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onCallBack(int pos) {
        if (type == TYPE_CATEGORY) {
            Intent i = new Intent(ActivitySearch.this, ListProductActivity.class);
            i.putExtra(AppConstant.MSG, listCategory.get(pos).getId());
            startActivity(i);
        }else{
            Intent i = new Intent(ActivitySearch.this, ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG, listProduct.get(pos).getId());
            startActivity(i);
        }
    }

    @Override
    public void toggleFav(int pos, Product product, boolean toggle) {

    }
}
