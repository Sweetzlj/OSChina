package com.route.test.oschinademo.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(1,2000);
    }

    @Override
    protected void initId() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
