package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Timeline {
    @SerializedName(value = "list_post" , alternate = "news")
    List<Post> listPost;
    @SerializedName("list_video")
    List<Image> list_video;
    @SerializedName("list_image")
    List<Image> list_image;
    @SerializedName("index")
    int index;
    @SerializedName("user")
    Profile user;

    public List<Post> getListPost() {
        return listPost;
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }

    public List<Image> getList_video() {
        return list_video;
    }

    public void setList_video(List<Image> list_video) {
        this.list_video = list_video;
    }

    public List<Image> getList_image() {
        return list_image;
    }

    public void setList_image(List<Image> list_image) {
        this.list_image = list_image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }
}
