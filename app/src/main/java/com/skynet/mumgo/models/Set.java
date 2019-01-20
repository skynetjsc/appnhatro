package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Set implements Parcelable {
    @SerializedName("number")
    int rep;
    @SerializedName("weight")
    int weight;
    @SerializedName("break_time")
    long time;
    boolean isCount;
    @SerializedName("rm")
    double _1RM;

    public double get_1RM() {
        return _1RM;
    }

    public boolean getIndexCount() {
        return isCount;
    }

    public void setIndexCount(boolean indexCount) {
        this.isCount = indexCount;
    }

    public void set_1RM(double _1RM) {
        this._1RM = _1RM;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rep);
        dest.writeInt(this.weight);
        dest.writeLong(this.time);
        dest.writeByte(this.isCount ? (byte) 1 : (byte) 0);
        dest.writeDouble(this._1RM);
    }

    public Set() {
    }

    protected Set(Parcel in) {
        this.rep = in.readInt();
        this.weight = in.readInt();
        this.time = in.readLong();
        this.isCount = in.readByte() != 0;
        this._1RM = in.readDouble();
    }

    public static final Parcelable.Creator<Set> CREATOR = new Parcelable.Creator<Set>() {
        @Override
        public Set createFromParcel(Parcel source) {
            return new Set(source);
        }

        @Override
        public Set[] newArray(int size) {
            return new Set[size];
        }
    };
}
