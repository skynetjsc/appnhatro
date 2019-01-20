package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Term {


    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("id")
    private String id;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
