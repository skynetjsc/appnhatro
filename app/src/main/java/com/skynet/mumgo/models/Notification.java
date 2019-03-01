package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Notification implements Parcelable {
    @SerializedName("title")
    String title;
    @SerializedName("id")
    String id;
    @SerializedName("content")
    String name;
    @SerializedName("date")
    String time;
    @SerializedName("is_read")
    int isRead;
    @SerializedName("type")
    int type;

    public int isRead() {
        return isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRead(int read) {
        isRead = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeInt(this.isRead);
        dest.writeInt(this.type);
    }

    public Notification() {
    }

    protected Notification(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.time = in.readString();
        this.isRead = in.readInt();
        this.type = in.readInt();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel source) {
            return new Notification(source);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };
}
