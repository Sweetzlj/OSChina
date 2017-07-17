package com.route.test.oschinademo.zonghe;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.zonghe.TuiJianAdapter;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.bean.TuiJianBean;
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
public class TuiJianFragment extends BaseFragment {

    private PullToRefreshRecyclerView tuijian_pull_pager;
    private ArrayList<TuiJianBean.BlogBean> list_tuijian=new ArrayList<>();
    private TuiJianAdapter tuiJianAdapter;
    private int index=0;

    @Override
    protected void initView(View view) {
        tuijian_pull_pager = (PullToRefreshRecyclerView) view.findViewById(R.id.tuijian_pull_pager);
        tuiJianAdapter = new TuiJianAdapter(getActivity().getApplication(),list_tuijian);
        tuijian_pull_pager.setAdapter(tuiJianAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tuijian_pull_pager.addItemDecoration(new DividerItemDecoration(MyApp.mBaseActivity, DividerItemDecoration.VERTICAL));
        tuijian_pull_pager.setLayoutManager(layoutManager);
        //是否开启下拉刷新功能
        tuijian_pull_pager.setPullRefreshEnabled(true);
//是否开启上拉加载功能
        tuijian_pull_pager.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        tuijian_pull_pager.displayLastRefreshTime(true);

        //设置刷新回调
        tuijian_pull_pager.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                tuijian_pull_pager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_tuijian.clear();
                        tuiJianAdapter.notifyDataSetChanged();
                        tuijian_pull_pager.setRefreshComplete();
                        index=0;
                        initData();

                    }
                }, 2000);
            }
            @Override
            public void onLoadMore() {
                tuijian_pull_pager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tuijian_pull_pager.setLoadMoreComplete();
                        index++;
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
                String url = "http://www.oschina.net/action/api/blog_list?type=recommend&pageIndex="+index;
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
                              //  Log.e("---------",string);
                                XStream xStream = new XStream();
                                xStream.alias("oschina", TuiJianBean.class);
                                xStream.alias("blog", TuiJianBean.BlogBean.class);
                                TuiJianBean bean = (TuiJianBean) xStream.fromXML(string);
                                List<TuiJianBean.BlogBean> list = bean.getBlogs();
                                list_tuijian.addAll(list);

                                tuiJianAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_tui_jian;
    }
}
