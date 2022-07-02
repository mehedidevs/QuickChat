package com.creativeitinstitute.quickchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.creativeitinstitute.quickchat.adapter.FragAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        FragAdapter fragAdapter = new FragAdapter(fragmentManager);

        viewPager = findViewById(R.id.fragmentViewpager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_round_feed_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_round_user_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_round_chat_alt_24);


    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.top_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logOutMenu:
                SignOut();
                break;
            case R.id.myProfileMenu:
                Toast.makeText(this, "My Profile Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                break;


        }


        return super.onOptionsItemSelected(item);
    }

    public void SignOut() {


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signOut();
        startActivity(new Intent(getApplicationContext(), StartActivity.class));


    }
}