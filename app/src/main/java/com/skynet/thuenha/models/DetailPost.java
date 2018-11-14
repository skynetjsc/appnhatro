package com.skynet.thuenha.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailPost {
    @SerializedName("post")
    Post post;
    @SerializedName("is_pay")
    int isPay;
    @SerializedName("is_favourite")
    int is_favourite;
    @SerializedName("price")
    double priceBuy;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("host")
    Profile host;

    @SerializedName("city")
    String city;
    @SerializedName("district")
    String district;
    @SerializedName("utility")
    List<Utility> listUtilies;
    @SerializedName("image")
    List<String> image;

    public Profile getHost() {
        return host;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
    }

    public void setHost(Profile host) {
        this.host = host;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Utility> getListUtilies() {
        return listUtilies;
    }

    public void setListUtilies(List<Utility> listUtilies) {
        this.listUtilies = listUtilies;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
