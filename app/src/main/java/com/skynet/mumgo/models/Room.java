package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room implements Parcelable {
    @SerializedName("user_1")
    Profile user1;
    @SerializedName("user_2")
    Profile user2;
    @SerializedName("list_content")
    List<Message> listMessage;

    @SerializedName("index")
    int index;

    @SerializedName("title")
    String title;

    @SerializedName("roomInfo")
    int roomInfo;
    @SerializedName("is_mute")
    int is_mute;
    @SerializedName("list_user")
    List<Profile> listUser;

    public int getIs_mute() {
        return is_mute;
    }

    public void setIs_mute(int is_mute) {
        this.is_mute = is_mute;
    }

    public String getTitle() {
        return title;
    }

    public List<Profile> getListUser() {
        return listUser;
    }

    public void setListUser(List<Profile> listUser) {
        this.listUser = listUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(int roomInfo) {
        this.roomInfo = roomInfo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Profile getUser1() {
        return user1;
    }

    public void setUser1(Profile user1) {
        this.user1 = user1;
    }

    public Profile getUser2() {
        return user2;
    }

    public void setUser2(Profile user2) {
        this.user2 = user2;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public Room() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user1, flags);
        dest.writeParcelable(this.user2, flags);
        dest.writeTypedList(this.listMessage);
        dest.writeInt(this.index);
        dest.writeString(this.title);
        dest.writeInt(this.roomInfo);
        dest.writeInt(this.is_mute);
        dest.writeTypedList(this.listUser);
    }

    protected Room(Parcel in) {
        this.user1 = in.readParcelable(Profile.class.getClassLoader());
        this.user2 = in.readParcelable(Profile.class.getClassLoader());
        this.listMessage = in.createTypedArrayList(Message.CREATOR);
        this.index = in.readInt();
        this.title = in.readString();
        this.roomInfo = in.readInt();
        this.is_mute = in.readInt();
        this.listUser = in.createTypedArrayList(Profile.CREATOR);
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel source) {
            return new Room(source);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };
}
