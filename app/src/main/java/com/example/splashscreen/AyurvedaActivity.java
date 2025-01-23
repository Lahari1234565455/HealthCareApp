package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AyurvedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayurveda);
    }

    public void Ayurveda1(View view) {
        Intent intent=new Intent(this,Ayurveda1.class);
        startActivity(intent);
    }

    public void Ayurveda2(View view) {
        Intent intent=new Intent(this,Ayurveda2.class);
        startActivity(intent);
    }

    public void Ayurveda3(View view) {
        Intent intent=new Intent(this,Ayurveda3.class);
        startActivity(intent);
    }

    public void Ayurveda4(View view) {
        Intent intent=new Intent(this,Ayurveda4.class);
        startActivity(intent);
    }

    public void Ayurveda5(View view) {
        Intent intent=new Intent(this,Ayurveda5.class);
        startActivity(intent);
    }
}