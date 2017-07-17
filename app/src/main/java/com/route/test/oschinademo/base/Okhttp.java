package com.route.test.oschinademo.base;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * 封装一个OKhttp的单例
 */

public class Okhttp {
    public static ResponseBody GetOkhttp(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        ResponseBody string = call.execute().body();
            return string;
    }
}
