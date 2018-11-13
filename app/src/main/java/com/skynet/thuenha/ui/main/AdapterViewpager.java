package com.skynet.thuenha.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skynet.thuenha.ui.home.HomeFragment;

public class AdapterViewpager extends FragmentStatePagerAdapter {

    public AdapterViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: {
                return HomeFragment.newInstance();
            }
            case 1: {
                return HomeFragment.newInstance();

            }
            case 2: {
                return HomeFragment.newInstance();

            }
            case 3: {
                return HomeFragment.newInstance();

            }
        }
        return HomeFragment.newInstance();

    }


}
