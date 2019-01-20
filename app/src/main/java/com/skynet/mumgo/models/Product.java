package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Product implements Parcelable {

    @Expose
    @SerializedName("active")
    private String active;
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
    @SerializedName("star")
    private double star;
    @Expose
    @SerializedName("total_rating")
    private int total_rating;
    @Expose
    @SerializedName("min_buy")
    private int min_buy;
    @Expose
    @SerializedName("booking_id")
    private int booking_id;
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
    @SerializedName("note")
    private String note;
    @Expose
    @SerializedName("time_ship")
    private String time_ship;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("shop_name")
    private String shopName;
    @Expose
    @SerializedName(value = "name",alternate = "product_name")
    private String name;
    @Expose
    @SerializedName("unit_name")
    private String name_unit;
    @Expose
    @SerializedName("unit_id")
    private int unit_id;
    @Expose
    @SerializedName("category_id")
    private int category_id;
    @Expose
    @SerializedName("shop_id")
    private int shop_id; @Expose
    @SerializedName("product_id")
    private int product_id;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Expose
    @SerializedName("id")

    private int id;
   @Expose
    @SerializedName("shop")
    private Shop shop;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    @SerializedName("number")

    private int quatity;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getQuatity() {
        return quatity;
    }

    public int getMin_buy() {
        return min_buy;
    }

    public void setMin_buy(int min_buy) {
        this.min_buy = min_buy;
    }

    public String getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(String time_ship) {
        this.time_ship = time_ship;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
    }

    @Expose
    @SerializedName("is_favourite")

    private int is_favourite;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
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

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(int total_rating) {
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

    public String getName_unit() {
        return name_unit;
    }

    public void setName_unit(String name_unit) {
        this.name_unit = name_unit;
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

    public Product() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.active);
        dest.writeString(this.date);
        dest.writeInt(this.number_sale);
        dest.writeInt(this.number_rating);
        dest.writeDouble(this.star);
        dest.writeInt(this.total_rating);
        dest.writeInt(this.min_buy);
        dest.writeInt(this.booking_id);
        dest.writeString(this.origin);
        dest.writeInt(this.discount);
        dest.writeDouble(this.min_price);
        dest.writeDouble(this.price);
        dest.writeString(this.img);
        dest.writeString(this.note);
        dest.writeString(this.time_ship);
        dest.writeString(this.content);
        dest.writeString(this.shopName);
        dest.writeString(this.name);
        dest.writeString(this.name_unit);
        dest.writeInt(this.unit_id);
        dest.writeInt(this.category_id);
        dest.writeInt(this.shop_id);
        dest.writeInt(this.product_id);
        dest.writeInt(this.id);
        dest.writeParcelable(this.shop, flags);
        dest.writeInt(this.quatity);
        dest.writeInt(this.is_favourite);
    }

    protected Product(Parcel in) {
        this.active = in.readString();
        this.date = in.readString();
        this.number_sale = in.readInt();
        this.number_rating = in.readInt();
        this.star = in.readDouble();
        this.total_rating = in.readInt();
        this.min_buy = in.readInt();
        this.booking_id = in.readInt();
        this.origin = in.readString();
        this.discount = in.readInt();
        this.min_price = in.readDouble();
        this.price = in.readDouble();
        this.img = in.readString();
        this.note = in.readString();
        this.time_ship = in.readString();
        this.content = in.readString();
        this.shopName = in.readString();
        this.name = in.readString();
        this.name_unit = in.readString();
        this.unit_id = in.readInt();
        this.category_id = in.readInt();
        this.shop_id = in.readInt();
        this.product_id = in.readInt();
        this.id = in.readInt();
        this.shop = in.readParcelable(Shop.class.getClassLoader());
        this.quatity = in.readInt();
        this.is_favourite = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
