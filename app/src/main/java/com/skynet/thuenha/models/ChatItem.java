package com.skynet.thuenha.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatItem {
    @SerializedName("user")
    Profile use;
    @SerializedName("host")
    Profile shop;
    @SerializedName("last_message")
    String lastMessage;
    @SerializedName("time")
    String time;
    @SerializedName("user_read")
    int userRead;
    @SerializedName("shop_read")
    int shopRead;
    @SerializedName("id_post")
    int id_post;
    String name,avt;
    @SerializedName("content")
    List<Message> content;
    @SerializedName("post")
    Post post;

    public Profile getUse() {
        return use;
    }

    public List<Message> getContent() {
        return content;
    }

    public void setContent(List<Message> content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUserRead() {
        return userRead;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRead(int userRead) {
        this.userRead = userRead;
    }

    public int getShopRead() {
        return shopRead;
    }

    public void setShopRead(int shopRead) {
        this.shopRead = shopRead;
    }

    public void setUse(Profile use) {
        this.use = use;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Profile getShop() {
        return shop;
    }

    public void setShop(Profile shop) {
        this.shop = shop;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
