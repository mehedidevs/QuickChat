package com.creativeitinstitute.quickchat.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.creativeitinstitute.quickchat.fragments.ChatFragment;
import com.creativeitinstitute.quickchat.fragments.StatusFragment;
import com.creativeitinstitute.quickchat.fragments.UserFragment;

public class FragAdapter extends FragmentPagerAdapter {
    String[] names = {"Status", "User", "Chat"};


    public FragAdapter(@NonNull final FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(final int position) {

        switch (position) {
            case 0:
                return new StatusFragment();
            case 1:
                return new UserFragment();
            case 2:
                return new ChatFragment();


        }


        return null;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return names[position];
    }
}
