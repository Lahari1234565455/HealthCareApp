package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardiologistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiologist);
    }

    public void Cardiologist1(View view) {
        Intent intent=new Intent(this,cardiologist1.class);
        startActivity(intent);
    }

    public void Cardiologist2(View view) {
        Intent intent=new Intent(this,cardiologist2.class);
        startActivity(intent);
    }

    public void Cardiologist3(View view) {
        Intent intent=new Intent(this,cardiologist3.class);
        startActivity(intent);
    }

    public void Cardiologist4(View view) {
        Intent intent=new Intent(this,cardiologist4.class);
        startActivity(intent);
    }
}