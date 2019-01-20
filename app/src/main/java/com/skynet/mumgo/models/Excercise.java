package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Excercise implements Parcelable {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("link_instruction")
    private String link_instruction;
    @Expose
    @SerializedName("link_support_2")
    private String link_support_2;
    @Expose
    @SerializedName("link_support_1")
    private String link_support_1;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("img1")
    private String img1;
    @Expose
    @SerializedName("img2")
    private String img2;
    @Expose
    @SerializedName("img3")
    private String img3;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    @Expose
    @SerializedName("time_finish")

    private String time_finish;
    @Expose
    @SerializedName("cat_id")
    private int cat_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("finish")
    private int finish;
    @Expose
    @SerializedName("ex_id")
    private int idInSeason;
    @Expose
    @SerializedName("is_fav")
    private int is_fav;
    @Expose
    @SerializedName("sets")
    private int sets;
    @Expose
    @SerializedName("list_sets")
    private List<Set> list_sets;
    @Expose
    @SerializedName("reps")
    private int reps;   @Expose
    @SerializedName("break_time")
    private int break_time;

    public List<Set> getList_sets() {
        return list_sets;
    }

    public void setList_sets(List<Set> list_sets) {
        this.list_sets = list_sets;
    }

    public int getSets() {
        return sets;
    }

    public int getIdInSeason() {
        return idInSeason;
    }

    public String getTime_finish() {
        return time_finish;
    }

    public void setTime_finish(String time_finish) {
        this.time_finish = time_finish;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public void setIdInSeason(int idInSeason) {
        this.idInSeason = idInSeason;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getBreak_time() {
        return break_time;
    }

    public void setBreak_time(int break_time) {
        this.break_time = break_time;
    }

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public int getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(int is_fav) {
        this.is_fav = is_fav;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink_instruction() {
        return link_instruction;
    }

    public void setLink_instruction(String link_instruction) {
        this.link_instruction = link_instruction;
    }

    public String getLink_support_2() {
        return link_support_2;
    }

    public void setLink_support_2(String link_support_2) {
        this.link_support_2 = link_support_2;
    }

    public String getLink_support_1() {
        return link_support_1;
    }

    public void setLink_support_1(String link_support_1) {
        this.link_support_1 = link_support_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Excercise() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.active);
        dest.writeString(this.date);
        dest.writeString(this.img);
        dest.writeString(this.content);
        dest.writeString(this.link_instruction);
        dest.writeString(this.link_support_2);
        dest.writeString(this.link_support_1);
        dest.writeString(this.name);
        dest.writeString(this.img1);
        dest.writeString(this.img2);
        dest.writeString(this.img3);
        dest.writeString(this.time_finish);
        dest.writeInt(this.cat_id);
        dest.writeInt(this.id);
        dest.writeInt(this.finish);
        dest.writeInt(this.idInSeason);
        dest.writeInt(this.is_fav);
        dest.writeInt(this.sets);
        dest.writeTypedList(this.list_sets);
        dest.writeInt(this.reps);
        dest.writeInt(this.break_time);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected Excercise(Parcel in) {
        this.active = in.readString();
        this.date = in.readString();
        this.img = in.readString();
        this.content = in.readString();
        this.link_instruction = in.readString();
        this.link_support_2 = in.readString();
        this.link_support_1 = in.readString();
        this.name = in.readString();
        this.img1 = in.readString();
        this.img2 = in.readString();
        this.img3 = in.readString();
        this.time_finish = in.readString();
        this.cat_id = in.readInt();
        this.id = in.readInt();
        this.finish = in.readInt();
        this.idInSeason = in.readInt();
        this.is_fav = in.readInt();
        this.sets = in.readInt();
        this.list_sets = in.createTypedArrayList(Set.CREATOR);
        this.reps = in.readInt();
        this.break_time = in.readInt();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<Excercise> CREATOR = new Creator<Excercise>() {
        @Override
        public Excercise createFromParcel(Parcel source) {
            return new Excercise(source);
        }

        @Override
        public Excercise[] newArray(int size) {
            return new Excercise[size];
        }
    };
}
