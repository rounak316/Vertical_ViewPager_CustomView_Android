package com.example.prakhar.tester2;

import android.app.ProgressDialog;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class MainActivity_FRAG extends Fragment implements View.OnTouchListener {

View parent_view ;
    private RestAdapter adapter;
    private int hei= 0, wid= 0;

    void getGT_SN(int i)
    {

        adapter.create(RetrofitClient.class).getGT_SL(

                i

                ,

                new retrofit.Callback<ArrayList<_news>>() {
                    @Override
                    public void success(ArrayList<_news> newses, retrofit.client.Response response) {
                        Log.d("BHOSADI 1", "" + newses.size());



                        String hg =   new Gson().toJson(newses);

                        Log.d("BHOSADI", "CHUTIYA "+ hg);

                        getActivity().getSharedPreferences("news" , 1).edit().putString("sexynews" , hg.toString()).commit();



                        reset();


                        if (newses.size() == 0) {

                            addData("", "", "","");
                            addData("", "", "","");

                            addData("", "", "","");


                            return;

                        }


                        addData("", "", "","");
                        for (int i = 0; i < newses.size(); i++) {

                            _news tmp = newses.get(i);

                            addData(tmp.head, tmp.desc, tmp.img ,tmp.timestamp );


                        }
                        addData("", "", "" , "");


                        initialize();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("BHOSADI", "CHUTIYA " + error);




                        String tmp =   getActivity().getSharedPreferences("news" , 1).getString("sexynews", "[]");


             fillData();

                    }
                });

    }
    void getLT_SN(int i)
    {


        adapter.create(RetrofitClient.class).getLT_SL(

                i

                ,

                new retrofit.Callback<ArrayList<_news>>() {
                    @Override
                    public void success(ArrayList<_news> newses, retrofit.client.Response response) {
                        Log.d("BHOSADI 1", "" + newses.size());



                        String hg =   new Gson().toJson(newses);

                        Log.d("BHOSADI", "CHUTIYA " + hg);

                        getActivity().getSharedPreferences("news" , 1).edit().putString("sexynews" , hg.toString()).commit();




reset();

                        if (newses.size() == 0) {

                            addData("", "", "","");
                            addData("", "", "","");

                            addData("", "", "", "");

                            initialize();

                            Log.d("BHOSADI", "CHUTIYA ZERO " );
                            return;

                        }


                        addData("", "", "","");
                        for (int i = 0; i < newses.size(); i++) {

                            _news tmp = newses.get(i);

                            addData(tmp.head, tmp.desc, tmp.img , tmp.timestamp);


                        }
                        addData("", "", "","");


                        initialize();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("BHOSADI", "CHUTIYA " + error);


fillData();

                    }
                });

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup vg = (ViewGroup) view;
        vg.setClipChildren(false);
        vg.setClipToPadding(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hei= getActivity().getResources().getDisplayMetrics().heightPixels;

        wid= getActivity().getResources().getDisplayMetrics().widthPixels;




        adapter =((app)getActivity().getApplication()).adapter;

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


        rl.setAnimationCacheEnabled(true);
        rl1.setAnimationCacheEnabled(true);

        rl2.setAnimationCacheEnabled(true);

        rl3.setAnimationCacheEnabled(true);




        ViewGroup vg = container;
        vg.setClipChildren(false);
        vg.setClipToPadding(false);
        parent_view = view;

        listner = new GestureDetector(getActivity() , new GestureListner());


        viewId = new ArrayList<>();
        viewId.add(R.id.rl1);  viewId.add(R.id.rl2);  viewId.add(R.id.rl3);




        rl.setOnTouchListener(this

        );

        fillData(); // initially set size to 3 while the request is executing to fetch data



        Log.d("sexysize fillData", "" + sexy_news_array.size());
        init(); // set up the screen on first visit

        Log.d("sexysize init", "" + sexy_news_array.size());

//        initialize();

        Log.d("sexysize initialize", "" + sexy_news_array.size());




  return  view;
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        getLT_SN(0);
//    }




    @Override
    public void onResume() {
        super.onResume();
        getLT_SN(0);
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
        headlines.setText(  sexy_news_array.get(cnt+2).head );

        headlines = (TextView) rl2.findViewById(R.id.headlines);
        headlines.setText(sexy_news_array.get(cnt + 1).head);

        headlines = (TextView) rl3.findViewById(R.id.headlines);
        headlines.setText(sexy_news_array.get(cnt).head);



        TextView timestamp = (TextView) rl1.findViewById(R.id.timestamp);
        timestamp.setText(  sexy_news_array.get(cnt+2).timestamp );

        timestamp = (TextView) rl2.findViewById(R.id.timestamp);
        timestamp.setText( sexy_news_array.get(cnt+1).timestamp    );

        timestamp = (TextView) rl3.findViewById(R.id.timestamp);
        timestamp.setText(sexy_news_array.get(cnt).timestamp);


        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText(sexy_news_array.get(cnt+2).desc);

        detailed = (TextView) rl2.findViewById(R.id.detailed);
        detailed.setText(sexy_news_array.get(cnt+1).desc);

        detailed = (TextView) rl3.findViewById(R.id.detailed);
        detailed.setText(sexy_news_array.get(cnt).desc);

        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {


//




            final int tmp = two;

            String url = sexy_news_array.get(tmp).image_url;

            try {
           target_2 = new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {




                        if(view_map.containsKey(tmp))
                        {
                            ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);

                            ((ImageView) view_map.get(tmp)).startAnimation(AnimationUtils.loadAnimation(getActivity() , R.anim.abc_fade_in));


                            Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                        }
                        else
                        {


                            Log.d("PICAASO onBitmapLoaded" , " misssing place" + tmp + " "  + view_map.keySet() );
                        }

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                        if(view_map.containsKey(tmp))
                        {
                            ((ImageView) view_map.get(tmp)).setImageDrawable(errorDrawable);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                        }

                        Log.d("PICAASO onBitmapFailed" , " " + tmp + " "  );


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                        if(view_map.containsKey(tmp))
                        {
                            ((ImageView) view_map.get(tmp)).setImageDrawable(placeHolderDrawable);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                        }


                        Log.d("PICAASO onPrepareLoad 1" , " " + tmp + " " + sexy_news_array.get(tmp).image_url );

                    }
                };



            }
            catch (Exception e)
            {

                Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + sexy_news_array.get(tmp).image_url );

            }


            built.with(getActivity().getApplicationContext())

                    .load(url)
//                    .priority(Picasso.Priority.HIGH)

                              .transform(new BitmapTransformation(wid, hei))
                    .placeholder(R.drawable.darker)

                    .error(R.drawable.darker)
                    .placeholder(R.drawable.darker)
                    .error(R.drawable.darker)

                    .into(target_2);





            final int tmp2 = one;
            url =  sexy_news_array.get(tmp2).image_url;


//            Log.d("PICAASO ERROR start 2 " ,  " " + tmp2 + " " + url+ " ---> " + image_url );
            try {
                target_1 = new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                        if(view_map.containsKey(tmp2))
                        {
                            ((ImageView) view_map.get(tmp2)).setImageBitmap(bitmap);
                            ((ImageView) view_map.get(tmp2)).startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
                            Log.d("PICAASO onBitmapLoaded", " " + tmp2 + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp2 + " " + view_map.keySet() );
                        }

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                        if(view_map.containsKey(tmp2))
                        {
                            ((ImageView) view_map.get(tmp2)).setImageDrawable(errorDrawable);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp2 + " ");

                        }
                        else
                        {


                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp2 + " " + view_map.keySet() );
                        }


                        Log.d("PICAASO onBitmapFailed" , " " + tmp2 + " "  );


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                        if(view_map.containsKey(tmp2))
                        {
                            ((ImageView) view_map.get(tmp2)).setImageDrawable(placeHolderDrawable);

                            Log.d("PICAASO onBitmapLoaded", " " + tmp2 + " ");

                        }
                        else
                        {
                            Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp2 + " " + view_map.keySet() );
                        }



                        Log.d("PICAASO onPrepareLoad 2" , " " + tmp2 + " "  + sexy_news_array.get(tmp2).image_url);

                    }
                };



            }
            catch (Exception e)
            {

//                Log.d("PICAASO ERROR" , e + " " + tmp2 + " " + url+ " ---> " + image_url );
                Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + sexy_news_array.get(tmp).image_url );

            }


