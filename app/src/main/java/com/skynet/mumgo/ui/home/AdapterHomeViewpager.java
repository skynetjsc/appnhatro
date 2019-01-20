package com.skynet.mumgo.ui.home;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.skynet.mumgo.ui.auth.FragmentIntro;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterHomeViewpager extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public AdapterHomeViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
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
            case 1:{
                return    FragmentIntro.newInstance("Mua sắm nhiều nơi","Cùng một lúc bạn có thể mua nhiều mặt hàng trên nhiều bách hoá và hệ thống siêu thị uy tín.");
            }case 2:{
                return   FragmentIntro.newInstance("Dễ dàng tiện lợi","Chọn mặt hàng bạn muốn mà không cần phải đi đâu cả, chỉ với vài thao tác, đơn hàng của bạn sẽ được giao đến tận tay bạn.");
            }case 3:{
                return   FragmentIntro.newInstance("Mua sắm nhiều nơi","Cùng một lúc bạn có thể mua nhiều mặt hàng trên nhiều bách hoá và hệ thống siêu thị uy tín.");
            }
            default:
             return    FragmentIntro.newInstance("Mua sắm nhiều nơi","Cùng một lúc bạn có thể mua nhiều mặt hàng trên nhiều bách hoá và hệ thống siêu thị uy tín.");
        }
    }
}
