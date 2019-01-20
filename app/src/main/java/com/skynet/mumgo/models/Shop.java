package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Shop implements Parcelable {

    @Expose
    @SerializedName("number_product")
    private int number_product;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("is_hot")
    private int is_hot;
    @Expose
    @SerializedName("number_rating")
    private int number_rating;
    @Expose
    @SerializedName("total_rating")
    private double total_rating;
    @Expose
    @SerializedName("lng")
    private double lng;
    @Expose
    @SerializedName("lat")
    private double lat;
    @Expose
    @SerializedName("account")
    private String account;
    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("number_ship")
    private String number_ship;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("tax")
    private String tax;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("person")
    private String person;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("intro")
    private String intro;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;  @Expose
    @SerializedName("is_favourite")
    private int is_favourite;
    @SerializedName("star")
    private double star;

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getNumber_product() {
        return number_product;
    }

    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
    }

    public void setNumber_product(int number_product) {
        this.number_product = number_product;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getNumber_ship() {
        return number_ship;
    }

    public void setNumber_ship(String number_ship) {
        this.number_ship = number_ship;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number_product);
        dest.writeString(this.avatar);
        dest.writeInt(this.is_hot);
        dest.writeInt(this.number_rating);
        dest.writeDouble(this.total_rating);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
        dest.writeString(this.account);
        dest.writeString(this.active);
        dest.writeString(this.number_ship);
        dest.writeString(this.date);
        dest.writeString(this.tax);
        dest.writeString(this.address);
        dest.writeString(this.person);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.intro);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeInt(this.is_favourite);
        dest.writeDouble(this.star);
    }

    public Shop() {
    }

    protected Shop(Parcel in) {
        this.number_product = in.readInt();
        this.avatar = in.readString();
        this.is_hot = in.readInt();
        this.number_rating = in.readInt();
        this.total_rating = in.readDouble();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
        this.account = in.readString();
        this.active = in.readString();
        this.number_ship = in.readString();
        this.date = in.readString();
        this.tax = in.readString();
        this.address = in.readString();
        this.person = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.intro = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.is_favourite = in.readInt();
        this.star = in.readDouble();
    }

    public static final Parcelable.Creator<Shop> CREATOR = new Parcelable.Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
