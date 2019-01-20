package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food implements Parcelable {


    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("type_detail")
    private int type_detail;
    @Expose
    @SerializedName("type")
    private int type;
    @Expose
    @SerializedName("fat_type")
    private int fat_type;
    @Expose
    @SerializedName("protein_type")
    private int protein_type;
    @Expose
    @SerializedName("glycemic")
    private int glycemic;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("calo")
    private int calo;
    @Expose
    @SerializedName("cabs")
    private int cabs;
    @Expose
    @SerializedName("fat")
    private int fat;
    @Expose
    @SerializedName("protein")
    private int protein;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("is_favourite")
    private int is_fav;
    @Expose
    @SerializedName("number")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(int is_fav) {
        this.is_fav = is_fav;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getType_detail() {
        return type_detail;
    }

    public void setType_detail(int type_detail) {
        this.type_detail = type_detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFat_type() {
        return fat_type;
    }

    public void setFat_type(int fat_type) {
        this.fat_type = fat_type;
    }

    public int getProtein_type() {
        return protein_type;
    }

    public void setProtein_type(int protein_type) {
        this.protein_type = protein_type;
    }

    public int getGlycemic() {
        return glycemic;
    }

    public void setGlycemic(int glycemic) {
        this.glycemic = glycemic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public int getCabs() {
        return cabs;
    }

    public void setCabs(int cabs) {
        this.cabs = cabs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        dest.writeInt(this.type_detail);
        dest.writeInt(this.type);
        dest.writeInt(this.fat_type);
        dest.writeInt(this.protein_type);
        dest.writeInt(this.glycemic);
        dest.writeString(this.date);
        dest.writeString(this.content);
        dest.writeInt(this.calo);
        dest.writeInt(this.cabs);
        dest.writeInt(this.fat);
        dest.writeInt(this.protein);
        dest.writeString(this.name);
        dest.writeString(this.img);
        dest.writeInt(this.id);
        dest.writeInt(this.is_fav);
    }

    public Food() {
    }

    protected Food(Parcel in) {
        this.active = in.readString();
        this.type_detail = in.readInt();
        this.type = in.readInt();
        this.fat_type = in.readInt();
        this.protein_type = in.readInt();
        this.glycemic = in.readInt();
        this.date = in.readString();
        this.content = in.readString();
        this.calo = in.readInt();
        this.cabs = in.readInt();
        this.fat = in.readInt();
        this.protein = in.readInt();
        this.name = in.readString();
        this.img = in.readString();
        this.id = in.readInt();
        this.is_fav = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
