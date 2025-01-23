package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullName, email, password;
    private Switch doctorSwitch;
    private Button signupButton;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the UI elements
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        doctorSwitch = findViewById(R.id.doctor_switch);
        signupButton = findViewById(R.id.signup_button);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        // Check if the user is already logged in
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        }

        // Set the onClickListener for the signup button
        signupButton.setOnClickListener(v -> {
            String name = fullName.getText().toString();
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            boolean isDoctor = doctorSwitch.isChecked();

            if (name.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                // Show progress bar
                progressBar.setVisibility(View.VISIBLE);

                // Handle the signup logic
                fAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Successfully created user
                            FirebaseAuth.getInstance().getCurrentUser().updateProfile(
                                    new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name) // Set the display name
                                            .build()
                            ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                        // Navigate to PhoneAuthenication activity
                                        Intent intent = new Intent(SignUpActivity.this, PhoneAuthenication.class);
                                        startActivity(intent);
                                        finish(); // Close this activity
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Profile update failed. Try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Signup failed
                            if (task.getException() instanceof FirebaseAuthException) {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                handleSignUpError(errorCode);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    public void navigateToLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, PhoneAuthenication.class);
        startActivity(intent);
    }

    private void handleSignUpError(String errorCode) {
        switch (errorCode) {
            case "ERROR_INVALID_EMAIL":
                email.setError("The email address is badly formatted.");
                email.requestFocus();
                break;
            case "ERROR_WEAK_PASSWORD":
                password.setError("The password is too weak.");
                password.requestFocus();
                break;
            case "ERROR_EMAIL_ALREADY_IN_USE":
                email.setError("The email address is already in use.");
                email.requestFocus();
                break;
            default:
                Toast.makeText(SignUpActivity.this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
