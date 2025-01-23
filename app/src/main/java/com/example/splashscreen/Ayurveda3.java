package com.example.splashscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Ayurveda3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayurveda3);
    }

    public void onPhoneNumberClick(View view) {
        String phoneNumber = "+1-541-754-3011"; // You can get this dynamically if needed
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void onLocationClick(View view) {
        String location = "Beside Sai Srinavasa residency,Nellore";
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void booknow(View view) {
        // Implement the booking action here
        Intent intent = new Intent(this, ba3.class); // Replace with actual activity
        startActivity(intent);
    }
}
