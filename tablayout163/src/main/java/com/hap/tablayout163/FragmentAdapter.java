package com.hap.tablayout163;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 继承FragmentPagerAdapter，重新它的方法
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    /**
     * 获取Item
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 设置tab的数量
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }
    /**
     * 显示title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
