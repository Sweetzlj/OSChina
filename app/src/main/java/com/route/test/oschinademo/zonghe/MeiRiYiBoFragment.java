package com.route.test.oschinademo.zonghe;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.route.test.oschinademo.R;
import com.route.test.oschinademo.adapter.zonghe.MeiRiYiBoAdapter;
import com.route.test.oschinademo.base.BaseFragment;
import com.route.test.oschinademo.bean.MeiRiYIBoBean;
import com.route.test.oschinademo.global.MyApp;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class MeiRiYiBoFragment extends BaseFragment {

    @BindView(R.id.contentTitle)
    TextView contentTitle;
    @BindView(R.id.contentBody)
    TextView contentBody;
    @BindView(R.id.sd)
    ImageView sd;
    @BindView(R.id.contentzuozhe)
    TextView contentzuozhe;
    @BindView(R.id.contentpinglun)
    TextView contentpinglun;
    @BindView(R.id.ZiXunLayout_Item)
    LinearLayout ZiXunLayoutItem;
    Unbinder unbinder;
    private PullToRefreshRecyclerView meiriyibo_recycler;
    private int index = 0;
    private ArrayList<MeiRiYIBoBean.NewsBean> list_meiriyibo=new ArrayList<>();
    private MeiRiYiBoAdapter meiRiYiBoAdapter;

    @Override
    protected void initView(View view) {
        meiriyibo_recycler = (PullToRefreshRecyclerView) view.findViewById(R.id.meiriyibo_recycler);
        meiRiYiBoAdapter = new MeiRiYiBoAdapter(getActivity().getApplication(),list_meiriyibo);
        meiriyibo_recycler.setAdapter(meiRiYiBoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        meiriyibo_recycler.addItemDecoration(new DividerItemDecoration(MyApp.mBaseActivity, DividerItemDecoration.VERTICAL));
        meiriyibo_recycler.setLayoutManager(layoutManager);
        //是否开启下拉刷新功能
        meiriyibo_recycler.setPullRefreshEnabled(true);
//是否开启上拉加载功能
        meiriyibo_recycler.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        meiriyibo_recycler.displayLastRefreshTime(true);

        //设置刷新回调
        meiriyibo_recycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                meiriyibo_recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_meiriyibo.clear();
                        meiRiYiBoAdapter.notifyDataSetChanged();
                        meiriyibo_recycler.setRefreshComplete();
                        initData();
                        index=0;
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                meiriyibo_recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        meiriyibo_recycler.setLoadMoreComplete();
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
                String url = "http://www.oschina.net/action/api/news_list?catalog=4&show=week&pageSize=0&pageIndex="+index;
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
//                                Log.e("-----------",string);
                                XStream xStream = new XStream();
                                xStream.alias("oschina", MeiRiYIBoBean.class);
                                xStream.alias("news", MeiRiYIBoBean.NewsBean.class);
                                MeiRiYIBoBean bean = (MeiRiYIBoBean) xStream.fromXML(string);

                                List<MeiRiYIBoBean.NewsBean> list = bean.getNewslist();
                                list_meiriyibo.addAll(list);

                                meiRiYiBoAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_meiriyibo;
    }
}
