package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

public class Promotion  {
    @SerializedName("code")
    String code;
    @SerializedName("value")
    double value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
