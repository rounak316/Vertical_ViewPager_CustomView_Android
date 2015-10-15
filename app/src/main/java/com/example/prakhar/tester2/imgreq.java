package com.example.prakhar.tester2;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by Prakhar on 9/26/2015.
 */
public class imgreq extends ImageRequest {


    public imgreq(String tag , String url, Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {



        super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
        imgreq.this.setTag( tag);
        Log.d("IMAGE TAG BC" , "" + imgreq.this.getTag());
    }
}
