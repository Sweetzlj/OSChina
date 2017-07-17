package com.route.test.oschinademo.adapter.dongtan;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.bean.dongtan_entity.NewsDongTanBean;
import com.route.test.oschinademo.global.MyApp;
import com.route.test.oschinademo.utils.Dates;

import java.util.List;

/**
 * Created by my301s on 2017/7/14.
 */

public class NewsDongTanAdapter extends BaseAdapter <NewsDongTanBean.TweetBean>{
    private String SystemDate;
    private Context context;
    private String islike;
    private SharedPreferences mShared;
    private ImageView ImageZan;
    private PopupWindow popup;

    public NewsDongTanAdapter(Context context,List<NewsDongTanBean.TweetBean> datas) {
        super(context, R.layout.news_dongtan, datas);
    }

    @Override
    public void convert(ViewHolder holder, NewsDongTanBean.TweetBean tweetBean) {

        ImageZan= (ImageView) holder.itemView.findViewById(R.id.DongTan_zanImage);
        if ("1".equals(tweetBean.getIsLike())){
            ImageZan.setImageResource(R.drawable.ic_thumbup_actived);
        }else {
            ImageZan.setImageResource(R.drawable.ic_thumb_normal);
        }


        ImageView  image= (ImageView) holder.itemView.findViewById(R.id.DongTan_ImageView);
        Glide.with(MyApp.mBaseActivity).load(tweetBean.getImgBig()).into(image);

        final ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.DongTan_head);
        Glide.with(MyApp.mBaseActivity).load(tweetBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(MyApp.mBaseActivity.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });

        holder.setText(R.id.DongTan_pinlun,tweetBean.getCommentCount());
        holder.setText(R.id.DongTan_name, tweetBean.getAuthor());
        holder.setText(R.id.DongTan_body, tweetBean.getBody());
        if ("0".equals(tweetBean.getIsLike())){
            holder.setText(R.id.DongTan_zan,"赞");
        }

        holder.setText(R.id.DongTan_zan,tweetBean.getLikeCount());
        switch (tweetBean.getAppclient()){
            case "3":
                holder.setText(R.id.DongTan_phone,"Android");
                break;
            case "4":
                holder.setText(R.id.DongTan_phone,"ipone");
                break;
            case  "1":
                holder.setText(R.id.DongTan_phone,"SamSung");
                break;
        }
        //将时间转换
        String date= Dates.getDate(tweetBean.getPubDate());
        holder.setText(R.id.DongTan_date,date);
    }
}
