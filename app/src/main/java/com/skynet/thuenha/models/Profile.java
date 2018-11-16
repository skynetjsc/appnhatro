package com.skynet.thuenha.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Profile implements Parcelable {

    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("number")
    private int number;
    @Expose
    @SerializedName("type")
    private int type;
    @Expose
    @SerializedName("point")
    private int point;
    @Expose
    @SerializedName("number_post")
    private int number_post;
    @Expose
    @SerializedName("register_date")
    private String register_date;
    @Expose
    @SerializedName("avatar")
    private String avatar;

    @Expose
    @SerializedName("achievement")
    private List<String> archiement;
    @Expose
    @SerializedName("type_device")
    private int type_device;
    @Expose
    @SerializedName("account_number")
    private String account_number;
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("type_account")
    private int type_account;
    @Expose
    @SerializedName("lng")
    private double lng;
    @Expose
    @SerializedName("lat")
    private double lat;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("ggid")
    private String ggid;
    @Expose
    @SerializedName("fbid")
    private String fbid;
    @Expose
    @SerializedName("rf_code")
    private String rf_code;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("department_id")
    private String department_id;
    @PrimaryKey
    @NonNull
    @Expose
    @SerializedName("id")
    private String u_id;
    @Expose
    @SerializedName("account")
    private double accountWallet;
    @Expose
    @SerializedName("post")
    private List<Post> post;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public double getAccountWallet() {
        return accountWallet;
    }

    public void setAccountWallet(double accountWallet) {
        this.accountWallet = accountWallet;
    }

    public int getNumber_post() {
        return number_post;
    }

    public void setNumber_post(int number_post) {
        this.number_post = number_post;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getType_device() {
        return type_device;
    }

    public void setType_device(int type_device) {
        this.type_device = type_device;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public int getType_account() {
        return type_account;
    }

    public void setType_account(int type_account) {
        this.type_account = type_account;
    }

    public double getLng() {
        return lng;
    }

    public List<String> getArchiement() {
        return archiement;
    }

    public void setArchiement(List<String> archiement) {
        this.archiement = archiement;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGgid() {
        return ggid;
    }

    public void setGgid(String ggid) {
        this.ggid = ggid;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getRf_code() {
        return rf_code;
    }

    public void setRf_code(String rf_code) {
        this.rf_code = rf_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    public String getId() {
        return u_id;
    }

    public void setId(@NonNull String u_id) {
        this.u_id = u_id;
    }

    public Profile() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.active);
        dest.writeInt(this.number);
        dest.writeInt(this.type);
        dest.writeInt(this.point);
        dest.writeInt(this.number_post);
        dest.writeString(this.register_date);
        dest.writeString(this.avatar);
        dest.writeStringList(this.archiement);
        dest.writeInt(this.type_device);
        dest.writeString(this.account_number);
        dest.writeString(this.token);
        dest.writeInt(this.type_account);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.ggid);
        dest.writeString(this.fbid);
        dest.writeString(this.rf_code);
        dest.writeString(this.code);
        dest.writeString(this.department_id);
        dest.writeString(this.u_id);
        dest.writeDouble(this.accountWallet);
        dest.writeTypedList(this.post);
    }

    protected Profile(Parcel in) {
        this.active = in.readInt();
        this.number = in.readInt();
        this.type = in.readInt();
        this.point = in.readInt();
        this.number_post = in.readInt();
        this.register_date = in.readString();
        this.avatar = in.readString();
        this.archiement = in.createStringArrayList();
        this.type_device = in.readInt();
        this.account_number = in.readString();
        this.token = in.readString();
        this.type_account = in.readInt();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
        this.password = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.ggid = in.readString();
        this.fbid = in.readString();
        this.rf_code = in.readString();
        this.code = in.readString();
        this.department_id = in.readString();
        this.u_id = in.readString();
        this.accountWallet = in.readDouble();
        this.post = in.createTypedArrayList(Post.CREATOR);
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
