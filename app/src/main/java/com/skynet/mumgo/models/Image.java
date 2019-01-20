package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.skynet.mumgo.ui.views.Assymetric.AsymmetricItem;

import java.io.File;

public class Image implements Parcelable, AsymmetricItem {
    @SerializedName(value = "img", alternate = "image")
    String img;
    int id;
    @SerializedName("post_id")
    int postID;
    @SerializedName("date")
    String date;
    @SerializedName("video")
    String video;
    @SerializedName("type")
    int type;
    private int columnSpan;
    private int rowSpan;
    private int position;
    public String getVideo() {
        return video;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Image(int columnSpan, int rowSpan, int position) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public int getPosition() {
        return position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("file")
    File file;

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

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
        this(1, 1, 0);

    }

    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
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
        dest.writeString(this.date);
        dest.writeString(this.video);
        dest.writeInt(this.type);
        dest.writeInt(this.columnSpan);
        dest.writeInt(this.rowSpan);
        dest.writeInt(this.position);
        dest.writeSerializable(this.file);
    }

    protected Image(Parcel in) {
        this.img = in.readString();
        this.id = in.readInt();
        this.postID = in.readInt();
        this.date = in.readString();
        this.video = in.readString();
        this.type = in.readInt();
        this.columnSpan = in.readInt();
        this.rowSpan = in.readInt();
        this.position = in.readInt();
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
