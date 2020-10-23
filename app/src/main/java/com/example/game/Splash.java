package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {


    private ImageView img;
    private TextView textView;
    Animation top_anim,bottom_anim;

    private static int SPLASH = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

            img = findViewById(R.id.img);
            textView = findViewById(R.id.textview_splash);


            top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
            bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);


            img.setAnimation(top_anim);
            textView.setAnimation(bottom_anim);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }
            },SPLASH);

    }
}