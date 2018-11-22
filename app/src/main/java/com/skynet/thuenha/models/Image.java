package com.skynet.thuenha.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Image implements Parcelable {
    @SerializedName("img")
    String img;
    @SerializedName("id")
    int id;
    @SerializedName("post_id")
    int postID;

    @SerializedName("file")
    File file;

    public String getImg() {
        return img;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public Image() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeInt(this.id);
        dest.writeInt(this.postID);
        dest.writeSerializable(this.file);
    }

    protected Image(Parcel in) {
        this.img = in.readString();
        this.id = in.readInt();
        this.postID = in.readInt();
        this.file = (File) in.readSerializable();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
