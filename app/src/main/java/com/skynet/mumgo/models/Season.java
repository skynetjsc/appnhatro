package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Season implements Parcelable {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("date_app")
    private String date_app;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("program_user_set_id")
    private int program_user_set_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("number_ex")
    private int number_ex;

    public String getDate_app() {
        return date_app;
    }

    public void setDate_app(String date_app) {
        this.date_app = date_app;
    }

    public int getNumber_ex() {
        return number_ex;
    }

    public void setNumber_ex(int number_ex) {
        this.number_ex = number_ex;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgram_user_set_id() {
        return program_user_set_id;
    }

    public void setProgram_user_set_id(int program_user_set_id) {
        this.program_user_set_id = program_user_set_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Season() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.active);
        dest.writeString(this.date);
        dest.writeString(this.date_app);
        dest.writeString(this.name);
        dest.writeInt(this.program_user_set_id);
        dest.writeInt(this.id);
        dest.writeInt(this.number_ex);
    }

    protected Season(Parcel in) {
        this.active = in.readString();
        this.date = in.readString();
        this.date_app = in.readString();
        this.name = in.readString();
        this.program_user_set_id = in.readInt();
        this.id = in.readInt();
        this.number_ex = in.readInt();
    }

    public static final Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel source) {
            return new Season(source);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };
}
