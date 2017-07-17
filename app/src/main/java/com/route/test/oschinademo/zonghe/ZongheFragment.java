package com.route.test.oschinademo.zonghe;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.zonghe.ZongHeAdapter;
import com.route.test.oschinademo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ZongheFragment extends BaseFragment {


    private TabLayout zongheTitle;
    private ViewPager vp;
    private ArrayList<BaseFragment> list_zonnghe=new ArrayList<>();

    private List<String> titles;
    private ZongHeAdapter pagerAdapter;
    private String[] type={"top","tiyu"};

    @Override
    protected void initView(View view) {
        zongheTitle = (TabLayout) view.findViewById(R.id.zonghe_title);
        vp = (ViewPager) view.findViewById(R.id.vp_zonghe);
    }

    @Override
    protected void initData() {

        titles=new ArrayList<>();
        titles.add("开源咨询");
        titles.add("推荐博客");
        titles.add("技术问答");
        titles.add("每日一搏");
        list_zonnghe.add(new ZiXunFragment());
        list_zonnghe.add(new TuiJianFragment());
        list_zonnghe.add(new JiShuFragment());
        list_zonnghe.add(new MeiRiYiBoFragment());


        zongheTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
       // vp.setOffscreenPageLimit(2);
        pagerAdapter =new ZongHeAdapter(getFragmentManager(),list_zonnghe,titles);
        vp.setAdapter(pagerAdapter);
        vp.setCurrentItem(0);
        zongheTitle.setupWithViewPager(vp);
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_zonghe;
    }
}

