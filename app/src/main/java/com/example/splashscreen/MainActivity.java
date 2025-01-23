package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtDoctor,txtBook;
    RelativeLayout relativeLayout;
    Animation txtAnimation,layoutAnimation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtAnimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.fall_down);
        layoutAnimation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_to_top);
        setContentView(R.layout.activity_main);
        txtDoctor= (TextView) findViewById(R.id.txtDoctor);
        txtBook= (TextView) findViewById(R.id.txtBook);
        relativeLayout= (RelativeLayout) findViewById(R.id.relMain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layoutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtDoctor.setVisibility(View.VISIBLE);
                        txtBook.setVisibility(View.VISIBLE);
                        txtDoctor.setAnimation(txtAnimation);
                        txtBook.setAnimation(txtAnimation);
                    }
                },500);



            }
        },500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(MainActivity.this, SignUpActivity.class);
               startActivity(intent);
            }
        },6000);

    }
}