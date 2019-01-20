package com.skynet.mumgo.ui.detailshop;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Product;
import com.skynet.mumgo.models.Rate;
import com.skynet.mumgo.ui.base.BaseFragment;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentListFeedbackOfShop extends BaseFragment implements AdapterProduct.CallBackProduct {
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.rati)
    RatingBar rati;
    @BindView(R.id.tvNumberRating)
    TextView tvNumberRating;
    int num;
    float rating;
    private List<Rate> list;

    public static FragmentListFeedbackOfShop newInstance(List<Rate> list, int num, float rating) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(AppConstant.MSG, (ArrayList<? extends Parcelable>) list);
        args.putInt("num", num);
        args.putFloat("rating", rating);
        FragmentListFeedbackOfShop fragment = new FragmentListFeedbackOfShop();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
    }

    @Override
    protected void initVariables() {
        list = getArguments().getParcelableArrayList(AppConstant.MSG);
        num = getArguments().getInt("num");
        rating = getArguments().getFloat("rating");
        tvNumberRating.setText(String.format("(%d đánh giá)", num));
        rati.setRating(rating);
        if (list != null) {
            rcv.setAdapter(new AdapterFeedback(list, getContext()));
        }
    }


    @Override
    public void toggleFav(int pos, Product product, boolean toggle) {

    }

    @Override
    public void onCallBack(int pos) {

    }
}
