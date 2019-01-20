package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Course implements Parcelable {
    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("number_day")
    private String number_day;
    @Expose
    @SerializedName("date_end")
    private String date_end;
    @Expose
    @SerializedName("date_start")
    private String date_start;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("session")
    private List<Season> listSeason;
    public Course() {
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<Season> getListSeason() {
        return listSeason;
    }

    public void setListSeason(List<Season> listSeason) {
        this.listSeason = listSeason;
    }

    public String getNumber_day() {
        return number_day;
    }

    public void setNumber_day(String number_day) {
        this.number_day = number_day;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.active);
        dest.writeString(this.number_day);
        dest.writeString(this.date_end);
        dest.writeString(this.date_start);
        dest.writeString(this.title);
        dest.writeInt(this.user_id);
        dest.writeInt(this.id);
        dest.writeList(this.listSeason);
    }

    protected Course(Parcel in) {
        this.active = in.readString();
        this.number_day = in.readString();
        this.date_end = in.readString();
        this.date_start = in.readString();
        this.title = in.readString();
        this.user_id = in.readInt();
        this.id = in.readInt();
        this.listSeason = new ArrayList<Season>();
        in.readList(this.listSeason, Season.class.getClassLoader());
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
