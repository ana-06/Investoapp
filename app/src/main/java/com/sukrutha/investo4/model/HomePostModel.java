package com.sukrutha.investo4.model;

public class HomePostModel {

    int profile, postImage;
    String name, like, comment;

    public HomePostModel(int profile, int postImage, String name, String like, String comment) {
        this.profile = profile;
        this.postImage = postImage;
        this.name = name;
        this.like = like;
        this.comment = comment;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