//


            built.with(getActivity().getApplicationContext())
                    .load(url)
//                    .priority(Picasso.Priority.HIGH)
                        .transform(new BitmapTransformation(wid, hei))
                    .placeholder(R.drawable.darker)
                    .error(R.drawable.darker)
                    .into(target_1);





        }



    }
    com.squareup.picasso.Target target_1;

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


        Log.d(cnt + "___CNT", one + " " + two + " " + three);




        rl1.clearAnimation();
        rl2.clearAnimation();
        rl3.clearAnimation();
        rl1 = (FrameLayout) rl.getChildAt(0);






        final TextView headlines = (TextView) rl1.findViewById(R.id.headlines);
        headlines.setText( sexy_news_array.get( cnt ).head);

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText( sexy_news_array.get(cnt).desc);
        TextView timestamp = (TextView) rl1.findViewById(R.id.timestamp);
        timestamp.setText( sexy_news_array.get(cnt).timestamp);



        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {


//








//







        }


      //  ((ImageView)rl1.findViewById(R.id.poster)).setImageBitmap(null);

//        ((ImageView)rl1.findViewById(R.id.poster)).setImageBitmap(null);
        ((ImageView)rl1.findViewById(R.id.poster)).setImageResource(android.R.color.transparent);


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
        String url = sexy_news_array.get(tmp).image_url;

        try {
          target_1 = new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);

                        if(getActivity()!=null)


                        ((ImageView) view_map.get(tmp)).startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageDrawable(errorDrawable);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }


                    Log.d("PICAASO onBitmapFailed" , " " + tmp + " "   );


                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageDrawable(placeHolderDrawable);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }


                    Log.d("PICAASO onPrepareLoad" , " " + tmp + " " + sexy_news_array.get(tmp).image_url  );

                }
            };


            built.with(getActivity().getApplicationContext())
                    .load(url)
