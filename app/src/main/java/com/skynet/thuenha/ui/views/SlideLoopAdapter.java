package com.skynet.thuenha.ui.views;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.HintView;

import java.util.ArrayList;

public abstract class SlideLoopAdapter extends PagerAdapter {
    private SlideView mViewPager;

    private ArrayList<View> mViewList = new ArrayList<>();

    private class LoopHintViewDelegate implements SlideView.HintViewDelegate{
        @Override
        public void setCurrentPosition(int position, HintView hintView) {
            if (hintView!=null)
                hintView.setCurrent(position%getRealCount());
        }

        @Override
        public void initView(int length, int gravity, HintView hintView) {
            if (hintView!=null)
                hintView.initView(getRealCount(),gravity);
        }
    }
    @Override
    public void notifyDataSetChanged() {
        mViewList.clear();
        mViewPager.getViewPager().setAdapter(this);
        initPosition(true);
        super.notifyDataSetChanged();
    }

    //一定要用这个回调,因为它只有第一次设置Adapter才会被回调。而除了这个时候去设置位置都是...ANR
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        initPosition(false);
    }

    private void initPosition(boolean isFast){
        if (getCount() <= 1)return;
        int half = isFast?getRealCount()*3: Integer.MAX_VALUE/2;
        int start = half - half%getRealCount();
        mViewPager.getViewPager().setCurrentItem(start,false);
    }

    public SlideLoopAdapter(SlideView viewPager){
        this.mViewPager = viewPager;
        viewPager.setHintViewDelegate(new SlideLoopAdapter.LoopHintViewDelegate());
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position%getRealCount();
        View itemView = findViewByPosition(container,realPosition);
        container.addView(itemView);
        return itemView;
    }


    private View findViewByPosition(ViewGroup container, int position){
        for (View view : mViewList) {
            if (((int)view.getTag()) == position&&view.getParent()==null){
                return view;
            }
        }
        View view = getView(container,position);
        view.setTag(position);
        mViewList.add(view);
        return view;
    }

    public abstract View getView(ViewGroup container, int position);

    @Deprecated
    @Override
    public final int getCount() {
        return getRealCount()<=1?getRealCount(): Integer.MAX_VALUE;
    }

    protected abstract int getRealCount();
}
