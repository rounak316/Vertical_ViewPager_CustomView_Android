package com.example.prakhar.tester2;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * Created by Prakhar on 9/25/2015.
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    DiskCache disk_cache;
    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();



        disk_cache = new DiskCache(context);


        mImageLoader = new ImageLoader(mRequestQueue,new DiskCache(context){
            @Override
            public Bitmap getBitmap(String url) {
                Log.d("SAVED FILE PUT" , "CACHE CALLED GET ");
                return super.getBitmap(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                Log.d("SAVED FILE PUT" , "YIPPY");
                super.putBitmap(url, bitmap);

            }
        }
                );


    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}