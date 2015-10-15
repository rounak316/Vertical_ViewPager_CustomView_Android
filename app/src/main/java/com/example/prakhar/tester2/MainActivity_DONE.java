package com.example.prakhar.tester2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity_DONE extends AppCompatActivity implements View.OnTouchListener {

    int one , two , three;

    GestureDetector listner;

ArrayList<Integer> viewId;
    FrameLayout rl1,rl2,rl3, rl;
ArrayList<String> image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image_url = new ArrayList<>();

        image_url.add("http://img0.mxstatic.com/wallpapers/3242c3dae93c72a1d8a6391cc6e5fe06_large.jpeg");
         image_url.add("http://p1.pichost.me/i/9/1321922.jpg");

        image_url.add("http://thewallpaperhost.com/wp-content/uploads/2014/12/the-dark-angel-hd-wallpaper-hd-1080p.jpg");

        image_url.add("http://hdwallpaper-hd.com/wp-content/uploads/abraham-lincoln-vampire-hunter-hd-desktop-wallpaper-high-landscape-3d-picture-hd-wallpaper.jpg");

        image_url.add("http://www.designbolts.com/wp-content/uploads/2014/05/x-men-hd-wallpaper1.jpg");

        image_url.add("http://thewallpaperhost.com/wp-content/uploads/2014/12/android-in-bubble-hd-wallpaper-141.jpg");

        image_url.add("http://www.hdwallpapers.in/walls/manhattan_nights-wide.jpg");

        image_url.add("http://www.hd-wallpapersdownload.com/upload/bulk-upload/best-hd-wallpaper-in.jpg");



        image_url.add("http://www.techicy.com/wp-content/uploads/2015/01/indian-flag-photos-hd-wallpapers-download-free.jpg");

        image_url.add("http://bestpics.in/wp-content/uploads/2014/08/hd-wallpaper-46.jpg");

        image_url.add("http://www.resimsi.com/wp-content/uploads/2015/06/minions-wallpaper-hd-1920x1080.jpg");

        image_url.add("http://hdwallpapersinspiration.com/wp-content/uploads/2014/06/Lionel-Messi-Lattest-HD-Wallpaper-2014.jpg");

        image_url.add("http://www.hdwallpapers.in/walls/piranha-HD.jpg");



        singleton =MySingleton.getInstance(getApplicationContext());


        singleton.getRequestQueue().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {




                if(request.getTag()!=null)
                if(request.getTag().equals("" + cnt))
                {
                    Log.d("FUBUSEDSD" , "!!" + request.getTag());

                }



                Log.d("FUBUSEDSD !"  ,cnt+  " " + request.getTag());


            }
        });






        ViewGroup vg = (ViewGroup) getWindow().getDecorView().getRootView();
        vg.setClipChildren(false);
        vg.setClipToPadding(false);

        listner = new GestureDetector(this , new GestureListner());


        viewId = new ArrayList<>();
        viewId.add(R.id.rl1);  viewId.add(R.id.rl2);  viewId.add(R.id.rl3);
        rl = (FrameLayout) findViewById(R.id.rl);




rl1 = (FrameLayout) findViewById(R.id.rl1);
        rl2 = (FrameLayout) findViewById(R.id.rl2);
        rl3 = (FrameLayout) findViewById(R.id.rl3);



        rl.setOnTouchListener(this

        );


init();
    }


