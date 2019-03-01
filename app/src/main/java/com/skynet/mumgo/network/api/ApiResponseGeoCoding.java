package com.skynet.mumgo.network.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thaopt on 11/24/17.
 */

public class ApiResponseGeoCoding<T> {
    @SerializedName("data")
    T data;
    @SerializedName(value = "errorId", alternate = "status")
    String code;
    @SerializedName(value = "message", alternate = "content")
    String message;
    @SerializedName("predictions")
    T place;
    @SerializedName("results")
    T placeNearBy;
    @SerializedName("routes")
    T routes;

    @SerializedName("Count")
    int count;

    public T getRoutes() {
        return routes;
    }

    public void setRoutes(T routes) {
        this.routes = routes;
    }

    public T getPlaceNearBy() {
        return placeNearBy;
    }

    public void setPlaceNearBy(T placeNearBy) {
        this.placeNearBy = placeNearBy;
    }

    public ApiResponseGeoCoding(){};
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public T getPlace() {
        return place;
    }

    public void setPlace(T place) {
        this.place = place;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
