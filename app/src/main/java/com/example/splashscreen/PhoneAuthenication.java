package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuthenication extends AppCompatActivity {

    EditText phonenumberEditText;
    Button sendOTP;
    String phoneNumber;
    String verifyId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_authenication);

        mAuth = FirebaseAuth.getInstance();
        phonenumberEditText = findViewById(R.id.ed1);
        sendOTP = findViewById(R.id.send);

        sendOTP.setOnClickListener(v -> {
            phoneNumber = phonenumberEditText.getText().toString().trim();
            if (phoneNumber.length() != 10) {
                Toast.makeText(PhoneAuthenication.this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
            } else {
                sendOTP(); // Call the method to send OTP
            }
        });

        // Initialize the callbacks for phone number verification
        onVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // This method is called when the code is automatically verified
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // Handle the error
                Toast.makeText(PhoneAuthenication.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                // This method is called when the OTP is sent
                super.onCodeSent(verificationId, forceResendingToken);
                verifyId = verificationId; // Save verification ID
                Intent intent = new Intent(PhoneAuthenication.this, Otp.class);
                intent.putExtra("verificationId", verificationId); // Pass verification ID
                intent.putExtra("phoneNumber", phoneNumber); // Pass phone number
                startActivity(intent); // Navigate to OTP activity
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(PhoneAuthenication.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PhoneAuthenication.this, Otp.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent); // Navigate to OTP activity
                    }
                }
        ).addOnFailureListener(e -> {
            Toast.makeText(PhoneAuthenication.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void sendOTP() {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91" + phoneNumber) // Set the phone number
                .setTimeout(60L, TimeUnit.SECONDS) // Set timeout duration
                .setActivity(this) // Activity context
                .setCallbacks(onVerificationStateChangedCallbacks) // Set callbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options); // Start phone number verification
    }
}
