package com.example.prakhar.tester2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class splash extends AppIntro {

    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "356356197551";

    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);






               //     MyApplication.mRequestQueue.add(req);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", msg);
                    Log.d("YES BITCHES", "DSADSA");


                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.i("GCM",  ex + " ");


                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {


                RestAdapter adapter =   adapter =((app)getApplication()).adapter;;


                adapter.create(RetrofitClient.class).register(""+regid, new Callback<String>() {
                    @Override
                    public void success(String newses, retrofit.client.Response response) {
                        Log.d("DSASAD",  newses );
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Log.d("DSASAD",  error + "" );

                    }
                });
                //    Toast.makeText(Splash2.this, "GCM registered", 1000).show();
            }
        }.execute(null, null, null);
    }







    @Override
    public void init(Bundle bundle) {

        getRegId();

       boolean intention = getIntent().getBooleanExtra("intention" , false);

   Boolean bol =     getSharedPreferences("first" , 1).getBoolean("first", true);

        if(intention)
        {
            bol = true;
        }

        if(bol )
        {

           // getSharedPreferences("first" , 1).edit().putBoolean("first" , false).commit();



        }
        else
        {
            startActivity(new Intent(this , Default.class));
            finish();
        }


        addSlide(AppIntroFragment.newInstance("","", R.drawable.logo, (Color.parseColor("#ffffff"))));
      //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
//        addSlide(AppIntroFragment.newInstance("Spread the news", "Share all our content easily", R.drawable.img_one, (Color.parseColor("#f44336"))));
      //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));


          addSlide(AppIntroFragment.newInstance("Vidhayaka News", "We set the news agenda", R.drawable.img_one, (Color.parseColor("#f44336"))));



//        addSlide(AppIntroFragment.newInstance("Vidhayaka News", "You would be pleased to know that our prakharnews.com is successfully disseminating news since last 10 years as we started this new site in the year 2005.", R.drawable.broadcast, (Color.parseColor("#2380FD"))));
        //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
//        addSlide(AppIntroFragment.newInstance("Spread the news", "prakharnews.com  is a first online website which connect with the mobile media this is a first web in the country who's news alerts are available on all social medai platform", R.drawable.img_one, (Color.parseColor("#f44336"))));
        //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));

//        addSlide(AppIntroFragment.newInstance("prakhar News", "prakharnews.com is an attempt to serve the readers with firsthand news that has come out of an outstanding Journalism in the Public Interest", R.drawable.broadcast, (Color.parseColor("#2380FD"))));
        //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
//        addSlide(AppIntroFragment.newInstance("Spread the news", "prakharnews.com has been synonymous with excellence in journalism and a commitment to independence and fairness", R.drawable.img_one, (Color.parseColor("#f44336"))));
        //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));

//        addSlide(AppIntroFragment.newInstance("prakhar News", "I can assure you that any  advertorial or promotional package you opt for in our media website will give you the maximum mileage as we are available among the decision makers of the country", R.drawable.broadcast, (Color.parseColor("#2380FD"))));
        //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
//        addSlide(AppIntroFragment.newInstance("Spread the news", "Share all our content easily", R.drawable.img_one, (Color.parseColor("#f44336"))));
        //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));

//        addSlide(AppIntroFragment.newInstance("prakhar News", "We set the news agenda", R.drawable.broadcast, (Color.parseColor("#2380FD"))));
        //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
//        addSlide(AppIntroFragment.newInstance("Spread the news", "Share all our content easily", R.drawable.img_one, (Color.parseColor("#f44336"))));
        //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));











        addSlide(AppIntroFragment.newInstance("Developed by", "Prakhar Agnihotri\n9425813670\nrounak316@gmail.com", R.drawable.img_four, (Color.parseColor("#2380FD"))));
     //   addSlide(AppIntroFragment.newInstance("Want your own App?", "Contact Us\nrounak316@gmail.com\n9425813670", R.drawable.share, (Color.parseColor("#FF1F80E1"))));

        setVibrate(true);

        setBarColor(Color.parseColor("#2380FD"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        setZoomAnimation();
        setVibrateIntensity(30);


setSkipText("Login");

        showSkipButton(false);

    }

    @Override
    public void onSkipPressed() {
        startActivity(new Intent(this , Fill_Splash.class));
        finish();
        // Login

    }


    @Override
    public void onDonePressed() {
        getSharedPreferences("first" , 1).edit().putBoolean("first" , false).commit();

        startActivity(new Intent(this , Default.class));
        finish();

    }
}