int directionChange = 0;
    void swap_one()
    {


        if(directionChange!=1)
        {
            directionChange = 1;

            cnt = cnt - 3;

            one =cnt + 2;
            two = cnt +1;
            three = cnt;




        }
        else
        {



            cnt--;

            one =cnt+2 ;
            two = cnt +1;
            three = cnt;
        }


        Log.d(cnt + "___CNT" , one + " " + two + " " +three);




        rl1.clearAnimation();
        rl2.clearAnimation();
        rl3.clearAnimation();
        rl1 = (FrameLayout) rl.getChildAt(0);






        final TextView headlines = (TextView) rl1.findViewById(R.id.headlines);
        headlines.setText( "Prakahr Agnihotri " + cnt );

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText("DOWNLOADING HSOULD BW OF " +cnt);

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt ));

        {


//


            String url = image_url.get(three);

final int tmp = three;

            singleton.getImageLoader().get("" + url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer respons, boolean isImmediate) {

                    Bitmap response = respons.getBitmap();
                    if (response != null) {
                        if (view_map != null) {
                            if (view_map.containsKey(tmp)) {

                                ((ImageView) view_map.get(tmp)).setImageBitmap(response);
                                (view_map.get(tmp)).startAnimation(AnimationUtils.loadAnimation(MainActivity_DONE.this, R.anim.abc_fade_in));

                                Log.d("bulla", " ji " + tmp);
                            } else {
                                Log.d("bulla", " ki " + tmp);
                                response.recycle();
                            }


                        } else {
                            Log.d("bulla", " ki " + tmp);
                            response.recycle();
                        }

                    }


                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("IMAGE LOADING", "F " + error);
                }
            });





