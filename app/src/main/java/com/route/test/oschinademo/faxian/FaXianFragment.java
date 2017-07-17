package com.route.test.oschinademo.faxian;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.faxian.kaiyuan_activity.KaiYuanActivity;
import com.route.test.oschinademo.global.MyApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaXianFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout find_open;
    private LinearLayout find_ma;
    private LinearLayout find_near;
    private LinearLayout find_sao;
    private LinearLayout find_yao;
    private LinearLayout find_activity;

    @Override
    protected void initView(View view) {
        find_open = (LinearLayout) view.findViewById(R.id.find_open);
        find_ma = (LinearLayout) view.findViewById(R.id.find_ma);
        find_near = (LinearLayout) view.findViewById(R.id.find_near);
        find_sao = (LinearLayout) view.findViewById(R.id.find_sao);
        find_yao = (LinearLayout) view.findViewById(R.id.find_yao);
        find_activity = (LinearLayout) view.findViewById(R.id.find_activity);

        find_open.setOnClickListener(this);
        find_ma.setOnClickListener(this);
        find_near.setOnClickListener(this);
        find_sao.setOnClickListener(this);
        find_yao.setOnClickListener(this);
        find_activity.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fa_xian;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_ma:
                break;
            case R.id.find_open:
                Intent intent = new Intent(MyApp.mBaseActivity, KaiYuanActivity.class);
                startActivity(intent);
                break;
            case R.id.find_sao:
                break;
            case R.id.find_yao:
                break;
            case R.id.find_near:
                break;
            case R.id.find_activity:
                break;
        }
    }
}
