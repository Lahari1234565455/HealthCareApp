package com.example.splashscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText upiId, amount;
    private Button payButton;
    private ImageView topImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        upiId = findViewById(R.id.upi_id);
        amount = findViewById(R.id.amount);
        payButton = findViewById(R.id.pay_button);
        topImage = findViewById(R.id.top_image);

        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        payButton.setOnTouchListener((v, event) -> {
            v.startAnimation(scaleUp);
            v.startAnimation(scaleDown);
            return false;
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUPI();
            }
        });
    }

    private void payUsingUPI() {
        String id = upiId.getText().toString();
        String amt = amount.getText().toString();

        if (id.isEmpty() || amt.isEmpty()) {
            Toast.makeText(this, "Please enter UPI ID and amount", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri uri = Uri.parse("upi://pay?pa=" + id +
                "&pn=YourName" + // Payee Name
                "&mc=0000" + // Merchant Code
                "&tid=0123456789ABCDEF" + // Transaction ID
                "&pid=example12345" + // Payment ID
                "&tn=TestTransaction" + // Transaction Note
                "&am=" + amt + // Amount
                "&cu=INR" + // Currency Code
                "&url="); // URL

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        Intent chooser = Intent.createChooser(intent, "Pay with");
        startActivityForResult(chooser, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String status = uri.getQueryParameter("Status");
                    if ("success".equalsIgnoreCase(status)) {
                        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
