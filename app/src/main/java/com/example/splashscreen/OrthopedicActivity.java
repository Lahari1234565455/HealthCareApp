package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OrthopedicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orthopedic);
    }

    public void Orthopedic1(View view) {
        Intent intent=new Intent(this,Orthopedic1.class);
        startActivity(intent);
    }

    public void Orthopedic2(View view) {
        Intent intent=new Intent(this,Orthopedic2.class);
        startActivity(intent);
    }
}