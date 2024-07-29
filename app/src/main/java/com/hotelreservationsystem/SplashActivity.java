package com.hotelreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Animation rotate, alpha, translate, scale;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        imageView = findViewById(R.id.image);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(rotate);
            }
        });
        imageView.startAnimation(rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class
                ));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(translate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageView.startAnimation(alpha);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
