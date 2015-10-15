package com.example.prakhar.tester2;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Prakhar on 9/25/2015.
 */
public class app extends Application {
     RestAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();

//        MySingleton.getInstance(getApplicationContext());
//
//
//        File httpCacheDirectory = new File(getApplicationContext().getCacheDir(), "responses");
//
//        Cache cache = null;
//        {
//            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//        }
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        if (cache != null) {
//            okHttpClient.setCache(cache);
//        }
//
//        okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);







        RestAdapter.Builder bui32lder = new RestAdapter.Builder()
                .setEndpoint("http://app.newsportal.co.in")
                .setClient(new OkClient(new OkHttpClient()));





        adapter = bui32lder.build();



    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)     getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }
    private  final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {





            Request request = chain.request();

            // Add Cache Control only for GET methods
            if (request.method().equals("GET")) {
                if (isOnline()) {
                    // 1 day
                    request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build();
                    Log.d("cacheer" , "online");

                } else {
                    // 4 weeks stale
                    request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .build();

                    Log.d("cacheer", "ffline");
                }
            }
            Log.d("cacheer" , "achsadsa");
            Response response = chain.proceed(request);

            // Re-write response CC header to force use of cache
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=86400") // 1 day
                    .build();
        }
    };
}
