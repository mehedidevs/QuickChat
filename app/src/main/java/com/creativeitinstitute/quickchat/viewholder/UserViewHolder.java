package com.creativeitinstitute.quickchat.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeitinstitute.quickchat.R;


import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder {


    public CircleImageView profile_image;

    public TextView userName, userEmail;

    public Button messageBtn;

    public UserViewHolder(@NonNull final View itemView) {
        super(itemView);

        profile_image = itemView.findViewById(R.id.profile_image);
        userName = itemView.findViewById(R.id.userName);
        userEmail = itemView.findViewById(R.id.userEmail);
        messageBtn = itemView.findViewById(R.id.messageBtn);


    }
}
