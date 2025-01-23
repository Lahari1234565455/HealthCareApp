package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EyecareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyecare);
    }

    public void Eye1(View view) {
        Intent intent=new Intent(this,Eye1.class);
        startActivity(intent);
    }

    public void Eye2(View view) {
        Intent intent=new Intent(this,Eye2.class);
        startActivity(intent);
    }

    public void Eye3(View view) {
        Intent intent=new Intent(this,Eye3.class);
        startActivity(intent);
    }

    public void Eye4(View view) {
        Intent intent=new Intent(this,Eye4.class);
        startActivity(intent);
    }

    public void Eye5(View view) {
        Intent intent=new Intent(this,Eye5.class);
        startActivity(intent);
    }
}