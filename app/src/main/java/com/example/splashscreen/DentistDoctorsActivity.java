package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DentistDoctorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_doctors);
    }

    public void openDentist1Activity(View view) {
        Intent intent=new Intent(this,Dentist1.class);
        startActivity(intent);

    }

    public void openDentist2Activity(View view) {
        Intent intent=new Intent(this,Dentist2.class);
        startActivity(intent);
    }

    public void Dentist3(View view) {
        Intent intent=new Intent(this,Dentist3.class);
        startActivity(intent);
    }

    public void Dentist4(View view) {
        Intent intent=new Intent(this,Dentist4.class);
        startActivity(intent);
    }

    public void Dentist5(View view) {
        Intent intent=new Intent(this,Dentist5.class);
        startActivity(intent);
    }

    public void Dentist6(View view) {
        Intent intent=new Intent(this,Dentist6.class);
        startActivity(intent);
    }

    public void Dentist7(View view) {
        Intent intent=new Intent(this,Dentist7.class);
        startActivity(intent);
    }

    public void Dentist8(View view) {
        Intent intent=new Intent(this,Dentist8.class);
        startActivity(intent);
    }

    public void Dentist9(View view) {
        Intent intent=new Intent(this,Dentist9.class);
        startActivity(intent);
    }

    public void Dentist10(View view) {
        Intent intent=new Intent(this,Dentist10.class);
        startActivity(intent);
    }

    public void Dentist11(View view) {
        Intent intent=new Intent(this,Dentist11.class);
        startActivity(intent);
    }

    public void Dentist12(View view) {
        Intent intent=new Intent(this,Dentist12.class);
        startActivity(intent);
    }
}