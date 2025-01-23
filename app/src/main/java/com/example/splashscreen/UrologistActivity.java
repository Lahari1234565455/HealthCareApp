package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UrologistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urologist);
    }

    public void Urologist1(View view) {
        Intent intent=new Intent(this,Urologist1.class);
        startActivity(intent);
    }

    public void Urologist2(View view) {
        Intent intent=new Intent(this,Urologist2.class);
        startActivity(intent);
    }

    public void Urologist3(View view) {
        Intent intent=new Intent(this,Urologist3.class);
        startActivity(intent);
    }

    public void Urologist4(View view) {
        Intent intent=new Intent(this,Urologist4.class);
        startActivity(intent);
    }
}