//







        }

        if  ( ((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap()!=null &&((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap().isRecycled()   )
            ((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap().recycle();
        ((ImageView)rl1.findViewById(R.id.poster)).setImageBitmap(null);


        rl.removeViewAt(0);


        rl.addView(rl1, 2);
        rl1 = (FrameLayout) rl.getChildAt(0);

        rl2 = (FrameLayout) rl.getChildAt(1);
        rl3 = (FrameLayout) rl.getChildAt(2);




        for(int i=0;i<3;i++)
        {
            Log.d("VIEW_ID" , "" + rl.getChildAt(i).getId() + " "  + cnt);
        }

        init();

    }
    HashMap<Integer , View> view_map;


    void swap_two()
    {



        if(directionChange!=2)
        {directionChange = 2;
cnt = cnt + 3;
            three =cnt - 2;
            two = cnt -1;
            one = cnt;

        }
        else
        {
            cnt++;
            one =cnt;
            two = cnt -1;
            three = cnt-2;

        }



        Log.d(cnt + "___CNT" , one + " " + two + " " +three);



        rl1.clearAnimation();
        rl2.clearAnimation();
        rl3.clearAnimation();

        rl1 = (FrameLayout) rl.getChildAt(2);
        final TextView headlines = (TextView) rl1.findViewById(R.id.headlines);
        headlines.setText( "Prakahr Agnihotri " + cnt );

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText("DOWNLOADING HSOULD BW OF " +cnt);

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {










            String url = image_url.get(one);


            final int tmp = cnt;
            singleton.getImageLoader().get("" + url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer respons, boolean isImmediate) {

                    Bitmap response = respons.getBitmap();
                    if(response!=null)
                    {
                        if(view_map!=null)
                        {
                            if(view_map.containsKey(tmp))
                            {

                                ((ImageView)view_map.get(tmp)).setImageBitmap(response);
                                (view_map.get(tmp)).startAnimation(AnimationUtils.loadAnimation(MainActivity_DONE.this, R.anim.abc_fade_in));

                                Log.d("bulla", " ji " + tmp);
                            }
                            else
                            {
                                Log.d("bulla" , " ki " + tmp);
                                response.recycle();
                            }


                        }

                        else
                        {
                            Log.d("bulla" , " ki " + tmp);
                            response.recycle();
                        }

                    }







                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("IMAGE LOADING", "F " + error);
                }
            });



//






        }
        if  ( ((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap()!=null &&((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap().isRecycled()   )
        ((BitmapDrawable)((ImageView)rl1.findViewById(R.id.poster)).getDrawable()).getBitmap().recycle();
        ((ImageView)rl1.findViewById(R.id.poster)).setImageBitmap(null);


        rl.removeViewAt(2);



        rl.addView(rl1, 0);
        rl1 = (FrameLayout) rl.getChildAt(0);

        rl2 = (FrameLayout) rl.getChildAt(1);
        rl3 = (FrameLayout) rl.getChildAt(2);




        for(int i=0;i<3;i++)
        {
            Log.d("VIEW_ID" , "" + rl.getChildAt(i).getId() + " "  + cnt);
        }

        init();
    }
float _dX = 0;

    float _dY = 0;

    void respectiveOfPrespective_CENTRAL_LAYOUT(float dY)


    {

        Log.d("FLIPPED_or_NOT"  , Math.abs(_dY / rl.getHeight()*100) + "");


if(rl2.getScaleX() != 1)
        rl2.setScaleX(   1);
        if(rl2.getScaleY() != 1)
        rl2.setScaleY(1);

        if(dY >= 0 )
        {


            rl1.setVisibility(View.INVISIBLE);
            rl3.setVisibility(View.VISIBLE);

             float scale_factor = Math.abs(dY);
            scale_factor/= rl3.getHeight();
scale_factor = 1 - scale_factor;
            scale_factor *= 100;
scale_factor = InterpolateScaleSmall(scale_factor);


            rl2.setScaleY(  scale_factor );
            rl2.setScaleX(scale_factor);

            rl3.setY(-rl3.getHeight() + dY);
            Log.d("YIPPY 1", "" + dY);

            Log.d("SCALY 1", "" + scale_factor);

        }

        else

        {



            rl1.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.INVISIBLE);

            rl2.setY(dY);
            rl1.setY(0);
            rl3.setY(-rl3.getHeight());
            Log.d("YIPPY 2", "" + dY);

            float scale_factor = Math.abs(dY);
            scale_factor/= rl3.getHeight();
scale_factor *= 100;

            scale_factor = InterpolateScaleEnlarge(scale_factor);
            Log.d("SCALY 2", "" + scale_factor);
            rl1.setScaleY(scale_factor);
            rl1.setScaleX( scale_factor);



        }


    }


    void releaseTouch()
    {

        rl1.clearAnimation();
        rl2.clearAnimation();

float chemical_x =  Math.abs(_dY / rl.getHeight());


if(_dY < 0)
{
if((chemical_x*100) >= 75)
{




    float TIME_FACTOR =1 -chemical_x;


    ScaleAnimation scale_anim = new ScaleAnimation(1 , 1 /rl1.getScaleX(), 1 , 1/rl1.getScaleY(), ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
    scale_anim.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));


    TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -rl.getHeight() - rl2.getY());
    trans_one.setDuration((long) (Math.max(10, _DURATION * TIME_FACTOR)));
Log.d("DURATION", "" + (_DURATION * TIME_FACTOR) + " " + TIME_FACTOR);
    trans_one.setFillEnabled(true);

    scale_anim.setFillEnabled(true);


    trans_one.setAnimationListener(new Animation.AnimationListener() {


        @Override
        public void onAnimationStart(Animation animation) {

            rl.setOnTouchListener(null);


        }


        @Override
        public void onAnimationEnd(Animation animation) {

            rl1.setVisibility(View.INVISIBLE);
            rl3.setVisibility(View.INVISIBLE);
            rl2.setVisibility(View.VISIBLE);

            rl3.setY(-rl3.getHeight());

            rl2.setY(0);
            rl1.setY(0);

            rl1.setScaleY(1);
            rl1.setScaleX(1);

            rl2.setScaleY(1);
            rl2.setScaleX(1);
            rl3.setScaleY(1);
            rl3.setScaleX(1);


            swap_two();
            rl.setOnTouchListener(MainActivity_DONE.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });




    trans_one.setInterpolator(new AccelerateInterpolator());
    rl1.startAnimation(scale_anim);
    rl2.startAnimation(trans_one);


}
    else {


    float TIME_FACTOR = chemical_x;


    ScaleAnimation scale_anim = new ScaleAnimation(rl2.getScaleX(), ZERO_SCALE, rl2.getScaleY(), ZERO_SCALE, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
    scale_anim.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));
    Log.d("DURATION", "" + (_DURATION * TIME_FACTOR) + " " + TIME_FACTOR);

    TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -rl2.getY());
    trans_one.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));
    trans_one.setFillEnabled(true);


    trans_one.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

            rl.setOnTouchListener(null);


        }

        @Override
        public void onAnimationEnd(Animation animation) {

            rl1.setVisibility(View.INVISIBLE);
            rl3.setVisibility(View.INVISIBLE);
            rl2.setVisibility(View.VISIBLE);

            rl3.setY(-rl3.getHeight());

            rl2.setY(0);
            rl1.setY(0);

            rl1.setScaleY(ZERO_SCALE);
            rl1.setScaleX(ZERO_SCALE);

            rl2.setScaleY(1);
            rl2.setScaleX(1);
            rl3.setScaleY(1);
            rl3.setScaleX(1);

            rl.setOnTouchListener(MainActivity_DONE.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
    trans_one.setInterpolator(new AccelerateInterpolator());
    rl2.startAnimation(trans_one);
    rl1.startAnimation(scale_anim);

}
}

else {

    if( (chemical_x*100) >= 75)
    {

        float TIME_FACTOR =1 -chemical_x;


        ScaleAnimation scale_anim = new ScaleAnimation(1 , ZERO_SCALE,  1 , ZERO_SCALE, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scale_anim.setDuration((long)( Math.max( 10 ,_DURATION * TIME_FACTOR)));

        Log.d("DURATION", "" + (_DURATION * TIME_FACTOR) + " " + TIME_FACTOR);
        TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE,  -  rl3.getY() );
        trans_one.setDuration((long)( Math.max( 10 ,_DURATION * TIME_FACTOR)));
trans_one.setFillEnabled(true);
        trans_one.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                rl.setOnTouchListener(null);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rl1.setVisibility(View.INVISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.VISIBLE);

                rl3.setY(-rl3.getHeight());

                rl2.setY(0);
                rl1.setY(0);

                rl1.setScaleY(1);
                rl1.setScaleX(1);

                rl2.setScaleY(ZERO_SCALE);
                rl2.setScaleX(ZERO_SCALE);
                rl3.setScaleY(1);
                rl3.setScaleX(1);


                swap_one();
                rl.setOnTouchListener(MainActivity_DONE.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new AccelerateInterpolator());
        rl2.startAnimation(scale_anim);
        rl3.startAnimation(trans_one);

    }
    else {


        float TIME_FACTOR = chemical_x;

        ScaleAnimation scale_anim = new ScaleAnimation(1, 1 / rl2.getScaleX(), 1, 1 / rl2.getScaleY(), ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scale_anim.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));
        Log.d("DURATION", "" + (_DURATION * TIME_FACTOR) + " " + TIME_FACTOR);

        TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -rl3.getHeight() - rl3.getY());
        trans_one.setDuration((long) ( ( Math.max( 10 ,_DURATION * TIME_FACTOR))));
        scale_anim.setFillEnabled(true);
        trans_one.setFillEnabled(true);
        trans_one.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rl.setOnTouchListener(null);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rl1.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setY(-rl3.getHeight());

                rl2.setY(0);
                rl1.setY(0);

                rl1.setScaleY(ZERO_SCALE);
                rl1.setScaleX(ZERO_SCALE);

                rl2.setScaleY(1);
                rl2.setScaleX(1);
                rl3.setScaleY(1);
                rl3.setScaleX(1);

                rl.setOnTouchListener(MainActivity_DONE.this);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new AccelerateInterpolator());
        rl3.startAnimation(trans_one);
        rl2.startAnimation(scale_anim);

    }
}
    }
    void releaseTouch_fling(boolean FlingToTop)
    {


        rl.setOnTouchListener(null);


        rl1.clearAnimation();
        rl2.clearAnimation();
        float chemical_x =  Math.abs(_dY / rl.getHeight());

float _DURATION = 150;

        if(FlingToTop)
        {

            {


                float TIME_FACTOR =1 -chemical_x;


                ScaleAnimation scale_anim = new ScaleAnimation(1 , 1 /rl1.getScaleX(), 1 , 1/rl1.getScaleY(), ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                scale_anim.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));


                TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -rl.getHeight() -  rl2.getY() );
                trans_one.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));
                scale_anim.setFillEnabled(true);
                trans_one.setFillEnabled(true);

                trans_one.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        rl.setOnTouchListener(null);


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        rl1.setVisibility(View.INVISIBLE);
                        rl3.setVisibility(View.INVISIBLE);
                        rl2.setVisibility(View.VISIBLE);

                        rl3.setY(-rl3.getHeight());

                        rl2.setY(0);
                        rl1.setY(0);

                        rl1.setScaleY(1);
                        rl1.setScaleX(1);

                        rl2.setScaleY(1);
                        rl2.setScaleX(1);
                        rl3.setScaleY(1);
                        rl3.setScaleX(1);


                        swap_two();


                        rl.setOnTouchListener(MainActivity_DONE.this);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                rl1.startAnimation(scale_anim);
                rl2.startAnimation(trans_one);


            }

        }

        else {


            {

                float TIME_FACTOR =1 -chemical_x;


                ScaleAnimation scale_anim = new ScaleAnimation(1 , ZERO_SCALE,  1 , ZERO_SCALE, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                scale_anim.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));


                TranslateAnimation trans_one = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE,  -  rl3.getY() );
                trans_one.setDuration((long) (( Math.max( 10 ,_DURATION * TIME_FACTOR))));
                trans_one.setFillEnabled(true);


                trans_one.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        rl.setOnTouchListener(null);


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        rl1.setVisibility(View.INVISIBLE);
                        rl3.setVisibility(View.INVISIBLE);
                        rl2.setVisibility(View.VISIBLE);

                        rl3.setY(-rl3.getHeight());

                        rl2.setY(0);
                        rl1.setY(0);

                        rl1.setScaleY(1);
                        rl1.setScaleX(1);

                        rl2.setScaleY(ZERO_SCALE);
                        rl2.setScaleX(ZERO_SCALE);
                        rl3.setScaleY(1);
                        rl3.setScaleX(1);


                        swap_one();

                        rl.setOnTouchListener(MainActivity_DONE.this);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                rl2.startAnimation(scale_anim);
                rl3.startAnimation(trans_one);

            }

        }
    }

