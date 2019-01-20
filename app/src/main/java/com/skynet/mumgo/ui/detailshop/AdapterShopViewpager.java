package com.skynet.mumgo.ui.detailshop;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.skynet.mumgo.models.ShopDetail;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterShopViewpager extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    ShopDetail shopDetail;

    public AdapterShopViewpager(FragmentManager fm, ShopDetail shopDetail) {

        super(fm);
        this.shopDetail = shopDetail;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: {
                return FragmentListProductOfShop.newInstance(shopDetail.getListProduct());
            }
            case 1: {
                return FragmentIntro.newInstance(shopDetail.getShop().getIntro());
            }
            default:
                return FragmentListFeedbackOfShop.newInstance(shopDetail.getRate(),shopDetail.getShop().getNumber_rating(), (float) shopDetail.getShop().getStar());
        }
    }
}
