package com.route.test.oschinademo.dongtan;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.dongtan.DongTanAdapter;
import com.route.test.oschinademo.base.BaseFragment;

import java.util.ArrayList;

/**
 *
 */
public class DongTanFragment extends BaseFragment {

    private ArrayList<String> titles;
    private ArrayList<BaseFragment> list_dongtan=new ArrayList<>();
    private DongTanAdapter pagerAdapter;
    private ViewPager vpDongtan;
    private TabLayout dongtanTitle;

    @Override
    protected void initView(View view) {
        vpDongtan = (ViewPager) view.findViewById(R.id.vp_dongtan);
        dongtanTitle = (TabLayout) view.findViewById(R.id.dongtan_title);
    }

    @Override
    protected void initData() {
        titles =new ArrayList<>();
        titles.add("最新动弹");
        titles.add("热门动弹");
        titles.add("每日乱弹");
        titles.add("我的动弹");
        list_dongtan.add(new NewsFragment());
        list_dongtan.add(new ReMenFragment());
        list_dongtan.add(new MeiRiFragment());
        list_dongtan.add(new MineFragment());

        dongtanTitle.setTabMode(TabLayout.MODE_FIXED);

        pagerAdapter =new DongTanAdapter(getFragmentManager(),list_dongtan,titles);
        vpDongtan.setAdapter(pagerAdapter);
        vpDongtan.setCurrentItem(0);
        dongtanTitle.setupWithViewPager(vpDongtan);
    }
    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_dong_tan;
    }

}
