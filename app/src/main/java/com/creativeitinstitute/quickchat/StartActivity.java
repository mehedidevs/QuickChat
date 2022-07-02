package com.creativeitinstitute.quickchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {


    AppCompatButton btnRegisterBtn, btn_log_in;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        btnRegisterBtn = findViewById(R.id.btnRegisterBtn);
        btn_log_in = findViewById(R.id.btn_log_in);

        btnRegisterBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

        });


        btn_log_in.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));

        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseUser != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }


    }
}