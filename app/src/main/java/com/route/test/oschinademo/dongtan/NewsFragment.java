package com.route.test.oschinademo.dongtan;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.dongtan.NewsDongTanAdapter;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.bean.dongtan_entity.NewsDongTanBean;
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
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {


    private PullToRefreshRecyclerView news_dongtan;
    private int Index = 0;
    private ArrayList<NewsDongTanBean.TweetBean> list_dongtanNews= new ArrayList<>();
    private NewsDongTanAdapter newsDongTanAdapter;

    @Override
    protected void initView(View view) {
        news_dongtan = (PullToRefreshRecyclerView) view.findViewById(R.id.news_dongtan);
        newsDongTanAdapter = new NewsDongTanAdapter(getActivity().getApplication(),list_dongtanNews);
        news_dongtan.setAdapter(newsDongTanAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        news_dongtan.addItemDecoration(new DividerItemDecoration(MyApp.mBaseActivity, DividerItemDecoration.VERTICAL));
        news_dongtan.setLayoutManager(layoutManager);
        news_dongtan.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        news_dongtan.setLoadingMoreEnabled(true);
        //开启刷新回调
        news_dongtan.displayLastRefreshTime(true);


        //设置刷新回调
        news_dongtan.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                news_dongtan.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_dongtanNews.clear();
                        newsDongTanAdapter.notifyDataSetChanged();
                        news_dongtan.setRefreshComplete();
                        initData();
                        Index=0;
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                news_dongtan.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        news_dongtan.setLoadMoreComplete();
                        Index++;
                        initData();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = "http://www.oschina.net/action/api/tweet_list?uid=0&pageSize=5&pageIndex="+Index;
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
                                //Log.e("==========",string);

                                XStream xStream = new XStream();
                                xStream.alias("oschina", NewsDongTanBean.class);
                                xStream.alias("tweet", NewsDongTanBean.TweetBean.class);
                                NewsDongTanBean bean = (NewsDongTanBean) xStream.fromXML(string);

                                List< NewsDongTanBean.TweetBean> list = bean.getTweets();
                                list_dongtanNews.addAll(list);

                                newsDongTanAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_news;
    }
}
