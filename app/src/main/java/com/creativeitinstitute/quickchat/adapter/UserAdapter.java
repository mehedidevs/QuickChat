package com.creativeitinstitute.quickchat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.creativeitinstitute.quickchat.R;
import com.creativeitinstitute.quickchat.User;
import com.creativeitinstitute.quickchat.viewholder.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    Context context;
    List<User> userList;

    public UserAdapter(final Context context, final List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {

        User user = userList.get(position);

        holder.userName.setText(user.getName());
        holder.userEmail.setText(user.getEmail());
        Glide.with(context).load(user.getProfileImage()).into(holder.profile_image);

        holder.messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
    /*            Intent intent = new Intent(context, ChatActivity.class);

                context.startActivity(intent);*/

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