//                    .priority(Picasso.Priority.HIGH)
                    .transform(new BitmapTransformation(wid, hei))
                    .placeholder(R.drawable.darker)
                    .error(R.drawable.darker)
                    .into(target_1);
        }
        catch (Exception e)
        {
            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + sexy_news_array.get(tmp).image_url );

//            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + image_url );

        }

    }
    HashMap<Integer , View> view_map;
    com.squareup.picasso.Target target_2;

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
        headlines.setText(  sexy_news_array.get(cnt).head );

        TextView detailed = (TextView) rl1.findViewById(R.id.detailed);
        detailed.setText( sexy_news_array.get(cnt).desc);

        TextView timestamp = (TextView) rl1.findViewById(R.id.timestamp);
        timestamp.setText( sexy_news_array.get(cnt).timestamp);


        final ImageView poster= (ImageView) rl1.findViewById(R.id.poster);

        poster.setTag((cnt));

        {

















//






        }

        ((ImageView)rl1.findViewById(R.id.poster)).setImageResource(android.R.color.transparent);



//        ((ImageView)rl1.findViewById(R.id.poster)).setImageBitmap(null);


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
        String url = sexy_news_array.get(tmp).image_url;

        try {
             target_2 = new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageBitmap(bitmap);
                        if(getActivity()!=null)
                        ((ImageView) view_map.get(tmp)).startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageDrawable(errorDrawable);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                    Log.d("PICAASO onBitmapFailed" , " " + tmp + " "   );


                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {


                    if(view_map.containsKey(tmp))
                    {
                        ((ImageView) view_map.get(tmp)).setImageDrawable(placeHolderDrawable);

                        Log.d("PICAASO onBitmapLoaded", " " + tmp + " ");

                    }
                    else
                    {
                        Log.d("PICAASO onBitmapLoaded", " misssing place" + tmp + " " + view_map.keySet() );
                    }

                    Log.d("PICAASO onPrepareLoad" , " " + tmp + " "  + sexy_news_array.get(tmp).image_url);

                }
            };

         //   Picasso.with(getContext()).setIndicatorsEnabled(true);
            built.with(getActivity().getApplicationContext())

                    .load(url)


//                    .priority(Picasso.Priority.HIGH)
                    .transform(new BitmapTransformation(wid, hei))
                    .placeholder(R.drawable.darker)
                    .error(R.drawable.darker)
                    .into(target_2);
        }
        catch (Exception e)
        {

            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + sexy_news_array.get(tmp).image_url );


//            Log.d("PICAASO ERROR" , e + " " + tmp + " " + url+ " ---> " + image_url );

        }



    }

    int one , two , three;

    GestureDetector listner;

    Picasso built;
