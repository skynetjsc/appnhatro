package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePhoto {
    @SerializedName("index")
    int index;
    @SerializedName("list_image")
    List<Image> list;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Image> getList() {
        return list;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }
}
