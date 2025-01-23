package com.example.splashscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Urologist4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urologist4);
    }

    public void onPhoneNumberClick(View view) {
        String phoneNumber = "+91 6366530173"; // You can get this dynamically if needed
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void onLocationClick(View view) {
        String location = "Nellore, 16/111, 1133, Muthukur Rd, Pinakini Avenue, Ramji Nagar, Nellore, Andhra Pradesh 524004.";
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void booknow(View view) {
        // Implement the booking action here
        Intent intent = new Intent(this, BU4.class); // Replace with actual activity
        startActivity(intent);
    }
}
