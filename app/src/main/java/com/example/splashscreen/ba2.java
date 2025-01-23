package com.example.splashscreen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ba2 extends AppCompatActivity {

    private String selectedDate = null;
    private String selectedSlot = null;
    private Button selectedSlotButton = null;
    private final int SELECTED_SLOT_COLOR = Color.parseColor("#FFFF00"); // Yellow for selected slots
    private final int DEFAULT_SLOT_COLOR = Color.parseColor("#E0F7FA"); // Default color for unselected slots (matches your design)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba2);

        TextView dateDisplay = findViewById(R.id.date_display);
        Button datePickerButton = findViewById(R.id.date_picker_button);

        // Set DatePicker Button Click Listener
        datePickerButton.setOnClickListener(v -> showDatePicker(dateDisplay));

        // Initialize slot buttons and set click listeners
        initializeSlotButtons();
    }

    private void showDatePicker(TextView dateDisplay) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            dateDisplay.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void initializeSlotButtons() {
        int[] slotIds = {
                R.id.morning_slot_1, R.id.morning_slot_2, R.id.morning_slot_3, R.id.morning_slot_4,
                R.id.morning_slot_5, R.id.morning_slot_6, R.id.morning_slot_7, R.id.morning_slot_8,
                R.id.morning_slot_9, R.id.morning_slot_10, R.id.morning_slot_11, R.id.morning_slot_12,
                R.id.evening_slot_1, R.id.evening_slot_2, R.id.evening_slot_3, R.id.evening_slot_4,
                R.id.evening_slot_5, R.id.evening_slot_6, R.id.evening_slot_7, R.id.evening_slot_8,
                R.id.evening_slot_9, R.id.evening_slot_10,R.id.evening_slot_11,R.id.evening_slot_11,
                R.id.evening_slot_12,R.id.evening_slot_13,R.id.evening_slot_14,R.id.evening_slot_15,
                R.id.evening_slot_15,R.id.evening_slot_16,R.id.evening_slot_16, R.id.evening_slot_17,
                R.id.evening_slot_17,R.id.evening_slot_18, R.id.evening_slot_20, R.id.evening_slot_21
        };
        for (int id : slotIds) {
            Button slotButton = findViewById(id);
            slotButton.setOnClickListener(this::onSlotButtonClick);
        }
    }

    private void onSlotButtonClick(View view) {
        if (selectedSlotButton != null) {
            selectedSlotButton.setBackgroundColor(DEFAULT_SLOT_COLOR);
        }

        selectedSlotButton = (Button) view;
        selectedSlot = selectedSlotButton.getText().toString();
        selectedSlotButton.setBackgroundColor(SELECTED_SLOT_COLOR);
    }

    private void bookAppointment() {
        if (selectedDate == null) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedSlot == null) {
            Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ac2.class);
        intent.putExtra("selectedDate", selectedDate);
        intent.putExtra("selectedSlot", selectedSlot);
        startActivity(intent);
    }

    public void booknow(View view) {
        Intent intent = new Intent(this, ac2.class);
        intent.putExtra("selectedDate", selectedDate);
        intent.putExtra("selectedSlot", selectedSlot);
        startActivity(intent);
    }

    public void makePayment(View view) {
        Intent intent=new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}
