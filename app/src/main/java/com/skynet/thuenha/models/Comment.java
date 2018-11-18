package com.skynet.thuenha.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("comment")
    private String comment;
    @Expose
    @SerializedName("name")
    private String name;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
