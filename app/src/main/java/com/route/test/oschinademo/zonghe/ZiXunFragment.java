package com.route.test.oschinademo.zonghe;

import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.TestNormalAdapter;
import com.route.test.oschinademo.adapter.zonghe.ZiXunAdapter;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.bean.ZiXunBean;
import com.route.test.oschinademo.global.MyApp;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class ZiXunFragment extends BaseFragment {

    private RollPagerView roll_pager_zonghe;
    private PullToRefreshRecyclerView pull_recycler_zonnghe;
    private ArrayList<ZiXunBean.NewsBean> list_zixun = new ArrayList<>();
    private int Index = 0;
    private ZiXunAdapter ziXunAdapter;

    @Override
    protected void initView(View view) {
        pull_recycler_zonnghe = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_recycler_zonnghe);

        ziXunAdapter = new ZiXunAdapter(getActivity().getApplication(), list_zixun);
        pull_recycler_zonnghe.setAdapter(ziXunAdapter);
        setLunBo();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pull_recycler_zonnghe.addItemDecoration(new DividerItemDecoration(MyApp.mBaseActivity, DividerItemDecoration.VERTICAL));
        pull_recycler_zonnghe.setLayoutManager(layoutManager);
        //是否开启下拉刷新功能
        pull_recycler_zonnghe.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pull_recycler_zonnghe.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pull_recycler_zonnghe.displayLastRefreshTime(true);
        //设置刷新回调
        pull_recycler_zonnghe.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pull_recycler_zonnghe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_zixun.clear();
                        ziXunAdapter.notifyDataSetChanged();
                        pull_recycler_zonnghe.setRefreshComplete();
                        Index=0;
                        initData();

                    }
                }, 2000);
            }
            @Override
            public void onLoadMore() {
                pull_recycler_zonnghe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         pull_recycler_zonnghe.setLoadMoreComplete();
                        Index++;
                        initData();
                    }
                }, 2000);
            }
        });
    }

    private void setLunBo() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view, null);
        roll_pager_zonghe = (RollPagerView) view.findViewById(R.id.roll_pager_zonghe);
        roll_pager_zonghe.setPlayDelay(1000);
        roll_pager_zonghe.setAnimationDurtion(500);
        roll_pager_zonghe.setAdapter(new TestNormalAdapter());
        roll_pager_zonghe.setHintView(new ColorPointHintView(getContext(), Color.GREEN, Color.WHITE));
        pull_recycler_zonnghe.addHeaderView(view);
    }

    @Override
    protected void initData() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = "http://www.oschina.net/action/api/news_list?catalog=1&pageIndex="+Index;
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Log.e("TAG",string);
                                XStream xStream = new XStream();
                                xStream.alias("oschina", ZiXunBean.class);
                                xStream.alias("news", ZiXunBean.NewsBean.class);
                                ZiXunBean bean = (ZiXunBean) xStream.fromXML(string);

                                List<ZiXunBean.NewsBean> list = bean.getNewslist();
                                list_zixun.addAll(list);


                                ziXunAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }.start();

    }


    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_zi_xun;
    }
}
