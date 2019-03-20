package com.skynet.thuenha.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataSearch {
    @SerializedName("index")
    int index;
    @SerializedName("list")
    List<Post> list;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }
}
