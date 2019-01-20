package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {
    @SerializedName("banner")
  private    List<Banner> banners;
    @SerializedName("category")
    private List<Category> category;
    @SerializedName("suggest")
    private  List<Suggestion> suggest;
    @SerializedName("news")
    private  List<News> news;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Suggestion> getSuggest() {
        return suggest;
    }

    public void setSuggest(List<Suggestion> suggest) {
        this.suggest = suggest;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
