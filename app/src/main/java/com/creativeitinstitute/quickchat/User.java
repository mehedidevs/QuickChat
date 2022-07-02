package com.creativeitinstitute.quickchat;

public class User {

    private String bio, coverImage, email, name, profileImage;

    public User() {
    }

    public User(final String bio, final String coverImage, final String email, final String name, final String profileImage) {
        this.bio = bio;
        this.coverImage = coverImage;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(final String coverImage) {
        this.coverImage = coverImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(final String profileImage) {
        this.profileImage = profileImage;
    }
}
