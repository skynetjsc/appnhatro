package com.skynet.thuenha.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Huy on 4/21/2018.
 */

public class Message implements Parcelable {

    @Expose
    @SerializedName("type")
    private int type;

    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("sh_id")
    private String shId;
    @Expose
    @SerializedName("u_id")
    private String uId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShId() {
        return shId;
    }

    public void setShId(String shId) {
        this.shId = shId;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.content);
        dest.writeString(this.time);
        dest.writeString(this.shId);
        dest.writeString(this.uId);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.type = in.readInt();
        this.content = in.readString();
        this.time = in.readString();
        this.shId = in.readString();
        this.uId = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