float init_X = 0;float init_Y = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
int cnt = 0;
    void init()
    {
        Log.d("COUNTER BABY" ,"" +  cnt);

        view_map = new HashMap<>();
        view_map.put(two , rl2.findViewById(R.id.poster) );
        view_map.put(one, rl1.findViewById(R.id.poster));

        view_map.put(three, rl3.findViewById(R.id.poster));


        rl1.setVisibility(View.VISIBLE);
        rl3.setVisibility(View.INVISIBLE);
        rl2.setVisibility(View.VISIBLE);
        rl3.setY(-rl3.getHeight());

        rl2.setY(0);
        rl1.setY(0);

        rl1.setScaleY(1);
        rl1.setScaleX(1);

        rl2.setScaleY(1);
        rl2.setScaleX(1);
        rl3.setScaleY(1);
        rl3.setScaleX(1);



        rl2.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shareScreenShot(rl2);
            }
        });



    }
    float _DURATION = 250;


            @Override
            public boolean onTouch(View v, MotionEvent event) {






                if(listner.onTouchEvent(event))
                {

                    Log.d("DOWN" , "GESTURE");


                    return  true;

                }

                int ACTION = event.getAction();

                switch (ACTION) {

                    case MotionEvent.ACTION_DOWN:
                        init_X = event.getX();
                        init_Y = event.getY();

Log.d("DOWN" , "ACTION");
                        break;


                    case MotionEvent.ACTION_MOVE:
                        Log.d("DOWN" , "ACTION_MOVE");
                        _dY = (event.getY() - init_Y) ;






                        if(_dY >= rl.getHeight())
                        {
                            _dY =  rl.getHeight() - 0.5f;
                        }

                        else  if(_dY <= - rl.getHeight())
                        {
                            _dY = - rl.getHeight() + 0.5f;
                        }
                        respectiveOfPrespective_CENTRAL_LAYOUT(_dY);


                        break;

                    case MotionEvent.ACTION_UP:

                        Log.d("DOWN" , "ACTION_UP");

                        if(_dY==0)
                        {
                            break;
                        }
                        releaseTouch();
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        Log.d("DOWN" , "ACTION_CANCEL");
                        if(_dY==0)
                        {
                            break;
                        }

                        releaseTouch();
                        break;


                }

                return true;
            }



    float ZERO_SCALE= 0.8f;

    float InterpolateScaleEnlarge(float x)
    {

      return (float) (((x)*(0.2f)/(100))  + 0.8);

    }


    float InterpolateScaleSmall(float x)
    {

        return (float) (((x)*(0.2f)/(100))  + 0.8);

    }


    public class GestureListner extends GestureDetector.SimpleOnGestureListener {

        private  final int SWIPE_MIN_DISTANCE = (int) (MainActivity_DONE.this.getResources().getDisplayMetrics().heightPixels*.05f);
        private  final int SWIPE_THRESHOLD_VELOCITY = 500;




        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
//
//        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE &&
//                Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//
//            Log.d("TOUCH_FLING" , "");
//            //From Right to Left
//            return true;
//        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE &&
//                Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//            //From Left to Right
//
//            Log.d("TOUCH_FLING" , "");
//            return true;
//        }




            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                _dY = (e2.getY() - init_Y) ;






                if(_dY >= rl.getHeight())
                {
                    _dY =  rl.getHeight() - 0.5f;
                }

                else  if(_dY <= - rl.getHeight())
                {
                    _dY = - rl.getHeight() + 0.5f;
                }


                Log.d("TOUCH_FLING" , "BtoT");

                releaseTouch_fling(true);

                //From Bottom to Top
                return true;
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                _dY = (e2.getY() - init_Y) ;






                if(_dY >= rl.getHeight())
                {
                    _dY =  rl.getHeight() - 0.5f;
                }

                else  if(_dY <= - rl.getHeight())
                {
                    _dY = - rl.getHeight() + 0.5f;
                }

                Log.d("TOUCH_FLING" , "TtoB");

                releaseTouch_fling(false);
                //From Top to Bottom
                return true;
            }
            return false;
        }
        @Override
        public boolean onDown(MotionEvent event) {
            //always return true since all gestures always begin with onDown and<br>
            //if this returns false, the framework won't try to pick up onFling for example.
            init_X = event.getX();
            init_Y = event.getY();


            return true;

        }


    }
    MySingleton singleton;




    void shareScreenShot(View v)
    {

        v.setDrawingCacheEnabled(true);

// this is the important code :)
// Without it the view will have a dimension of 0,0 and the bitmap will be null
        v.measure(View.MeasureSpec.makeMeasureSpec((int)getResources().getDisplayMetrics().widthPixels, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec((int)getResources().getDisplayMetrics().heightPixels, View.MeasureSpec.AT_MOST));
//                v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//v.layout( (int) v.getX() ,(int) v.getY()  , v.getMeasuredWidth(), v.getMeasuredHeight() );
        v.buildDrawingCache(true);
        Bitmap bm = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache

        String barcodeNumber = "poopy";

        String pathy =   MediaStore.Images.Media.insertImage(getContentResolver(), bm,
                barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



        Uri uri1 = Uri.parse(pathy);
        Uri uri2 = Uri.parse(pathy);
        Uri uri3 = Uri.parse(pathy);

        ArrayList<Uri> imageUriArray = new ArrayList<Uri>();
        imageUriArray.add(uri1);
        imageUriArray.add(uri2);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Text caption message!!");
        intent.setType("text/plain");
        intent.setType("image/jpeg");
        intent.setPackage("com.whatsapp");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);
        startActivity(intent);

    }


}
