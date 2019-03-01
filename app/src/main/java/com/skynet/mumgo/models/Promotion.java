package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Promotion {
    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("user_read")
    private int user_read;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("date_end")
    private String date_end;
    @Expose
    @SerializedName("date_start")
    private String date_start;
    @Expose
    @SerializedName("value")
    private int value;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id")
    private int id;



    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getUser_read() {
        return user_read;
    }

    public void setUser_read(int user_read) {
        this.user_read = user_read;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
