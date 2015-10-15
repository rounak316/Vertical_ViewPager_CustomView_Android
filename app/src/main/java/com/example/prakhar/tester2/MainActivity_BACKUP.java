package com.example.prakhar.tester2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity_BACKUP extends AppCompatActivity implements View.OnTouchListener {

    GestureDetector listner;

ArrayList<Integer> viewId;
    FrameLayout rl1,rl2,rl3, rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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





        rl.setOnTouchListener( this

             );


init();
    }

    void swap_one()
    {


        rl1.clearAnimation();
        rl2.clearAnimation();
        rl3.clearAnimation();
        rl1 = (FrameLayout) rl.getChildAt(0);
        rl.removeViewAt(0);


        rl.addView(rl1, 2);
rl1 = (FrameLayout) rl.getChildAt(0);

        rl2 = (FrameLayout) rl.getChildAt(1);
        rl3 = (FrameLayout) rl.getChildAt(2);




        for(int i=0;i<3;i++)
        {
            Log.d("VIEW_ID" , "" + rl.getChildAt(i).getId() + " " );
        }

        init();

    }
    void swap_two()
    {


        rl1.clearAnimation();
        rl2.clearAnimation();
        rl3.clearAnimation();

        rl1 = (FrameLayout) rl.getChildAt(2);
        rl.removeViewAt(2);


        rl.addView(rl1, 0);
        rl1 = (FrameLayout) rl.getChildAt(0);

        rl2 = (FrameLayout) rl.getChildAt(1);
        rl3 = (FrameLayout) rl.getChildAt(2);




        for(int i=0;i<3;i++)
        {
            Log.d("VIEW_ID" , "" + rl.getChildAt(i).getId() + " " );
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
    trans_one.setDuration((long) ( Math.max( 10 ,_DURATION * TIME_FACTOR)));
Log.d("DURATION" , "" +  (_DURATION * TIME_FACTOR) + " " + TIME_FACTOR);
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
            rl.setOnTouchListener(MainActivity_BACKUP.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });




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

            rl.setOnTouchListener(MainActivity_BACKUP.this);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });


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
                rl.setOnTouchListener(MainActivity_BACKUP.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


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

                rl.setOnTouchListener(MainActivity_BACKUP.this);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        rl3.startAnimation(trans_one);
        rl2.startAnimation(scale_anim);

    }
}
    }
    void releaseTouch_fling(boolean FlingToTop)
    {


        rl1.clearAnimation();
        rl2.clearAnimation();
        float chemical_x =  Math.abs(_dY / rl.getHeight());

float _DURATION = 170;

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


                        rl.setOnTouchListener(MainActivity_BACKUP.this);


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

                        rl.setOnTouchListener(MainActivity_BACKUP.this);

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

    void init()
    {
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

    }
    int _DURATION = 250;


            @Override
            public boolean onTouch(View v, MotionEvent event) {






                if(listner.onTouchEvent(event))
                {

                    Log.d("DOWN" , "GESTURE");


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

        private static final int SWIPE_MIN_DISTANCE = 80;
        private static final int SWIPE_THRESHOLD_VELOCITY = 800;




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

}
