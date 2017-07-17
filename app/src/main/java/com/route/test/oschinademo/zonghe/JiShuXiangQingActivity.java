package com.route.test.oschinademo.zonghe;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.route.test.oschinademo.R;
import com.route.test.oschinademo.base.BaseActivity;
import com.route.test.oschinademo.bean.JiShuDetailBean;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JiShuXiangQingActivity extends BaseActivity {

    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.Title_Text)
    TextView TitleText;
    @BindView(R.id.fra_news_commed)
    ImageView fraNewsCommed;
    @BindView(R.id.Top_Group)
    LinearLayout TopGroup;
    @BindView(R.id.activity_info)
    LinearLayout activityInfo;
    private Intent intent;
    private String id;
    private WebView webView;


    @Override
    protected void initData() {
       new Thread() {
            @Override
            public void run() {
                super.run();
                //id=2254371
                final String url = "http://www.oschina.net/action/api/post_detail?id=" + id;
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
                               // Log.e("string----------",string);
                                XStream xStream = new XStream();
                                xStream.alias("oschina", JiShuDetailBean.class);
                                xStream.alias("post", JiShuDetailBean.PostBean.class);
                                JiShuDetailBean bean = (JiShuDetailBean) xStream.fromXML(string);

                                final String url1 = bean.getPost().getUrl();
                                WebSettings settings = webView.getSettings();
                                settings.setJavaScriptEnabled(true);
                                webView.loadUrl(url1);
                                webView.setWebViewClient(new WebViewClient());
                            }
                        });
                    }
                });
            }
        }.start();

    }

    @Override
    protected void initId() {
        webView = (WebView) findViewById(R.id.WebView);
        intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_jishu_xiang_qing;
    }

}
