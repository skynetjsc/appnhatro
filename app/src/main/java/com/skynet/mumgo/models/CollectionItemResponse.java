package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionItemResponse  {
    @SerializedName("front")
    List<Image> listFront;
    @SerializedName("back")
    List<Image> listback;
    @SerializedName("side")
    List<Image> listside;

    public List<Image> getListFront() {
        return listFront;
    }

    public void setListFront(List<Image> listFront) {
        this.listFront = listFront;
    }

    public List<Image> getListback() {
        return listback;
    }

    public void setListback(List<Image> listback) {
        this.listback = listback;
    }

    public List<Image> getListside() {
        return listside;
    }

    public void setListside(List<Image> listside) {
        this.listside = listside;
    }
}
