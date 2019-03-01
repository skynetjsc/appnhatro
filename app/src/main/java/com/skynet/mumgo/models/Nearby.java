package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Nearby {
    @SerializedName("list_product")
    List<Product> listProduct;
    @SerializedName("list_shop")
    List<Shop> listShop;
    @SerializedName("list_scan")
    List<Shop> listFriend;
    @SerializedName("index")
    int index;

    public List<Shop> getListFriend() {
        return listFriend;
    }

    public void setListFriend(List<Shop> listFriend) {
        this.listFriend = listFriend;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<Shop> getListShop() {
        return listShop;
    }

    public void setListShop(List<Shop> listShop) {
        this.listShop = listShop;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
