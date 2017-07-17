package com.route.test.oschinademo.zonghe;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.zonghe.JiShuAdapter;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.global.MyApp;
import com.thoughtworks.xstream.XStream;
import com.route.test.oschinademo.bean.JiSHuBean;

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
public class JiShuFragment extends BaseFragment {

    private PullToRefreshRecyclerView jishu_recycler;
    private int Index = 0;
    private ArrayList<JiSHuBean.PostBean> list_jishu=new ArrayList<>();
    private JiShuAdapter jiShuAdapter;

    @Override
    protected void initView(View view) {
        jishu_recycler = (PullToRefreshRecyclerView) view.findViewById(R.id.jishu_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        jiShuAdapter = new JiShuAdapter(getActivity().getApplication(),list_jishu);
        jishu_recycler.setAdapter(jiShuAdapter);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jishu_recycler.addItemDecoration(new DividerItemDecoration(MyApp.mBaseActivity, DividerItemDecoration.VERTICAL));
        jishu_recycler.setLayoutManager(layoutManager);
        jishu_recycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        jishu_recycler.setLoadingMoreEnabled(true);
        //开启刷新回调
        jishu_recycler.displayLastRefreshTime(true);


        //设置刷新回调
        jishu_recycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                jishu_recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_jishu.clear();
                        jiShuAdapter.notifyDataSetChanged();
                        jishu_recycler.setRefreshComplete();
                        Index=0;
                        initData();
                    }
                }, 2000);
            }
            @Override
            public void onLoadMore() {
                jishu_recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jishu_recycler.setLoadMoreComplete();
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
                String url = "http://www.oschina.net/action/api/post_list?catalog=1&pageIndex="+Index;
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
                                xStream.alias("oschina", JiSHuBean.class);
                                xStream.alias("post", JiSHuBean.PostBean.class);
                                JiSHuBean bean = (JiSHuBean) xStream.fromXML(string);

                                List<JiSHuBean.PostBean> list = bean.getPosts();
                                list_jishu.addAll(list);


                                jiShuAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_ji_shu;
    }
}
