package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Target {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("time_update")
    private String time_update;
    @Expose
    @SerializedName("time_update_target_week")
    private String time_update_target_week;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("mucdovandong")
    private String mucdovandong;
    @Expose
    @SerializedName("tylemo")
    private int tylemo;
    @Expose
    @SerializedName("khoiluong")
    private int khoiluong;
    @Expose
    @SerializedName("khoiluongmuctieu")
    private int khoiluongmuctieu;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("protein")
    private double protein;
    @Expose
    @SerializedName("fat")
    private double fat;
    @Expose
    @SerializedName("carb")
    private double carb;
    @Expose
    @SerializedName("other")
    private double other;
    @Expose
    @SerializedName("total")
    private double total;

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTime_update() {
        return time_update;
    }

    public void setTime_update(String time_update) {
        this.time_update = time_update;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMucdovandong() {
        return mucdovandong;
    }

    public String getTime_update_target_week() {
        return time_update_target_week;
    }

    public void setTime_update_target_week(String time_update_target_week) {
        this.time_update_target_week = time_update_target_week;
    }

    public int getKhoiluongmuctieu() {
        return khoiluongmuctieu;
    }

    public void setKhoiluongmuctieu(int khoiluongmuctieu) {
        this.khoiluongmuctieu = khoiluongmuctieu;
    }

    public void setMucdovandong(String mucdovandong) {
        this.mucdovandong = mucdovandong;
    }

    public int getTylemo() {
        return tylemo;
    }

    public void setTylemo(int tylemo) {
        this.tylemo = tylemo;
    }

    public int getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(int khoiluong) {
        this.khoiluong = khoiluong;
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
