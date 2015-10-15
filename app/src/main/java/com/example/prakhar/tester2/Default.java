package com.example.prakhar.tester2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

/**
 * Created by Prakhar on 9/26/2015.
 */
public class Default extends AppCompatActivity {
    ViewPager viewPager;
    View parent;
    boolean visible_tool = true;

    private String imgDecodableString="";




View iv3,iv2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.default_act);


iv3 = findViewById(R.id.iv3);
        iv2 =  findViewById(R.id.iv2);



View pop = findViewById(R.id.iv);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Default.this,
                      splash.class);
Log.d("CLCKDS", "DAD");
                galleryIntent.putExtra("intention" , true);
// Start the Intent
            startActivity(galleryIntent);

                finish();
            }
        });


        parent = findViewById(R.id.parent);

toolbar = findViewById(R.id.toolbar);

        ViewGroup vg = (ViewGroup) getWindow().getDecorView().getRootView();



        vg.setClipChildren(false);
        vg.setClipToPadding(false);

         viewPager = (ViewPager) findViewById(R.id.default_act);



        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 1)
                    return new MainActivity_FRAG();

                return new list_news();
            }

            @Override
            public int getCount() {
                return 2;
            }
        });



        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(  0, true);

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(  1, true);
            }
        });

    }

View toolbar;

    public void toolbarPop() {
        visible_tool = !visible_tool;

if(1==1)
        return;
        if(visible_tool )
        {

            toolbar.startAnimation(AnimationUtils.loadAnimation(this , R.anim.abc_slide_in_top));
            toolbar.setVisibility(View.VISIBLE);
        }
        else
        {
            toolbar.startAnimation(AnimationUtils.loadAnimation(this , R.anim.abc_slide_out_top));

            toolbar.setVisibility(View.INVISIBLE);

        }




    }






    @Override
    protected void onStart() {
        super.onStart();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.share);















    }
}
