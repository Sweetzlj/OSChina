package com.route.test.oschinademo.adapter.dongtan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.route.test.oschinademo.base.BaseFragment;

import java.util.List;

/**
 * Created by my301s on 2017/7/13.
 */

public class DongTanAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<String>  titles;
    public DongTanAdapter(FragmentManager fm,List<BaseFragment>  fragments,List<String>  titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
