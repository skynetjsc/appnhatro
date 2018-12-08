package com.skynet.thuenha.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post implements Parcelable {

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
    @SerializedName("color")
    private int color;
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
    @SerializedName("number_seen")
    private int number_seen;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("host_id")
    private int host_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("number_bed")
    private int number_bed;
    @Expose
    @SerializedName("number_wc")
    private int number_wc;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getNumber_seen() {
        return number_seen;
    }

    public void setNumber_seen(int number_seen) {
        this.number_seen = number_seen;
    }

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getNumber_bed() {
        return number_bed;
    }

    public void setNumber_bed(int number_bed) {
        this.number_bed = number_bed;
    }

    public int getNumber_wc() {
        return number_wc;
    }

    public void setNumber_wc(int number_wc) {
        this.number_wc = number_wc;
    }

    private Utility utility1;
    private Utility utility2;

    public Utility getUtility1() {
        return utility1;
    }

    public void setUtility1(Utility utility1) {
        this.utility1 = utility1;
    }

    public Utility getUtility2() {
        return utility2;
    }

    public void setUtility2(Utility utility2) {
        this.utility2 = utility2;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.active);
        dest.writeString(this.type);
        dest.writeString(this.date);
        dest.writeString(this.avatar);
        dest.writeString(this.note);
        dest.writeString(this.id_utility);
        dest.writeString(this.content);
        dest.writeDouble(this.price);
        dest.writeDouble(this.area);
        dest.writeString(this.district_id);
        dest.writeString(this.city_id);
        dest.writeString(this.address);
        dest.writeString(this.title);
        dest.writeInt(this.id_service);
        dest.writeInt(this.number_seen);
        dest.writeInt(this.user_id);
        dest.writeInt(this.host_id);
        dest.writeInt(this.id);
        dest.writeInt(this.number_bed);
        dest.writeInt(this.number_wc);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.utility1, flags);
        dest.writeParcelable(this.utility2, flags);
    }

    public Post() {
    }

    protected Post(Parcel in) {
        this.active = in.readString();
        this.type = in.readString();
        this.date = in.readString();
        this.avatar = in.readString();
        this.note = in.readString();
        this.id_utility = in.readString();
        this.content = in.readString();
        this.price = in.readDouble();
        this.area = in.readDouble();
        this.district_id = in.readString();
        this.city_id = in.readString();
        this.address = in.readString();
        this.title = in.readString();
        this.id_service = in.readInt();
        this.number_seen = in.readInt();
        this.user_id = in.readInt();
        this.host_id = in.readInt();
        this.id = in.readInt();
        this.number_bed = in.readInt();
        this.number_wc = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.utility1 = in.readParcelable(Utility.class.getClassLoader());
        this.utility2 = in.readParcelable(Utility.class.getClassLoader());
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
