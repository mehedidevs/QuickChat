package com.creativeitinstitute.quickchat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeitinstitute.quickchat.R;
import com.creativeitinstitute.quickchat.User;
import com.creativeitinstitute.quickchat.adapter.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }

    RecyclerView userRecyclerView;
    List<User> userList;
    DatabaseReference userReference;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        userList = new ArrayList<>();

        userReference = FirebaseDatabase.getInstance().getReference("User");

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue(User.class);

                    userList.add(user);


                }

                UserAdapter userAdapter = new UserAdapter(getActivity(), userList);

                userRecyclerView.setAdapter(userAdapter);


            }

            @Override
            public void onCancelled(@NonNull final DatabaseError error) {

            }
        });


        return view;
    }
}