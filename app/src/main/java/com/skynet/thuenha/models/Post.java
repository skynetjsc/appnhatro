package com.skynet.thuenha.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("note")
    private String note;
    @Expose
    @SerializedName("id_utility")
    private String id_utility;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("area")
    private double area;
    @Expose
    @SerializedName("district_id")
    private String district_id;
    @Expose
    @SerializedName("city_id")
    private String city_id;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id_service")
    private int id_service;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("host_id")
    private int host_id;
    @Expose
    @SerializedName("id")
    private int id;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId_utility() {
        return id_utility;
    }

    public void setId_utility(String id_utility) {
        this.id_utility = id_utility;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
