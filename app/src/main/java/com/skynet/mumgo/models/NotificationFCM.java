package com.skynet.mumgo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class NotificationFCM {

    @Expose
    @SerializedName("type_noti")
    private int type;
    @Expose
    @SerializedName("object")
    private Message object;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("title")
    private String title;

    @SerializedName("room_id")
    String roomID;

    @SerializedName("user_id")
    String idUserRequestFriend;
    @SerializedName("user_name")
    String nameUserRequestFriend;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIdUserRequestFriend() {
        return idUserRequestFriend;
    }

    public void setIdUserRequestFriend(String idUserRequestFriend) {
        this.idUserRequestFriend = idUserRequestFriend;
    }

    public String getNameUserRequestFriend() {
        return nameUserRequestFriend;
    }

    public void setNameUserRequestFriend(String nameUserRequestFriend) {
        this.nameUserRequestFriend = nameUserRequestFriend;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public Message getObject() {
        return object;
    }

    public void setObject(Message object) {
        this.object = object;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
