package com.route.test.oschinademo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.base.BaseActivity;
import com.route.test.oschinademo.base.FragmentBuilder;
import com.route.test.oschinademo.dongtan.DongTanFragment;
import com.route.test.oschinademo.faxian.FaXianFragment;
import com.route.test.oschinademo.wode.WoDeFragment;
import com.route.test.oschinademo.zonghe.ZongheFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.Title_Text)
    TextView TitleText;
    @BindView(R.id.Serch_Btn)
    ImageView SerchBtn;
    @BindView(R.id.Top_Group)
    RelativeLayout TopGroup;
    @BindView(R.id.main_ZongHe)
    RadioButton mainZongHe;
    @BindView(R.id.main_DongTan)
    RadioButton mainDongTan;
    @BindView(R.id.main_Login)
    ImageView mainLogin;
    @BindView(R.id.main_FaXian)
    RadioButton mainFaXian;
    @BindView(R.id.main_WoDe)
    RadioButton mainWoDe;
    @BindView(R.id.Main_RadioGroup)
    RadioGroup MainRadioGroup;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.MainFrameLayout)
    FrameLayout MainFrameLayout;


    @Override
    protected void initData() {
        TitleText.setText("综合");
        FragmentBuilder.getFragmentBuilder().containerId(R.id.MainFrameLayout).satrt(ZongheFragment.class).build();
        MainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                  /*  case R.id.Serch_Btn:

                        break;
                    case R.id.main_Login:

                        break;*/
                    case R.id.main_ZongHe:
                        TopGroup.setVisibility(View.VISIBLE);
                        TitleText.setText("综合");
                        FragmentBuilder.getFragmentBuilder().containerId(R.id.MainFrameLayout).satrt(ZongheFragment.class).build();
                        break;
                    case R.id.main_DongTan:
                        TopGroup.setVisibility(View.VISIBLE);
                        TitleText.setText("动弹");
                        FragmentBuilder.getFragmentBuilder().containerId(R.id.MainFrameLayout).satrt(DongTanFragment.class).build();
                        break;

                    case R.id.main_FaXian:
                        TopGroup.setVisibility(View.VISIBLE);
                        TitleText.setText("发现");
                        FragmentBuilder.getFragmentBuilder().containerId(R.id.MainFrameLayout).satrt(FaXianFragment.class).build();
                        break;
                    case R.id.main_WoDe:
                        TopGroup.setVisibility(View.GONE);
                        FragmentBuilder.getFragmentBuilder().containerId(R.id.MainFrameLayout).satrt(WoDeFragment.class).build();
                        break;

                }
            }
        });
    }

    @Override
    protected void initId() {

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Serch_Btn, R.id.main_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Serch_Btn:
                Intent intent1 = new Intent(HomeActivity.this,SouSuoActivity.class);
                startActivity(intent1);
                break;
            case R.id.main_Login:
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}