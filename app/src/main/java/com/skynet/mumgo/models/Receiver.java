package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Receiver implements Parcelable {

    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("name")
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.name);
    }

    public Receiver() {
    }

    protected Receiver(Parcel in) {
        this.address = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Receiver> CREATOR = new Parcelable.Creator<Receiver>() {
        @Override
        public Receiver createFromParcel(Parcel source) {
            return new Receiver(source);
        }

        @Override
        public Receiver[] newArray(int size) {
            return new Receiver[size];
        }
    };
}
