package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class History {

    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("payment")
    private int payment;
    @Expose
    @SerializedName("note")
    private String note;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("id_promotion")
    private int id_promotion;
    @Expose
    @SerializedName("date_booking")
    private String date_booking;
    @Expose
    @SerializedName("time_ship")
    private String time_ship;
    @Expose
    @SerializedName("date_create")
    private String date_create;
    @Expose
    @SerializedName("shop_id")
    private int shop_id;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("product_name")
    private String name;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("list_product")
    private List<Product> list_product;
    private String activeString;

    public String getActiveString() {
        return activeString;
    }

    public void setActiveString(String activeString) {
        this.activeString = activeString;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Product> getList_product() {
        return list_product;
    }

    public void setList_product(List<Product> list_product) {
        this.list_product = list_product;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPayment() {
        return payment;
    }

    public String getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(String time_ship) {
        this.time_ship = time_ship;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public String getDate_booking() {
        return date_booking;
    }

    public void setDate_booking(String date_booking) {
        this.date_booking = date_booking;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
