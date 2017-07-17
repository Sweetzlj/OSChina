package com.route.test.oschinademo.zonghe;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.base.BaseActivity;
import com.route.test.oschinademo.bean.ZiXunDetailBean;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZiXunDetailActivity extends BaseActivity {


    private String id;
    private WebView webview;

    @Override
    protected void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //id=2254371
                String url = "http://www.oschina.net/action/api/news_detail?id="+id;
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                XStream xStream = new XStream();
                                xStream.alias("oschina", ZiXunDetailBean.class);
                                xStream.alias("news", ZiXunDetailBean.NewsBean.class);
                                xStream.alias("relative",ZiXunDetailBean.NewsBean.RelativeBean.class);
                                ZiXunDetailBean bean = (ZiXunDetailBean) xStream.fromXML(string);


                                String url = bean.getNews().getUrl();
                                WebSettings settings = webview.getSettings();
                                settings.setJavaScriptEnabled(true);
                                webview.loadUrl(url);
                                webview.setWebViewClient(new WebViewClient());
                            }
                        });
                    }
                });
            }
        }.start();
    }

    @Override
    protected void initId() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        webview = (WebView) findViewById(R.id.webview);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zi_xun_detail;
    }
}
