package com.skynet.thuenha.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {
    @SerializedName("banner")
  private    List<Banner> banners;
    @SerializedName("city")
    private List<Address> city;
    @SerializedName("profile")
    private  Profile profile;
    @SerializedName("service")
    private  List<Service> services;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Address> getCity() {
        return city;
    }

    public void setCity(List<Address> city) {
        this.city = city;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
