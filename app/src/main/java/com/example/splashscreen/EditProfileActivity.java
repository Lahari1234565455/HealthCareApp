package com.example.splashscreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editProfileName, editProfileEmail;
    private ImageView editProfileImage;
    private Button changeProfileImageButton, saveProfileButton;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private FirebaseUser user;
    private Uri imageUri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfileName = findViewById(R.id.edit_profile_name);
        editProfileEmail = findViewById(R.id.edit_profile_email);
        editProfileImage = findViewById(R.id.edit_profile_image);
        changeProfileImageButton = findViewById(R.id.change_profile_image_button);
        saveProfileButton = findViewById(R.id.save_profile_button);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();

        // Set current user information
        if (user != null) {
            editProfileName.setText(user.getDisplayName());
            editProfileEmail.setText(user.getEmail());
            // Load the profile image if available
            // You can use libraries like Glide or Picasso for this
        }

        changeProfileImageButton.setOnClickListener(v -> openFileChooser());

        saveProfileButton.setOnClickListener(v -> saveProfileChanges());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                editProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProfileChanges() {
        String name = editProfileName.getText().toString();
        String email = editProfileEmail.getText().toString();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        user.updateEmail(email).addOnSuccessListener(aVoid -> {
            DocumentReference docRef = fStore.collection("users").document(user.getUid());
            docRef.update("name", name, "email", email)
                    .addOnSuccessListener(aVoid1 -> {
                        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        if (imageUri != null) {
                            uploadImage();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }).addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void uploadImage() {
        StorageReference fileRef = storageReference.child("users/" + user.getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
            DocumentReference docRef = fStore.collection("users").document(user.getUid());
            docRef.update("profileImage", uri.toString())
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Profile Image Updated", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        })).addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
