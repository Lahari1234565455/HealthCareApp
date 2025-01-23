package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class Otp extends AppCompatActivity {
    Button verifyOTP;
    EditText editText;
    TextView resendOtp;
    ProgressBar progressBar;
    String verificationID, phoneNumber;
    FirebaseAuth mAuth;
    PhoneAuthProvider.ForceResendingToken forceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        verifyOTP = findViewById(R.id.verify);
        editText = findViewById(R.id.edittext);
        resendOtp = findViewById(R.id.res);
        progressBar = findViewById(R.id.progressbar);

        phoneNumber = getIntent().getStringExtra("phoneNumber");
        verificationID = getIntent().getStringExtra("verificationId");
        forceResendingToken = getIntent().getParcelableExtra("forceResendingToken");

        verifyOTP.setOnClickListener(v -> {
            String otp = editText.getText().toString().trim();
            if (otp.length() != 6) {
                Toast.makeText(Otp.this, "Enter a valid 6-digit OTP", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                verifyOTP(); // Call method to verify OTP
            }
        });

        resendOtp.setOnClickListener(v -> resendOTP()); // Call method to resend OTP
    }

    private void verifyOTP() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, editText.getText().toString().trim());
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                Toast.makeText(Otp.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Otp.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(Otp.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void resendOTP() {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        verificationID = newVerificationId;
                        forceResendingToken = token;
                        Toast.makeText(Otp.this, "OTP Resent", Toast.LENGTH_SHORT).show();
                    }
                })
                .setForceResendingToken(forceResendingToken)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
