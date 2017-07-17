package com.route.test.oschinademo.adapter.zonghe;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.bean.ZiXunBean;
import com.route.test.oschinademo.global.MyApp;
import com.route.test.oschinademo.utils.Dates;
import com.route.test.oschinademo.zonghe.ZiXunDetailActivity;

import java.util.List;

/**
 * Created by my301s on 2017/7/13.
 */

public class ZiXunAdapter extends BaseAdapter<ZiXunBean.NewsBean>{
    public ZiXunAdapter(Context context, List<ZiXunBean.NewsBean> datas) {
        super(context, R.layout.zixun_fra, datas);
    }
    @Override
    public void convert(ViewHolder holder, final ZiXunBean.NewsBean newstypeBean) {
        holder.setText(R.id.contentTitle, newstypeBean.getTitle());
        holder.setText(R.id.contentBody, newstypeBean.getBody());
        holder.setText(R.id.contentzuozhe, newstypeBean.getAuthor());
        holder.setText(R.id.contentpinglun,newstypeBean.getCommentCount());
        String date= Dates.getDate(newstypeBean.getPubDate());
        holder.setText(R.id.contentShijian,date);
        holder.setOnclickListener(R.id.ZiXunLayout_Item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ZiXunDetailActivity.class);
                intent.putExtra("id",newstypeBean.getId());
                MyApp.mBaseActivity.startActivity(intent);
            }
        });
    }
}
