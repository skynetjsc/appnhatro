package com.skynet.thuenha.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Utility implements Parcelable {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("position")
    private String position;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        dest.writeString(this.active);
        dest.writeString(this.date);
        dest.writeString(this.content);
        dest.writeString(this.img);
        dest.writeString(this.position);
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }

    public Utility() {
    }

    protected Utility(Parcel in) {
        this.active = in.readString();
        this.date = in.readString();
        this.content = in.readString();
        this.img = in.readString();
        this.position = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Utility> CREATOR = new Parcelable.Creator<Utility>() {
        @Override
        public Utility createFromParcel(Parcel source) {
            return new Utility(source);
        }

        @Override
        public Utility[] newArray(int size) {
            return new Utility[size];
        }
    };
}
