package com.skynet.thuenha.network.socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DuongKK on 6/20/2017.
 */

public class SocketResponse implements Parcelable {
    @SerializedName("idBooking")
    String idBooking;
    @SerializedName("u_id")
    String u_id;
    @SerializedName("h_id")
    String h_id;
    @SerializedName("address")
    String address;
    @SerializedName("distance")
    String distance;
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
    @SerializedName("active")
    String active;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("numberHelper")
    String numberHelper;


    // message
    @SerializedName("from")
    String from;
    @SerializedName("to")
    String to;
    @SerializedName("idUserBooking")
    String idUserBooking;
    @SerializedName("idDriverBooking")
    String idDriverBooking;
    @SerializedName("idSimpleBooking")
    String idSimpleBooking;
    @SerializedName("wantToJoin")
    int wantToJoin;
    @SerializedName("type_booking")
    int type_booking;

    public int getWantToJoin() {
        return wantToJoin;
    }

    public void setWantToJoin(int wantToJoin) {
        this.wantToJoin = wantToJoin;
    }

    public SocketResponse() {
    }

    public String getIdSimpleBooking() {
        return idSimpleBooking;
    }

    public void setIdSimpleBooking(String idSimpleBooking) {
        this.idSimpleBooking = idSimpleBooking;
    }

    public String getIdUserBooking() {
        return idUserBooking;
    }

    public void setIdUserBooking(String idUserBooking) {
        this.idUserBooking = idUserBooking;
    }

    public int getType_booking() {
        return type_booking;
    }

    public void setType_booking(int type_booking) {
        this.type_booking = type_booking;
    }

    public String getIdDriverBooking() {
        return idDriverBooking;
    }

    public void setIdDriverBooking(String idDriverBooking) {
        this.idDriverBooking = idDriverBooking;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNumberHelper() {
        return numberHelper;
    }

    public void setNumberHelper(String numberHelper) {
        this.numberHelper = numberHelper;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idBooking);
        dest.writeString(this.u_id);
        dest.writeString(this.h_id);
        dest.writeString(this.address);
        dest.writeString(this.distance);
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.type);
        dest.writeString(this.active);
        dest.writeString(this.avatar);
        dest.writeString(this.numberHelper);
        dest.writeString(this.from);
        dest.writeString(this.to);
        dest.writeString(this.idUserBooking);
        dest.writeString(this.idDriverBooking);
        dest.writeString(this.idSimpleBooking);
        dest.writeInt(this.wantToJoin);
        dest.writeInt(this.type_booking);
    }

    protected SocketResponse(Parcel in) {
        this.idBooking = in.readString();
        this.u_id = in.readString();
        this.h_id = in.readString();
        this.address = in.readString();
        this.distance = in.readString();
        this.name = in.readString();
        this.time = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.type = in.readString();
        this.active = in.readString();
        this.avatar = in.readString();
        this.numberHelper = in.readString();
        this.from = in.readString();
        this.to = in.readString();
        this.idUserBooking = in.readString();
        this.idDriverBooking = in.readString();
        this.idSimpleBooking = in.readString();
        this.wantToJoin = in.readInt();
        this.type_booking = in.readInt();
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
