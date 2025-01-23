package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Sendmessage6 extends AppCompatActivity {
    EditText t0;
    Button send;
    ImageView confirmationIcon;

    private static final int REQUEST_SEND_SMS = 1;
    private static final String CONFIRMATION_MESSAGE = "Your Appointment has been confirmed";
    private static final String DOCTOR_PHONE_NUMBER = "+91 9908443770";
    private static final String DOCTOR_MESSAGE_TEMPLATE = "New Appointment Details:\nDate: %s\nTime: %s\nPhone: %s\nDescription: General Checkup";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage6);
        t0 = findViewById(R.id.t0);
        send = findViewById(R.id.send);
        confirmationIcon = findViewById(R.id.confirmation_icon);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(Sendmessage6.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Sendmessage6.this,
                                new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                    } else {
                        showConfirmationIconAndSendSMS();
                    }
                } else {
                    showConfirmationIconAndSendSMS();
                }
            }
        });
    }

    private void showConfirmationIconAndSendSMS() {
        confirmationIcon.setVisibility(View.VISIBLE);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(confirmationIcon, "scaleX", 0.0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(confirmationIcon, "scaleY", 0.0f, 1.0f);
        scaleX.setDuration(500);
        scaleY.setDuration(500);

        scaleX.start();
        scaleY.start();

        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                sendSMS();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }

    private void sendSMS() {
        // Retrieve the selected date and slot from the Intent
        Intent intent = getIntent();
        String date = intent.getStringExtra("selectedDate");
        String time = intent.getStringExtra("selectedSlot");

        String phoneNumber = t0.getText().toString();
        String doctorMessage = String.format(DOCTOR_MESSAGE_TEMPLATE, date, time, phoneNumber);

        if (!phoneNumber.isEmpty()) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, CONFIRMATION_MESSAGE, null, null);
                smsManager.sendTextMessage(DOCTOR_PHONE_NUMBER, null, doctorMessage, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();

                // Optionally, pass the appointment details to the Confirmation activity again
                Intent confirmIntent = new Intent(Sendmessage6.this, CD6.class);
                confirmIntent.putExtra("selectedDate", date);
                confirmIntent.putExtra("selectedSlot", time);
                startActivity(confirmIntent);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "SMS Failed to Send", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showConfirmationIconAndSendSMS();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
