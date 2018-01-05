package com.zhengpu.watch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import cn.youngkaaa.yviewpager.YFragmentPagerAdapter;

/**
 * sayid ....
 * Created by wengmf on 2017/12/8.
 */
public class HelpFragmentAdapter extends YFragmentPagerAdapter {

    private List<Fragment> mFragments;

    public HelpFragmentAdapter(List<Fragment> mFragments, FragmentManager fm) {
        super(fm);
        this.mFragments = mFragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
