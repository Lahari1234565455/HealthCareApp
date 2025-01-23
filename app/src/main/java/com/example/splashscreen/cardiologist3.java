package com.example.splashscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class cardiologist3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiologist3);
    }

    public void onPhoneNumberClick(View view) {
        String phoneNumber = "1860 500 1066"; // You can get this dynamically if needed
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void onLocationClick(View view) {
        String location = "16/ 111/ 1133, Muttukur Road, Pinakini Avenue, Ramji Nagar, Nellore, AP 524004";
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void booknow(View view) {
        Intent intent = new Intent(cardiologist3.this, BC3.class);
        startActivity(intent);
    }
}
