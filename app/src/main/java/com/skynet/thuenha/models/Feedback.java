package com.skynet.thuenha.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feedback {
    private boolean isChecked;
    @SerializedName("comment")
    List<Comment> listComment;
    @Expose
    @SerializedName("is_like")
    private int is_like;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("like_feedback")
    private int like_feedback;
    @Expose
    @SerializedName("date_response")
    private String date_response;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("response")
    private String response;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("type")
    private int type;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("id")
    private int id;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getIs_like() {
        return is_like;
    }

    public List<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Comment> listComment) {
        this.listComment = listComment;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getLike_feedback() {
        return like_feedback;
    }

    public void setLike_feedback(int like_feedback) {
        this.like_feedback = like_feedback;
    }

    public String getDate_response() {
        return date_response;
    }

    public void setDate_response(String date_response) {
        this.date_response = date_response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
