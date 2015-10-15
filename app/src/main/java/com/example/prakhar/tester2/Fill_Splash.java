package com.example.prakhar.tester2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Fill_Splash extends AppIntro {

    @Override
    public void init(Bundle bundle) {

        addSlide(AppIntroFragment.newInstance("Wall", "Descriptive news with an image", R.drawable.blog, (Color.parseColor("#795548"))));
        //  addSlide(AppIntroFragment.newInstance("News to the point", "We provide facts, not stories", R.drawable.img_one, (Color.parseColor("#FFB1F452"))));
        addSlide(AppIntroFragment.newInstance("News", "Breaking News", R.drawable.broadcast, (Color.parseColor("#f44336"))));
        //  addSlide(AppIntroFragment.newInstance("Offline support", "Lost connectivty? Not an issue", R.drawable.img_three, (Color.parseColor("#2F7ABF"))));


        setVibrate(true);

        setBarColor(Color.parseColor("#2380FD"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        setZoomAnimation();
        setVibrateIntensity(30);


        setSkipText("Post Blog");
        setDoneText("Post News");

    }

    @Override
    public void onSkipPressed() {


        // Post Blog
        startActivity(new Intent(this , add_special_news.class));
        finish();
        // Login

    }


    @Override
    public void onDonePressed() {

        startActivity(new Intent(this , headline_add.class));
        finish();

    }
}
