package com.example.orderts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class Splash extends AppCompatActivity {

    Animation leftAnim, rightAnim, topAnim;
    GifImageView gifImageView;
    TextView nameapp, slogan;

    private static int SPLASH_SCREEN = 2600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);

        gifImageView = findViewById(R.id.gifImageView);
        nameapp = findViewById(R.id.nameapp);
        slogan = findViewById(R.id.slogan);

        gifImageView.setAnimation(leftAnim);
        nameapp.setAnimation(topAnim);
        slogan.setAnimation(rightAnim);
    }
}
