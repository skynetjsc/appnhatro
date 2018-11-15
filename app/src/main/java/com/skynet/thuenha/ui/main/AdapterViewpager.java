package com.skynet.thuenha.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skynet.thuenha.ui.favourite.FavouriteFragment;
import com.skynet.thuenha.ui.home.HomeFragment;
import com.skynet.thuenha.ui.listchat.ListChatFragment;
import com.skynet.thuenha.ui.profile.ProfileFragment;
import com.skynet.thuenha.ui.search.FragmentSearch;

public class AdapterViewpager extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    public AdapterViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
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
                return HomeFragment.newInstance();
            }
            case 1: {
                return FavouriteFragment.newInstance();

            }
            case 2: {
                return ListChatFragment.newInstance();

            }
            case 3: {
                return ProfileFragment.newInstance();

            }
        }
        return FragmentSearch.newInstance(1);

    }


}
