package com.route.test.oschinademo.faxian.kaiyuan_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.zonghe.ZongHeAdapter;
import com.route.test.oschinademo.base.BaseActivity;
import com.route.test.oschinademo.base.BaseFragment;

import java.util.ArrayList;

public class KaiYuanActivity extends BaseActivity {

    private TabLayout KaiYuanTablayut;
    private ViewPager KaiYuanViewPager;
    private ArrayList<BaseFragment> list= new ArrayList<>();
    private ArrayList<String> titles;
    private ZongHeAdapter pagerAdapter;

    @Override
    protected void initData() {

        titles =new ArrayList<>();
        titles.add("分类");
        titles.add("推荐");
        titles.add("最新");
        titles.add("热门");
        titles.add("国产");

        list.add(new FenLeiFragment());
        list.add(new TuiJiaFragment());
        list.add(new ZuiXinFragment());
        list.add(new ReMenDemoFragment());
        list.add(new GuoChanFragment());

        KaiYuanTablayut.setTabMode(TabLayout.MODE_FIXED);
        pagerAdapter =new ZongHeAdapter(getSupportFragmentManager(),list,titles);
        KaiYuanViewPager.setAdapter(pagerAdapter);
        KaiYuanViewPager.setCurrentItem(0);
        KaiYuanTablayut.setupWithViewPager(KaiYuanViewPager);

    }

    @Override
    protected void initId() {
        KaiYuanViewPager = (ViewPager) findViewById(R.id.KaiYuanViewPager);
        KaiYuanTablayut= (TabLayout) findViewById(R.id.KaiYuanTablayut);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_kai_yuan;
    }

}
