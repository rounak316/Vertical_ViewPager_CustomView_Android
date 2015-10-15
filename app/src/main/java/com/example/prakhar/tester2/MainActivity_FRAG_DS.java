package com.example.prakhar.tester2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity_FRAG_DS extends Fragment implements View.OnTouchListener {

View parent_view ;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup vg = (ViewGroup) view;
        vg.setClipChildren(false);
        vg.setClipToPadding(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.activity_main , null);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        rl = (FrameLayout)view. findViewById(R.id.rl);




        rl1 = (FrameLayout) view.findViewById(R.id.rl1);
        rl2 = (FrameLayout) view.findViewById(R.id.rl2);
        rl3 = (FrameLayout) view.findViewById(R.id.rl3);
        ViewGroup vg = container;
        vg.setClipChildren(false);
        vg.setClipToPadding(false);
        parent_view = view;

  return  view;
    }

    @Override
    public void onStart() {
        super.onStart();





        singleton =MySingleton.getInstance(getActivity().getApplicationContext());


        singleton.getRequestQueue().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {


                if (request.getTag() != null)
                    if (request.getTag().equals("" + cnt)) {
                        Log.d("FUBUSEDSD", "!!" + request.getTag());

                    }


                Log.d("FUBUSEDSD !", cnt + " " + request.getTag());


            }
        });








        listner = new GestureDetector(getActivity() , new GestureListner());


        viewId = new ArrayList<>();
        viewId.add(R.id.rl1);  viewId.add(R.id.rl2);  viewId.add(R.id.rl3);




        rl.setOnTouchListener(this

        );

fillData();
        init();

        initialize();
    }


    void initialize()
    {



        one = 2;
        two = 1;
        three=0;


        view_map = new HashMap<>();


        view_map.put(two, rl2.findViewById(R.id.poster));
        view_map.put(one, rl1.findViewById(R.id.poster));

        view_map.put(three, rl3.findViewById(R.id.poster));

        Log.d("PICAASO COUNTER BABY", "" + view_map.keySet());

         TextView headlines = (TextView) rl1.findViewById(R.id.headlines);
        headlines.setText(  head.get(cnt+2) );

        headlines = (TextView) rl2.findViewById(R.id.headlines);
        headlines.setText(  head.get (cnt+1) );

        headlines = (TextView) rl3.findViewById(R.id.headlines);
        headlines.setText(head.get(cnt));

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText(desc.get(cnt + 2));

        detailed = (TextView) rl2.findViewById(R.id.detailed);
        detailed.setText(desc.get(cnt + 1));

        detailed = (TextView) rl3.findViewById(R.id.detailed);
        detailed.setText(desc.get(cnt));

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {


//




            final int tmp = two;

            String url = image_url.get(tmp);
            Log.d("PICAASO ERROR start " ,  " " + tmp + " " + url+ " ---> " + image_url );

            try {
                com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                        if(view_map.containsKey(tmp))
                        {
                            ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded" , " misssing place" + tmp + " "  + view_map.keySet() );
                        }

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                        Log.d("PICAASO onBitmapFailed" , " " + tmp + " "  );


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {


                        Log.d("PICAASO onPrepareLoad" , " " + tmp + " "  );

                    }
                };


                Picasso.with(getContext())
                        .load(url)

              .into(target);
            }
            catch (Exception e)
            {

                Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + image_url );

            }







            final int tmp2 = one;
            url = image_url.get(tmp2);
            Log.d("PICAASO ERROR start 2 " ,  " " + tmp2 + " " + url+ " ---> " + image_url );
            try {
                com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                        if(view_map.containsKey(tmp2))
                        {
                            ((ImageView) view_map.get(tmp2)).setImageBitmap(bitmap);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp2 + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp2 + " " + view_map.keySet() );
                        }

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                        Log.d("PICAASO onBitmapFailed" , " " + tmp2 + " "  );


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {


                        Log.d("PICAASO onPrepareLoad" , " " + tmp2 + " "  );

                    }
                };


                Picasso.with(getContext())
                        .load(url)

                        .into(target);
            }
            catch (Exception e)
            {

                Log.d("PICAASO ERROR" , e + " " + tmp2 + " " + url+ " ---> " + image_url );

            }


