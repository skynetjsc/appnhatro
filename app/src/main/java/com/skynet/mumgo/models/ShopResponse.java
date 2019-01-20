package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopResponse {
    @SerializedName("shop")
    List<Shop> listShop;
    @SerializedName("hot_shop")
    List<Shop> hot_shop;

    public List<Shop> getListShop() {
        return listShop;
    }

    public void setListShop(List<Shop> listShop) {
        this.listShop = listShop;
    }

    public List<Shop> getHot_shop() {
        return hot_shop;
    }

    public void setHot_shop(List<Shop> hot_shop) {
        this.hot_shop = hot_shop;
    }
}
