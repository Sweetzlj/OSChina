package com.route.test.oschinademo.adapter.zonghe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.bean.JiSHuBean;
import com.route.test.oschinademo.global.MyApp;
import com.route.test.oschinademo.utils.Dates;
import com.route.test.oschinademo.zonghe.JiShuXiangQingActivity;

import java.util.List;

/**
 * Created by my301s on 2017/7/14.
 */

public class JiShuAdapter extends BaseAdapter<JiSHuBean.PostBean> {

    public JiShuAdapter(Context context, List<JiSHuBean.PostBean> datas) {
        super(context, R.layout.jishu_fre, datas);
    }

    @Override
    public void convert(ViewHolder holder, final JiSHuBean.PostBean postBean) {
        holder.setText(R.id.contentTitle,postBean.getTitle());
        holder.setText(R.id.contentBody,postBean.getBody());
        holder.setText(R.id.contentpinglun,postBean.getAnswerCount());
        holder.setText(R.id.contentzuozhe,postBean.getAuthor());
        final ImageView imageView= (ImageView) holder.itemView.findViewById(R.id.contentpic);
        Glide.with(context).load(postBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });
        String data= Dates.getDate(postBean.getPubDate());
        holder.setText(R.id.contentShijian,data);
        holder.setOnclickListener(R.id.ZiXunLayout_Item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, JiShuXiangQingActivity.class);
                intent.putExtra("id",postBean.getId());
                intent.putExtra("count",postBean.getAnswerCount());
                MyApp.mBaseActivity.startActivity(intent);
            }
        });
    }
}
