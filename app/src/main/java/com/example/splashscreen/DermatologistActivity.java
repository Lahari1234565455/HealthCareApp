package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DermatologistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dermatologist);
    }

    public void Dermatologist1(View view) {
        Intent intent=new Intent(this,Dermatologist1.class);
        startActivity(intent);
    }

    public void Dermatologist2(View view) {
        Intent intent=new Intent(this,Dermatologist2.class);
        startActivity(intent);
    }

    public void Dermatologist3(View view) {
        Intent intent=new Intent(this,Dermatologist3.class);
        startActivity(intent);
    }

    public void Dermatologist4(View view) {
        Intent intent=new Intent(this,Dermatologist4.class);
        startActivity(intent);
    }
}