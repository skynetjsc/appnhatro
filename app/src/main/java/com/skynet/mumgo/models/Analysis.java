package com.skynet.mumgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Analysis implements Parcelable {

    @Expose
    @SerializedName("duiphai")
    private int duiphai;
    @Expose
    @SerializedName("duitrai")
    private int duitrai;
    @Expose
    @SerializedName("bungngangron")
    private int bungngangron;
    @Expose
    @SerializedName("bapphai")
    private int bapphai;
    @Expose
    @SerializedName("baptrai")
    private int baptrai;
    @Expose
    @SerializedName("khoiluongnac")
    private double khoiluongnac;
    @Expose
    @SerializedName("khoiluongmo")
    private double khoiluongmo;
    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("bapchuoiphai")
    private int bapchuoiphai;
    @Expose
    @SerializedName("bapchuoitrai")
    private int bapchuoitrai;
    @Expose
    @SerializedName("coduitrai")
    private int coduitrai;
    @Expose
    @SerializedName("coduiphai")
    private int coduiphai;
    @Expose
    @SerializedName("cobungduoi")
    private int cobungduoi;
    @Expose
    @SerializedName("cobungtren")
    private int cobungtren;
    @Expose
    @SerializedName("eo")
    private int eo;
    @Expose
    @SerializedName("mong")
    private int mong;
    @Expose
    @SerializedName("cangtayphai")
    private int cangtayphai;
    @Expose
    @SerializedName("cangtaytrai")
    private int cangtaytrai;
    @Expose
    @SerializedName("cotayphai")
    private int cotayphai;
    @Expose
    @SerializedName("cotaytrai")
    private int cotaytrai;
    @Expose
    @SerializedName("vai")
    private int vai;
    @Expose
    @SerializedName("nguc")
    private int nguc;
    @Expose
    @SerializedName("phantramnac")
    private double phantramnac;
    @Expose
    @SerializedName("phantrammo")
    private double phantrammo;
    @Expose
    @SerializedName("khoiluong")
    private double khoiluong;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("id")
    private int id;

    public void setKhoiluongnac(double khoiluongnac) {
        this.khoiluongnac = khoiluongnac;
    }

    public void setKhoiluongmo(double khoiluongmo) {
        this.khoiluongmo = khoiluongmo;
    }

    public void setPhantramnac(double phantramnac) {
        this.phantramnac = phantramnac;
    }

    public void setPhantrammo(double phantrammo) {
        this.phantrammo = phantrammo;
    }

    public void setKhoiluong(double khoiluong) {
        this.khoiluong = khoiluong;
    }

    public int getDuiphai() {
        return duiphai;
    }

    public void setDuiphai(int duiphai) {
        this.duiphai = duiphai;
    }

    public int getDuitrai() {
        return duitrai;
    }

    public void setDuitrai(int duitrai) {
        this.duitrai = duitrai;
    }

    public int getBungngangron() {
        return bungngangron;
    }

    public void setBungngangron(int bungngangron) {
        this.bungngangron = bungngangron;
    }

    public int getBapphai() {
        return bapphai;
    }

    public void setBapphai(int bapphai) {
        this.bapphai = bapphai;
    }

    public int getBaptrai() {
        return baptrai;
    }

    public void setBaptrai(int baptrai) {
        this.baptrai = baptrai;
    }


    public void setKhoiluongnac(int khoiluongnac) {
        this.khoiluongnac = khoiluongnac;
    }


    public void setKhoiluongmo(int khoiluongmo) {
        this.khoiluongmo = khoiluongmo;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBapchuoiphai() {
        return bapchuoiphai;
    }

    public void setBapchuoiphai(int bapchuoiphai) {
        this.bapchuoiphai = bapchuoiphai;
    }

    public int getBapchuoitrai() {
        return bapchuoitrai;
    }

    public void setBapchuoitrai(int bapchuoitrai) {
        this.bapchuoitrai = bapchuoitrai;
    }

    public int getCoduitrai() {
        return coduitrai;
    }

    public void setCoduitrai(int coduitrai) {
        this.coduitrai = coduitrai;
    }

    public int getCoduiphai() {
        return coduiphai;
    }

    public void setCoduiphai(int coduiphai) {
        this.coduiphai = coduiphai;
    }

    public int getCobungduoi() {
        return cobungduoi;
    }

    public void setCobungduoi(int cobungduoi) {
        this.cobungduoi = cobungduoi;
    }

    public int getCobungtren() {
        return cobungtren;
    }

    public void setCobungtren(int cobungtren) {
        this.cobungtren = cobungtren;
    }

    public int getEo() {
        return eo;
    }

    public void setEo(int eo) {
        this.eo = eo;
    }

    public int getMong() {
        return mong;
    }

    public void setMong(int mong) {
        this.mong = mong;
    }

    public int getCangtayphai() {
        return cangtayphai;
    }

    public void setCangtayphai(int cangtayphai) {
        this.cangtayphai = cangtayphai;
    }

    public int getCangtaytrai() {
        return cangtaytrai;
    }

    public void setCangtaytrai(int cangtaytrai) {
        this.cangtaytrai = cangtaytrai;
    }

    public int getCotayphai() {
        return cotayphai;
    }

    public void setCotayphai(int cotayphai) {
        this.cotayphai = cotayphai;
    }

    public int getCotaytrai() {
        return cotaytrai;
    }

    public void setCotaytrai(int cotaytrai) {
        this.cotaytrai = cotaytrai;
    }

    public int getVai() {
        return vai;
    }

    public void setVai(int vai) {
        this.vai = vai;
    }

    public int getNguc() {
        return nguc;
    }

    public void setNguc(int nguc) {
        this.nguc = nguc;
    }


    public void setPhantramnac(int phantramnac) {
        this.phantramnac = phantramnac;
    }


    public void setPhantrammo(int phantrammo) {
        this.phantrammo = phantrammo;
    }


    public void setKhoiluong(int khoiluong) {
        this.khoiluong = khoiluong;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getKhoiluongnac() {
        return khoiluongnac;
    }

    public double getKhoiluongmo() {
        return khoiluongmo;
    }

    public double getPhantramnac() {
        return phantramnac;
    }

    public double getPhantrammo() {
        return phantrammo;
    }

    public double getKhoiluong() {
        return khoiluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Analysis() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.duiphai);
        dest.writeInt(this.duitrai);
        dest.writeInt(this.bungngangron);
        dest.writeInt(this.bapphai);
        dest.writeInt(this.baptrai);
        dest.writeDouble(this.khoiluongnac);
        dest.writeDouble(this.khoiluongmo);
        dest.writeInt(this.active);
        dest.writeString(this.date);
        dest.writeInt(this.bapchuoiphai);
        dest.writeInt(this.bapchuoitrai);
        dest.writeInt(this.coduitrai);
        dest.writeInt(this.coduiphai);
        dest.writeInt(this.cobungduoi);
        dest.writeInt(this.cobungtren);
        dest.writeInt(this.eo);
        dest.writeInt(this.mong);
        dest.writeInt(this.cangtayphai);
        dest.writeInt(this.cangtaytrai);
        dest.writeInt(this.cotayphai);
        dest.writeInt(this.cotaytrai);
        dest.writeInt(this.vai);
        dest.writeInt(this.nguc);
        dest.writeDouble(this.phantramnac);
        dest.writeDouble(this.phantrammo);
        dest.writeDouble(this.khoiluong);
        dest.writeInt(this.user_id);
        dest.writeInt(this.id);
    }

    protected Analysis(Parcel in) {
        this.duiphai = in.readInt();
        this.duitrai = in.readInt();
        this.bungngangron = in.readInt();
        this.bapphai = in.readInt();
        this.baptrai = in.readInt();
        this.khoiluongnac = in.readDouble();
        this.khoiluongmo = in.readDouble();
        this.active = in.readInt();
        this.date = in.readString();
        this.bapchuoiphai = in.readInt();
        this.bapchuoitrai = in.readInt();
        this.coduitrai = in.readInt();
        this.coduiphai = in.readInt();
        this.cobungduoi = in.readInt();
        this.cobungtren = in.readInt();
        this.eo = in.readInt();
        this.mong = in.readInt();
        this.cangtayphai = in.readInt();
        this.cangtaytrai = in.readInt();
        this.cotayphai = in.readInt();
        this.cotaytrai = in.readInt();
        this.vai = in.readInt();
        this.nguc = in.readInt();
        this.phantramnac = in.readDouble();
        this.phantrammo = in.readDouble();
        this.khoiluong = in.readDouble();
        this.user_id = in.readInt();
        this.id = in.readInt();
    }

    public static final Creator<Analysis> CREATOR = new Creator<Analysis>() {
        @Override
        public Analysis createFromParcel(Parcel source) {
            return new Analysis(source);
        }

        @Override
        public Analysis[] newArray(int size) {
            return new Analysis[size];
        }
    };
}
