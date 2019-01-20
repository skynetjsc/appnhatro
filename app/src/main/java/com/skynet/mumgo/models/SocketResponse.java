package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DuongKK on 6/20/2017.
 */

public class SocketResponse implements Parcelable {

    @SerializedName("name")
    String name;
    @SerializedName("time")
    String time;
    @SerializedName("title")
    String title;
    @SerializedName("content")
    String content;
    @SerializedName("type")
    String type;
    @SerializedName("sendFrom")
    String sendFrom;
    @SerializedName("idUser")
    String idUser;
    @SerializedName("idHost")
    String idHost;
    @SerializedName("idPost")
    String idPost;


    public SocketResponse() {
    }


    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdHost() {
        return idHost;
    }

    public void setIdHost(String idHost) {
        this.idHost = idHost;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.type);
        dest.writeString(this.sendFrom);
        dest.writeString(this.idUser);
        dest.writeString(this.idHost);
        dest.writeString(this.idPost);
    }

    protected SocketResponse(Parcel in) {
        this.name = in.readString();
        this.time = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.type = in.readString();
        this.sendFrom = in.readString();
        this.idUser = in.readString();
        this.idHost = in.readString();
        this.idPost = in.readString();
    }

    public static final Creator<SocketResponse> CREATOR = new Creator<SocketResponse>() {
        @Override
        public SocketResponse createFromParcel(Parcel source) {
            return new SocketResponse(source);
        }

        @Override
        public SocketResponse[] newArray(int size) {
            return new SocketResponse[size];
        }
    };
}