ArrayList<Integer> viewId;
    FrameLayout rl1,rl2,rl3, rl;


    protected void onCreaste(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        OkHttpDownloader sdsa = new OkHttpDownloader(getActivity(), 1000000);




        Picasso.Builder builder = new Picasso.Builder(getActivity());
        builder.downloader(sdsa);
        built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);









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
            rl.setOnTouchListener(MainActivity_FRAG.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });




    trans_one.setInterpolator(new LinearInterpolator());
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

            rl.setOnTouchListener(MainActivity_FRAG.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
    trans_one.setInterpolator(new LinearInterpolator());
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
                rl.setOnTouchListener(MainActivity_FRAG.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new LinearInterpolator());
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

                rl.setOnTouchListener(MainActivity_FRAG.this);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        trans_one.setInterpolator(new LinearInterpolator());
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

            if (one == sexy_news_array.size() - 1) {

                init_Y = _dY;
                rl2.setY(0);


                return;
            }

        }

        rl.setOnTouchListener(null);


        rl1.clearAnimation();
        rl2.clearAnimation();
        float chemical_x =  Math.abs(_dY / rl.getHeight());


        chemical_x = 0;
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


                        rl.setOnTouchListener(MainActivity_FRAG.this);


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

                        rl.setOnTouchListener(MainActivity_FRAG.this);

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
        view_map.put(three, ((ImageView) rl3.findViewById(R.id.poster)));


        Log.d("PICAASO COUNTER BABY", "" + view_map.keySet());





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

        rl2.findViewById(R.id.forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BIND" , "");
                String shareBody = ((TextView)rl2.findViewById(R.id.headlines)).getText()  + "\n\n" + ((TextView)rl2.findViewById(R.id.detailed)).getText() +"\n" + "Download our app 'Vidhayaka News' from PlayStore, and stay updated. ";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share News");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share News"));
            }
        });





    }
    float _DURATION = 300;


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

                            if (sexy_news_array.size()-1 == one) {

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

        private  final int SWIPE_MIN_DISTANCE = (int) (MainActivity_FRAG.this.getResources().getDisplayMetrics().heightPixels*.05f);
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

      ProgressDialog dia = ProgressDialog.show(getActivity(),"Please wait","");



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
        intent.putExtra(Intent.EXTRA_TEXT, "Visit: vidhayakanews.com!!");
        intent.setType("text/plain");
        intent.setType("image/jpeg");
        intent.setPackage("com.whatsapp");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);



        startActivity(intent);

        dia.dismiss();

    }


    void vibrate()
    {
        ((Vibrator)  getActivity().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(10);

    }



    class sexy_news
    {

        String head,desc,image_url,ID, timestamp;

    }

//    ArrayList<String> head = new ArrayList<>();
//    ArrayList<String> desc = new ArrayList<>();
//    ArrayList<String> image_url= new ArrayList<>();


    ArrayList<sexy_news> sexy_news_array= new ArrayList<>();




void reset()
{

    sexy_news_array= new ArrayList<>();


}

    void addData(String h , String d , String u , String timestamp )
    {


        sexy_news ne = new sexy_news();
        ne.desc = d;
        ne.head = h;
        ne.image_url = "http://app.newsportal.co.in/uploads/" + u + ".jpg";
ne.timestamp = timestamp;

        sexy_news_array.add(ne);

        Log.d("sexysize", "" + sexy_news_array.size());

    }

void fillData()
{




    String tmp =   getActivity().getSharedPreferences("news" , 1).getString("sexynews", "[]");


    try {
        JSONArray ar = new JSONArray(tmp);

        ArrayList<sexy_news> tmp_news = new ArrayList<sexy_news>();




        if (ar.length() == 0) {


            for(int i=0;i<3;i++) {

                sexy_news headlines = new sexy_news();
                headlines.head = "";
                headlines.desc = "";
                headlines.ID = "";
                tmp_news.add(headlines);

            }

            reset();

            sexy_news_array = tmp_news;






            initialize();

            return;

        }




        sexy_news headlines = new sexy_news();
        headlines.head = "";
        headlines.desc = "";
        headlines.ID = "";
        tmp_news.add(headlines);
        for(int i=0;i<ar.length();i++)
        {


            headlines = new sexy_news();
            headlines.head =   ar.getJSONObject(i).getString("head");
            headlines.desc =   ar.getJSONObject(i).getString("desc");
            headlines.ID =   ar.getJSONObject(i).getString("id");

            tmp_news.add(headlines);


        }
         headlines = new sexy_news();
        headlines.head = "";
        headlines.desc = "";
        headlines.ID = "";
        tmp_news.add(headlines);



        reset();

        sexy_news_array = tmp_news;






        initialize();



    } catch (JSONException e) { 
        e.printStackTrace();

        ArrayList<sexy_news> tmp_news = new ArrayList<sexy_news>();
   {
            for(int i=0;i<3;i++) {

                sexy_news headlines = new sexy_news();
                headlines.head = "";
                headlines.desc = "";
                headlines.ID = "";
                tmp_news.add(headlines);

            }




        }
        reset();

        sexy_news_array = tmp_news;



    }



}

}