//







        }



    }


    int directionChange = 0;
    void swap_one()
    {
        vibrate();

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
        headlines.setText( head.get( cnt ));

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText( desc.get(cnt));

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {


//








//







        }


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


        final int tmp = three;
        String url = image_url.get(tmp);

        try {
            com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                    Log.d("PICAASO onBitmapFailed" , " " + tmp + " "   );


                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {


                    Log.d("PICAASO onPrepareLoad" , " " + tmp + " "  );

                }
            };


            Picasso.with(getContext())
                    .load(url)

                    .into(target);
        }
        catch (Exception e)
        {

            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + image_url );

        }

    }
    HashMap<Integer , View> view_map;


    void swap_two()
    {
        vibrate();


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
        headlines.setText(  head.get(cnt) );

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText( desc.get(cnt));

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {

















//






        }



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


        final int tmp = one;
        String url = image_url.get(tmp);

        try {
            com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                    Log.d("PICAASO onBitmapFailed" , " " + tmp + " "   );


                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {


                    Log.d("PICAASO onPrepareLoad" , " " + tmp + " "  );

                }
            };


            Picasso.with(getContext())
                    .load(url)

                    .into(target);
        }
        catch (Exception e)
        {

            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + image_url );

        }



    }

    int one , two , three;

    GestureDetector listner;

