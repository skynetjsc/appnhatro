package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

public class AddressGeocoding {
    @SerializedName("formatted_address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
