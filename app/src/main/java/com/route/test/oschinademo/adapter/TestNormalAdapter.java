package com.route.test.oschinademo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.route.test.oschinademo.R;

/**
 * Created by my301s on 2017/7/12.
 */
 public  class TestNormalAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.mipmap.hehe,
            R.mipmap.haha,
            R.mipmap.meme,
    };
    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public int getCount() {
        return imgs.length;
    }
}