ArrayList<Integer> viewId;
    FrameLayout rl1,rl2,rl3, rl;


    protected void onCreaste(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

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

        if(_dY >= 0 ) {




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
            rl.setOnTouchListener(MainActivity_FRAG_DS.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });




    trans_one.setInterpolator(new AccelerateDecelerateInterpolator());
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

            rl.setOnTouchListener(MainActivity_FRAG_DS.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
    trans_one.setInterpolator(new AccelerateDecelerateInterpolator());
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
                rl.setOnTouchListener(MainActivity_FRAG_DS.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new AccelerateDecelerateInterpolator());
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

                rl.setOnTouchListener(MainActivity_FRAG_DS.this);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new AccelerateDecelerateInterpolator());
        rl3.startAnimation(trans_one);
        rl2.startAnimation(scale_anim);

    }
}
    }
    void releaseTouch_fling(boolean FlingToTop)
    {

        if(!FlingToTop) {

            if (three == 0) {

                init_Y = _dY;
                rl2.setY(0);


                return;
            }

        }
else       {

            if (one == image_url.size() - 1) {

                init_Y = _dY;
                rl2.setY(0);


                return;
            }

        }

        rl.setOnTouchListener(null);


        rl1.clearAnimation();
        rl2.clearAnimation();
        float chemical_x =  Math.abs(_dY / rl.getHeight());

float _DURATION = 100;

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


                        rl.setOnTouchListener(MainActivity_FRAG_DS.this);


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

                        rl.setOnTouchListener(MainActivity_FRAG_DS.this);

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
        view_map = new HashMap<>();
        view_map.put(one , ((ImageView)rl1.findViewById(R.id.poster)));
        view_map.put(two , ((ImageView)rl2.findViewById(R.id.poster)));
        view_map.put(three , ((ImageView)rl3.findViewById(R.id.poster)));


        Log.d("PICAASO COUNTER BABY" ,"" +  view_map.keySet());





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



                        ((Default)getActivity()).viewPager.beginFakeDrag();

                        v.getParent().requestDisallowInterceptTouchEvent(true);

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



                        if(_dY >= 0 ) {

                            if (three == 0) {

                                init_Y = _dY;
                                rl2.setY(0);

                                init_Y = event.getY();
                                break;
                            }

                        }

                        if(_dY <= 0 ) {

                            if (image_url.size()-1 == one) {

                                init_Y = _dY;
                                rl2.setY(0);

                                init_Y = event.getY();
                                break;
                            }

                        }

                        respectiveOfPrespective_CENTRAL_LAYOUT(_dY);


                        break;

                    case MotionEvent.ACTION_UP:

                        Log.d("DOWN" , "ACTION_UP");

                        if(_dY==0 )
                        {
                            break;
                        }
                        releaseTouch();
//                        ((Default)getActivity()).viewPager.endFakeDrag();

                        break;

                    case MotionEvent.ACTION_CANCEL:
                        Log.d("DOWN" , "ACTION_CANCEL");
                        if(_dY==0 )
                        {
                            break;
                        }

                        releaseTouch();

//                        ((Default)getActivity()).viewPager.endFakeDrag();
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

        private  final int SWIPE_MIN_DISTANCE = (int) (MainActivity_FRAG_DS.this.getResources().getDisplayMetrics().heightPixels*.05f);
        private  final int SWIPE_THRESHOLD_VELOCITY = 500;
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {



            ((Default)getActivity()).toolbarPop();
            return true;
        }



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

        String pathy =   MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm,
                barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



        Uri uri1 = Uri.parse(pathy);

        ArrayList<Uri> imageUriArray = new ArrayList<Uri>();
        imageUriArray.add(uri1);

        Log.d("bhosadi url" , "" + uri1);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Visit: prakharnews.com!!");
        intent.setType("text/plain");
        intent.setType("image/jpeg");
        intent.setPackage("com.whatsapp");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);
        startActivity(intent);

    }


    void vibrate()
    {
        ((Vibrator)  getActivity().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(10);

    }


    ArrayList<String> head = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> image_url= new ArrayList<>();


    void addData(String h , String d , String u)
    {
        head.add(h);
        desc.add(d);
        image_url.add(u);

    }

void fillData()
{


    image_url = new ArrayList<>();
    head = new ArrayList<>();
    desc = new ArrayList<>();
    addData("",
            "" , "");



    addData("",
            "Election Commission of India will announce the schedule of the Bihar Assembly Election tomorrow. Election's will be likely held in the month of October & November",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT-fOaRRRXlAtDY-nclqrlibYw_fG316c9p2YmxR0dQ_fOcRWvJ9w");

    addData("",
            "Bihar elections: Samajwadi Party shuts door on Grand Alliance, to contest alone on all 243 seats, Wary of alliance with Congress, Mulayam snubs Lalu, Sharad",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRRZu78P6sZDHB-xl54vtnyPr5S0cuo8_A-dNmQJ-0yzIuZgeXOLQ");

    addData("",
            "Six Left parties join hands to fight 'saffron brigade', Lalu-Nitish alliance in Bihar polls",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSHRstVGLOxqmCFexYotunIsqLNf-ToLxhokx7cpK_1GQPHiSUu2Q");

    addData("",
            "'Faltu shouting', says Minister VK Singh on Pakistan army chief's threat\n",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRcUmjc7V5-O-yO6xBNlGOm2dj9cwP02Y73z5f0CDdYFkDJXLcW");

    addData("",
            "Mumbai ED officials and Delhi ED team in consultation on Lalit Modi Red Corner Notice issue which has been sent to Interpol for issuance",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcROeV8eE5BWEYCZMrvEhBnSTlt8e2PuzGn_No-PPxcWJ6TCdlkj");

    addData("",
            "Govt decision on Parliament session likely this week",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3OrJdmwPc0OHzhcc2ldrVPwXHgLdfVYSIKTpqajkmasteD7kB");

    addData("",
            "Care about your customers, Union Minister RS Prasad tells telecom companies",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQrJwKuSTeIznKyfuP8TfxlSXi3Z9eoouaEPq11WULQbHyhRLPS");

    addData("",
            "Indrani, her driver sent to judicial custody; DNA report says remains belong to Sheena Bora",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRXRMblNl8vceaTaAH7OKOwdo3dcfXuVeoxwhcOHwXFha4dicjrWQ");

    addData("",
            "Mumbai Police commissioner Rakesh Maria along with joint CP Deven Bharti reach Khar police station to interrogate Peter Mukherjea.",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSkj8Ot-qIWJ79nHeatbQf0dU41u8cLvurCCbeDf0lem3nRR5We3g");

    addData("",
            "Ex Union Minister A Raja was main conspirator in 2G scam, favoured firms, says CBI",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQS9EZIR1M5hjbX4GrKX6wU59uzY6yncb-BkfWslz3I3wUmpDdk");

    addData("",
            "" , "");


}

}
