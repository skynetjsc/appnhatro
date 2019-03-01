package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {
    @SerializedName("banner")
    private List<Banner> banners;

    @SerializedName("category")
    private List<Category> category;

    @SerializedName("category_combo")
    private List<Banner> combo;

    @SerializedName("category_banner")
    private List<Category> category_banner;

    @SerializedName("parent_category")
    private List<Category> parent_category;

    @SerializedName("suggest")
    private List<Product> suggest;

    @SerializedName("news")
    private List<News> news;

    public List<Banner> getBanners() {
        return banners;
    }

    public List<Banner> getCategory_combo() {
        return combo;
    }

    public List<Category> getCategory_banner() {
        return category_banner;
    }

    public List<Banner> getCombo() {
        return combo;
    }

    public void setCombo(List<Banner> combo) {
        this.combo = combo;
    }

    public void setCategory_banner(List<Category> category_banner) {
        this.category_banner = category_banner;
    }

    public List<Category> getParent_category() {
        return parent_category;
    }

    public void setParent_category(List<Category> parent_category) {
        this.parent_category = parent_category;
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

    public List<Product> getSuggest() {
        return suggest;
    }

    public void setSuggest(List<Product> suggest) {
        this.suggest = suggest;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
