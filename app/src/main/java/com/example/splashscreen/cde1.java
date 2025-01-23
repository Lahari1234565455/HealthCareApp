package com.example.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class cde1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cde1);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String date = intent.getStringExtra("selectedDate");
        String slot = intent.getStringExtra("selectedSlot");

        // Update UI with the selected date and slot
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView dateTextView = findViewById(R.id.date_display);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView slotTextView = findViewById(R.id.slot_display);

        if (date != null && slot != null) {
            dateTextView.setText("Date: " + date);
            slotTextView.setText("Slot: " + slot);
        }
    }

    public void confirm(View view) {
        TextView dateTextView = findViewById(R.id.date_display);
        TextView slotTextView = findViewById(R.id.slot_display);

        String date = dateTextView.getText().toString().replace("Date: ", "");
        String slot = slotTextView.getText().toString().replace("Slot: ", "");

        // Pass the date and slot to Sendmessage activity
        Intent intent = new Intent(this, Sendmessage17.class);
        intent.putExtra("selectedDate", date);
        intent.putExtra("selectedSlot", slot);
        startActivity(intent);
    }
}
