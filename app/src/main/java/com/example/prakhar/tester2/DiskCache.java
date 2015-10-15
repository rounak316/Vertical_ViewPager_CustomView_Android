package com.example.prakhar.tester2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Prakhar on 9/25/2015.
 */
public class DiskCache implements ImageLoader.ImageCache
{

public     DiskCache(Context con)
    {
        context =con;
    }

    Context context;

    class  writerAsync extends AsyncTask<Bitmap, String , String>
    {
        String url;
        public writerAsync(String url)
        {
            this.url = url;
        }



        @Override
        protected String doInBackground(Bitmap... params) {
Bitmap bitmap = params[0];

            File file = null;
            try {
                file = new File(context.getFilesDir() ,  new URL(url).getFile() );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if(file.exists())
            {

                return null;
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream     outStream = null;
            try {
                outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();
                Log.d("SAVED FILE PUT" , "YIPPY");
            } catch (Exception e) {
                e.printStackTrace();

                Log.d("SAVED FILE PUT", "FUCK " + e);
            }





            return null;
        }
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        Log.d("SAVED FILE GET", "SUCK");
        File file = null;
        try {
            file = new File(context.getFilesDir() , new URL(url).getFile());
            if(file.exists())
            {
                Log.d("SAVED FILE GET", "SUCK");

            }
            else
            {
                Log.d("SAVED FILE GET", "FUCK");
            }

            bitmap =    BitmapFactory.decodeFile(file.getPath(), new BitmapFactory.Options());



        } catch (MalformedURLException e) {
            e.printStackTrace();

            Log.d("SAVED FILE GET", " !! " + e);
        };


        return  bitmap;




    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

new writerAsync(url).execute(bitmap);


    }
}