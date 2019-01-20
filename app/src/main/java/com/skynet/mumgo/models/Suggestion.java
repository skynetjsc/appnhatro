package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Suggestion {

    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("number_sale")
    private int number_sale;
    @Expose
    @SerializedName("number_rating")
    private int number_rating;
    @Expose
    @SerializedName("total_rating")
    private double total_rating;
    @Expose
    @SerializedName("origin")
    private String origin;
    @Expose
    @SerializedName("discount")
    private int discount;
    @Expose
    @SerializedName("min_price")
    private double min_price;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("unit_id")
    private int unit_id;
    @Expose
    @SerializedName("category_id")
    private int category_id;
    @Expose
    @SerializedName("shop_id")
    private int shop_id;
    @Expose
    @SerializedName("id")
    private int id;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber_sale() {
        return number_sale;
    }

    public void setNumber_sale(int number_sale) {
        this.number_sale = number_sale;
    }

    public int getNumber_rating() {
        return number_rating;
    }

    public void setNumber_rating(int number_rating) {
        this.number_rating = number_rating;
    }

    public double getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(double total_rating) {
        this.total_rating = total_rating;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getMin_price() {
        return min_price;
    }

    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
