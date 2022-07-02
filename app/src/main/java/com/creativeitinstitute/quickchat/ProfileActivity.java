package com.creativeitinstitute.quickchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    ImageView coverImage, profile_image;

    TextView userName, userEmail;


    FirebaseUser firebaseUser;


    StorageReference storageReferenceCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        storageReferenceCover = FirebaseStorage.getInstance().getReference("uploads").child("cover_image");

        coverImage = findViewById(R.id.coverImage);
        profile_image = findViewById(R.id.profile_image);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot snapshot) {

                        User user = snapshot.getValue(User.class);
                        userName.setText(user.getName());
                        userEmail.setText(user.getEmail());


                        Glide.with(ProfileActivity.this).load(user.getProfileImage()).into(profile_image);
                        Glide.with(ProfileActivity.this).load(user.getCoverImage()).into(coverImage);


                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError error) {

                    }
                });

        coverImage.setOnClickListener(view -> {
            ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(512)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start(101);
        });


    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri coverUri = data.getData();
            coverImage.setImageURI(coverUri);
            uploadImage(coverUri);

        }


    }

    private void uploadImage(final Uri coverUri) {
        StorageReference cRef = storageReferenceCover.child("c_img_" + firebaseUser.getUid());

        cRef.putFile(coverUri).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                cRef.getDownloadUrl().addOnSuccessListener(uri -> {

                    String imageUrl = String.valueOf(uri);

                    HashMap<String, Object> coverMap = new HashMap<>();
                    coverMap.put("coverImage", imageUrl);

                    FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid())
                            .updateChildren(coverMap);


                });


            }


        });


    }
}