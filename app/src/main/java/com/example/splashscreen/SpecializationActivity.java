package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SpecializationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization);
    }

    public void onImageClick(View view) {
        Intent intent=new Intent(this, DentistDoctorsActivity.class);
        startActivity(intent);
    }

    public void dentist1(View view) {

    }


    public void ImageClick2(View view) {
        Intent intent=new Intent(this,CardiologistActivity.class);
        startActivity(intent);
    }

    public void onImageClick3(View view) {
        Intent intent=new Intent(this,DermatologistActivity.class);
        startActivity(intent);
    }

    public void ImageClick4(View view) {
        Intent intent=new Intent(this,AyurvedaActivity.class);
        startActivity(intent);
    }

    public void onImageClick5(View view) {
        Intent intent=new Intent(this,EyecareActivity.class);
        startActivity(intent);
    }

    public void onImageClick7(View view) {
        Intent intent=new Intent(this,OrthopedicActivity.class);
        startActivity(intent);
    }

    public void ImageClick8(View view) {
        Intent intent=new Intent(this,UrologistActivity.class);
        startActivity(intent);
    }

    public void onImageClickl(View view) {
        Intent intent=new Intent(this,GynecologistActivity.class);
        startActivity(intent);
    }

    public void image6(View view) {
        Intent intent=new Intent(this,OrthopedicActivity.class);
        startActivity(intent);
    }
}