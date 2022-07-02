package com.creativeitinstitute.quickchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {


    TextInputEditText edt_username, edt_email, edt_password;

    AppCompatButton btn_register, btn_have_an_account;


    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edt_username = findViewById(R.id.edt_username);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_register = findViewById(R.id.btn_register);
        btn_have_an_account = findViewById(R.id.btn_have_an_account);

        btn_register.setOnClickListener(v -> {
            String email = edt_email.getText().toString().trim();
            String password = edt_password.getText().toString().trim();
            String username = edt_username.getText().toString().trim();

            registerUser(email, password, username);


        });
        btn_have_an_account.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();

        });


    }

    private void registerUser(final String email, final String password, final String username) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(final AuthResult authResult) {

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = firebaseUser.getUid();

                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("name", username);
                userMap.put("email", email);
                userMap.put("profileImage", "");
                userMap.put("coverImage", "");
                userMap.put("bio", "");

                FirebaseDatabase.getInstance().getReference("User").child(userId)
                        .setValue(userMap).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Log.i("TAG", "Error: " + task.getException().getLocalizedMessage());
                            }
                        });
            }
        }).addOnFailureListener(e -> {

        });


    }
}