package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GynecologistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynecologist);
    }

    public void Gynecologist1(View view) {
        Intent intent=new Intent(this,Gynecologist1.class);
        startActivity(intent);
    }

    public void Gynecolgosit2(View view) {
        Intent intent=new Intent(this,Gynecologist2.class);
        startActivity(intent);
    }

    public void Gynecologist3(View view) {
        Intent intent=new Intent(this,Gynecologist3.class);
        startActivity(intent);
    }
}